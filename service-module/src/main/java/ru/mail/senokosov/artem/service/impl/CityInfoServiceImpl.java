package ru.mail.senokosov.artem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.mail.senokosov.artem.repository.CityInfoRepository;
import ru.mail.senokosov.artem.repository.CityRepository;
import ru.mail.senokosov.artem.repository.model.City;
import ru.mail.senokosov.artem.repository.model.CityInfo;
import ru.mail.senokosov.artem.service.CityInfoService;
import ru.mail.senokosov.artem.service.converter.CityConverter;
import ru.mail.senokosov.artem.service.converter.CityInfoConverter;
import ru.mail.senokosov.artem.service.exception.ServiceException;
import ru.mail.senokosov.artem.service.model.CityInfoDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@Service
public class CityInfoServiceImpl implements CityInfoService {

    private final CityInfoRepository cityInfoRepository;
    private final CityRepository cityRepository;
    private final CityInfoConverter cityInfoConverter;
    private final CityConverter cityConverter;

    @Override
    @Transactional
    public boolean deleteCityInfoById(Long id) {
        cityInfoRepository.removeById(id);
        return true;
    }

    @Override
    @Transactional
    public List<CityInfoDTO> getAllCityInfoByCityName(String cityName) throws ServiceException {
        City city = cityRepository.findCityByNameIgnoreCaseContaining(cityName);
        if (Objects.nonNull(city)) {
            List<CityInfo> cityInfo = city.getCityInfo();
            if (Objects.nonNull(cityInfo)) {
                return cityInfo.stream()
                        .map(cityInfoConverter::convert)
                        .collect(Collectors.toList());
            }
            return null;
        } else {
            throw new ServiceException(String.format("Города %s нет в базе ", cityName));
        }
    }
}