package ru.mail.senokosov.artem.web.bot;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.mail.senokosov.artem.service.CityInfoService;
import ru.mail.senokosov.artem.service.CityService;
import ru.mail.senokosov.artem.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class TravelBot extends TelegramLongPollingBot {

    private final CityService cityService;
    private final CityInfoService cityInfoService;
    @Value("${telegram.botusername}")
    private String botUSerName;
    @Value("${telegram.token}")
    private String botToken;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                log.info(update.toString());
                sendMsg(update.getMessage().getChatId(),
                        update.getMessage().getText(),
                        update.getMessage().getMessageId());
            }
        } else if (update.hasCallbackQuery()) {
            log.info("Callback {}", update.toString());
            EditMessageText newMessage = new EditMessageText()
                    .setChatId(update.getCallbackQuery().getMessage().getChatId())
                    .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                    .setText(update.getCallbackQuery().getData());
            try {
                execute(newMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            sendMsg(update.getCallbackQuery().getMessage().getChatId(),
                    update.getCallbackQuery().getData(),
                    update.getCallbackQuery().getMessage().getMessageId());
        }
    }

    @SneakyThrows
    private void sendMsg(Long chatId, String message, Integer messageId) throws ServiceException {

        SendMessage sendMessage;
        String send = "";

        if (message.equals("/start") || message.equals("/Start")) {
            send = "Добро пожаловать! \nТуристический помощник подскажет Вам лучшие места для посещения. \nВведите название города:";
            sendMessage = createMessage(send, chatId, messageId);
        } else {
            List<String> allCityInfo = cityInfoService.getAllCityInfoByCityName(message.strip());

            if (allCityInfo != null) {
                if (!allCityInfo.isEmpty()) {
                    send = String.join(".\n", allCityInfo);
                } else {
                    send = "В этом городе смотреть нечего. Езжай отсюда.";
                }
                sendMessage = createMessage(send, chatId, messageId);
            } else {
                send = "У нас в базе нет информации о таком городе.";
                sendMessage = createMessage(send, chatId, messageId);
            }
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Message not sent. Update message {}", message);
            e.printStackTrace();
        }
    }

    private SendMessage createMessage(String send, Long chatId, Integer messageId, InlineKeyboardMarkup setInline) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(setInline);
        sendMessage.setText(send);

        return sendMessage;
    }

    private SendMessage createMessage(String send, Long chatId, Integer messageId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyToMessageId(messageId);
        sendMessage.setChatId(chatId);
        sendMessage.setText(send);
        return sendMessage;
    }


    private InlineKeyboardMarkup setInline(List<String> cities) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (String city : cities) {
            List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
            keyboardButtonsRow.add(new InlineKeyboardButton().setText(city).setCallbackData(city));
            rowList.add(keyboardButtonsRow);
        }

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }


    @Override
    public String getBotUsername() {
        return botUSerName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}