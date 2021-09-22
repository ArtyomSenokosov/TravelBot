package ru.mail.senokosov.artem.service;

import ru.mail.senokosov.artem.service.exception.ServiceException;
import ru.mail.senokosov.artem.service.model.CityInfoDTO;

import java.util.List;

public interface CityInfoService {

    boolean deleteCityInfoById(Long id);

    List<CityInfoDTO> getAllCityInfoByCityName(String cityName) throws ServiceException;
}