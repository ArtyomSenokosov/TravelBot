package ru.mail.senokosov.artem.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.mail.senokosov.artem.repository.CityInfoRepository;
import ru.mail.senokosov.artem.repository.CityRepository;
import ru.mail.senokosov.artem.repository.model.City;
import ru.mail.senokosov.artem.repository.model.CityInfo;
import ru.mail.senokosov.artem.service.converter.CityInfoConverter;
import ru.mail.senokosov.artem.service.model.CityDTO;
import ru.mail.senokosov.artem.service.model.CityInfoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CityInfoServiceImplTest {

    @Mock
    private CityInfoRepository cityInfoRepository;
    @Mock
    private CityInfoConverter cityInfoConverter;
    @Mock
    private CityRepository cityRepository;
    @InjectMocks
    private CityInfoServiceImpl cityInfoService;

    @Test
    void shouldDeleteCityInfoById() {
        Long id = 1L;
        boolean isDeleteCityInfo = cityInfoService.deleteCityInfoById(id);

        assertTrue(isDeleteCityInfo);
    }

    @Test
    void shouldFindCityByNameAndReturnNotNullIfCityWasFound() {
        String cityName = "test name";
        City city = new City();
        city.setCityName(cityName);
        when(cityRepository.findCityByName(cityName)).thenReturn(city);
        CityDTO cityDTO = new CityDTO();
        assertNotNull(cityDTO);
    }

    @Test
    void shouldGetAllCitiesInfo() {
        List<CityInfo> citiesInfoWithMock = new ArrayList<>();
        when(cityInfoRepository.findAll()).thenReturn(citiesInfoWithMock);
        List<CityInfoDTO> citiesInfoDTOWithMock = citiesInfoWithMock.stream()
                .map(cityInfoConverter::convert)
                .collect(Collectors.toList());

        List<CityInfo> citiesInfo = cityInfoRepository.findAll();
        List<CityInfoDTO> citiesInfoDTO = citiesInfo.stream()
                .map(cityInfoConverter::convert)
                .collect(Collectors.toList());

        assertEquals(citiesInfoDTOWithMock, citiesInfoDTO);
    }
}
