package ru.mail.senokosov.artem.service.converter;

import ru.mail.senokosov.artem.repository.model.City;
import ru.mail.senokosov.artem.repository.model.CityInfo;
import ru.mail.senokosov.artem.service.model.CityDTO;
import ru.mail.senokosov.artem.service.model.CityInfoDTO;

public interface CityInfoConverter {

    CityInfoDTO convert(CityInfo cityInfo);

    CityInfo convert(CityInfoDTO cityInfoDTO);
}
