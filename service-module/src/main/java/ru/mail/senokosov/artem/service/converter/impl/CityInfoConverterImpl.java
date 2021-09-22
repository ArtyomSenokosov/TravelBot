package ru.mail.senokosov.artem.service.converter.impl;

import org.springframework.stereotype.Component;
import ru.mail.senokosov.artem.repository.model.CityInfo;
import ru.mail.senokosov.artem.service.converter.CityInfoConverter;
import ru.mail.senokosov.artem.service.model.CityInfoDTO;

@Component
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

    @Override
    public CityInfo convert(CityInfoDTO cityInfoDTO) {
        CityInfo cityInfo = new CityInfo();
        String info = cityInfoDTO.getCityInfo();
        cityInfo.setCityInfo(info);
        return cityInfo;
    }
}
