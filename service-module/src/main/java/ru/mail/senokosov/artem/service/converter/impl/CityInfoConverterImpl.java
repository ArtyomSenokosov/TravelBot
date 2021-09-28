package ru.mail.senokosov.artem.service.converter.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mail.senokosov.artem.repository.model.CityInfo;
import ru.mail.senokosov.artem.service.converter.CityInfoConverter;
import ru.mail.senokosov.artem.service.model.CityInfoDTO;

@Component
@RequiredArgsConstructor
public class CityInfoConverterImpl implements CityInfoConverter {

    @Override
    public CityInfoDTO convert(CityInfo cityInfo) {
        CityInfoDTO cityInfoDTO = new CityInfoDTO();
        Long id = cityInfo.getId();
        cityInfoDTO.setId(id);
        String info = cityInfo.getCityInfo();
        cityInfoDTO.setCityInfo(info);
        return cityInfoDTO;
    }
}