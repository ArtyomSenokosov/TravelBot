package ru.mail.senokosov.artem.service;

import ru.mail.senokosov.artem.service.exception.ServiceException;
import ru.mail.senokosov.artem.service.model.CityDTO;

import java.util.List;

public interface CityService {

    CityDTO getCityById(Long id) throws ServiceException;

    List<CityDTO> getAllCity() throws ServiceException;

    CityDTO persist(CityDTO cityDTO) throws ServiceException;

    boolean deleteCityById(Long id);

    CityDTO updateCity(Long id, CityDTO cityDTO) throws ServiceException;
}