package ru.mail.senokosov.artem.service.converter.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ru.mail.senokosov.artem.repository.model.City;
import ru.mail.senokosov.artem.repository.model.CityInfo;
import ru.mail.senokosov.artem.service.converter.CityConverter;
import ru.mail.senokosov.artem.service.converter.CityInfoConverter;
import ru.mail.senokosov.artem.service.model.CityDTO;
import ru.mail.senokosov.artem.service.model.CityInfoDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Log4j2
@RequiredArgsConstructor
public class CityConverterImpl implements CityConverter {

    private final CityInfoConverter cityInfoConverter;

    @Override
    public CityDTO convert(City city) {
        CityDTO cityDTO = new CityDTO();
        Long id = city.getId();
        cityDTO.setId(id);
        String cityName = city.getCityName();
        cityDTO.setCityName(cityName);
        Set<CityInfo> cityInfo = city.getCityInfo();
        if (!cityInfo.isEmpty()) {
            List<CityInfoDTO> cityInfoDTOs = cityInfo.stream()
                    .map(cityInfoConverter::convert)
                    .collect(Collectors.toList());
            cityDTO.getCityInfo().addAll(cityInfoDTOs);
        }
        return cityDTO;
    }

    @Override
    public City convert(CityDTO cityDTO) {
        City city = new City();
        String cityName = cityDTO.getCityName();
        city.setCityName(cityName);
        return city;
    }
}