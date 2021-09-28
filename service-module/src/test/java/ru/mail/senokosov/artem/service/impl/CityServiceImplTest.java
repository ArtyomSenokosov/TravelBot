package ru.mail.senokosov.artem.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.mail.senokosov.artem.repository.CityRepository;
import ru.mail.senokosov.artem.repository.model.City;
import ru.mail.senokosov.artem.service.converter.CityConverter;
import ru.mail.senokosov.artem.service.exception.ServiceException;
import ru.mail.senokosov.artem.service.model.CityDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;
    @Mock
    private CityConverter cityConverter;
    @InjectMocks
    private CityServiceImpl cityService;

    @Test
    void shouldFindCityByIdAndReturnExceptionIfCityNotFound() {
        Long id = 1L;
        assertThrows(ServiceException.class, () -> cityService.getCityById(id));
    }

    @Test
    void shouldFindCityByIdAndReturnNotNullIfCityWasFound() {
        Long id = 1L;
        City city = new City();
        city.setId(id);
        when(cityRepository.findById(id)).thenReturn(city);
        CityDTO cityDTO = new CityDTO();
        assertNotNull(cityDTO);
    }

    @Test
    void shouldGetCityById() throws ServiceException {
        Long id = 1L;
        City city = new City();
        city.setId(id);
        when(cityRepository.findById(id)).thenReturn(city);
        CityDTO cityDTO = new CityDTO();
        when(cityConverter.convert(city)).thenReturn(cityDTO);

        CityDTO cityById = cityService.getCityById(id);

        assertEquals(cityDTO, cityById);
    }

    @Test
    void shouldGetAllCities() {
        List<City> citiesWithMock = new ArrayList<>();
        when(cityRepository.findAll()).thenReturn(citiesWithMock);
        List<CityDTO> citiesDTOWithMock = citiesWithMock.stream()
                .map(cityConverter::convert)
                .collect(Collectors.toList());

        List<City> cities = cityRepository.findAll();
        List<CityDTO> citiesDTO = cities.stream()
                .map(cityConverter::convert)
                .collect(Collectors.toList());

        assertEquals(citiesDTOWithMock, citiesDTO);
    }

    @Test
    void shouldChangeCityNameByIdAndReturnExceptionIfCityWithIdNotFound() {
        Long id = 1L;
        assertThrows(ServiceException.class, () -> cityService.getCityById(id));
    }

    @Test
    void shouldChangeCityNameByIdAndReturnNotNullObject() throws ServiceException {
        Long id = 1L;
        City city = new City();
        when(cityRepository.findById(id)).thenReturn(city);
        CityDTO cityDTO = new CityDTO();
        when(cityService.updateCity(id, cityDTO)).thenReturn(cityDTO);

        assertNotNull(cityDTO);
    }

    @Test
    void shouldDeleteArticleById() {
        Long id = 1L;
        boolean isDeleteCity = cityService.deleteCityById(id);

        assertTrue(isDeleteCity);
    }
}
