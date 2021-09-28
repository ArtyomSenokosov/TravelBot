package ru.mail.senokosov.artem.service.converter.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.mail.senokosov.artem.repository.model.City;
import ru.mail.senokosov.artem.repository.model.CityInfo;
import ru.mail.senokosov.artem.service.converter.CityInfoConverter;
import ru.mail.senokosov.artem.service.model.CityDTO;
import ru.mail.senokosov.artem.service.model.CityInfoDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CityConverterImplTest {

    @Mock
    private CityInfoConverter cityInfoConverter;
    @InjectMocks
    private CityConverterImpl cityConverter;

    @Test
    void shouldConvertCityToCityDTOAndReturnRightId() {
        City city = new City();
        Long id = 1L;
        city.setId(id);
        CityDTO cityDTO = cityConverter.convert(city);

        assertEquals(id, cityDTO.getId());
    }

    @Test
    void shouldConvertCityToCityDTOAndReturnRightName() {
        City city = new City();
        String name = "test name";
        city.setCityName(name);
        CityDTO cityDTO = cityConverter.convert(city);

        assertEquals(name, cityDTO.getCityName());
    }

    @Test
    void shouldConvertCityToCityDTOAndReturnRightCityInfo() {
        Set<CityInfo> cityInfos = new HashSet<>();

        CityInfo cityInfo = new CityInfo();
        Long cityInfoId = 1L;
        cityInfo.setId(cityInfoId);
        String cityInfoIn = "info test";
        cityInfo.setCityInfo(cityInfoIn);
        cityInfos.add(cityInfo);

        CityInfo cityInfo2 = new CityInfo();
        Long cityInfo2Id = 2L;
        cityInfo2.setId(cityInfo2Id);
        String cityInfo2In = "info test 2";
        cityInfo2.setCityInfo(cityInfo2In);
        cityInfos.add(cityInfo2);

        City city = new City();
        city.setCityInfo(cityInfos);
        List<CityInfoDTO> cityInfoDTOS = cityInfos.stream()
                .map(cityInfoConverter::convert)
                .collect(Collectors.toList());
        CityDTO cityDTO = cityConverter.convert(city);

        assertEquals(cityInfoDTOS, cityDTO.getCityInfo());
    }

    @Test
    void shouldConvertCityDTOToCityAndReturnRightName() {
        CityDTO cityDTO = new CityDTO();
        String name = "test name";
        cityDTO.setCityName(name);

        City city = cityConverter.convert(cityDTO);
        assertEquals(name, city.getCityName());
    }
}
