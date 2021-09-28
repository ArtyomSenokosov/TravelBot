package ru.mail.senokosov.artem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.mail.senokosov.artem.repository.CityRepository;
import ru.mail.senokosov.artem.repository.model.City;
import ru.mail.senokosov.artem.service.CityService;
import ru.mail.senokosov.artem.service.converter.CityConverter;
import ru.mail.senokosov.artem.service.exception.ServiceException;
import ru.mail.senokosov.artem.service.model.CityDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityConverter cityConverter;

    @Override
    @Transactional
    public CityDTO getCityById(Long id) throws ServiceException {
        City city = cityRepository.findById(id);
        if (Objects.nonNull(city)) {
            return cityConverter.convert(city);
        } else {
            throw new ServiceException(String.format("Город с ID=%s не найден", id));
        }
    }

    @Override
    @Transactional
    public List<CityDTO> getAllCity() throws ServiceException {
        List<City> allCities = cityRepository.findAll();
        if (allCities.isEmpty()) {
            throw new ServiceException("Городов в базе нет");
        }
        return allCities.stream()
                .map(cityConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CityDTO persist(CityDTO cityDTO) throws ServiceException {
        City cityByName = cityRepository.findCityByName(cityDTO.getCityName());
        if (Objects.isNull(cityByName)) {
            City city = cityConverter.convert(cityDTO);
            cityRepository.persist(city);
            return cityConverter.convert(city);
        } else {
            throw new ServiceException(String.format("Данный город уже есть в базе!", cityDTO.getCityName()));
        }
    }

    @Override
    @Transactional
    public CityDTO updateCity(Long id, CityDTO cityDTO) throws ServiceException {
        City city = cityRepository.findById(id);
        if (Objects.nonNull(city)) {
            String cityName = cityDTO.getCityName();
            city.setCityName(cityName);
            cityRepository.merge(city);
            return cityConverter.convert(city);
        } else {
            throw new ServiceException(String.format("Город с ID=%s не найден", id));
        }
    }

    @Override
    @Transactional
    public boolean deleteCityById(Long id) {
        cityRepository.removeById(id);
        return true;
    }
}