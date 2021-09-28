package ru.mail.senokosov.artem.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Configuration
@ComponentScan(basePackages = {"ru.mail.senokosov.artem.repository", "ru.mail.senokosov.artem.service"})
public class ApplicationConfig {

    @Bean
    public TelegramBotsApi BotReg(TelegramLongPollingBot bot) {
        ApiContextInitializer.init();
        TelegramBotsApi botReg = new TelegramBotsApi();
        try {
            botReg.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        return botReg;
    }

    @Bean
    public DefaultBotOptions botOptions() {
        return new DefaultBotOptions();
    }
}