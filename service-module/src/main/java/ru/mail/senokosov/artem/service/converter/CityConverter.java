package ru.mail.senokosov.artem.service.converter;

import ru.mail.senokosov.artem.repository.model.City;
import ru.mail.senokosov.artem.service.model.CityDTO;

public interface CityConverter {

    CityDTO convert(City city);

    City convert(CityDTO cityDTO);
}
