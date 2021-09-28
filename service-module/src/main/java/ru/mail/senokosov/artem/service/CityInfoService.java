package ru.mail.senokosov.artem.service;

import ru.mail.senokosov.artem.service.exception.ServiceException;

import java.util.List;

public interface CityInfoService {

    boolean deleteCityInfoById(Long id);

    List<String> getAllCityInfoByCityName(String cityName) throws ServiceException;
}