package ru.mail.senokosov.artem.service;

import java.util.List;

public interface CityInfoService {

    boolean deleteCityInfoById(Long id);

    List<String> getAllCityInfoByCityName(String cityName);
}