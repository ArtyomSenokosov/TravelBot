package ru.mail.senokosov.artem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.mail.senokosov.artem.repository.CityInfoRepository;
import ru.mail.senokosov.artem.repository.CityRepository;
import ru.mail.senokosov.artem.repository.model.City;
import ru.mail.senokosov.artem.repository.model.CityInfo;
import ru.mail.senokosov.artem.service.CityInfoService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@Service
public class CityInfoServiceImpl implements CityInfoService {

    private final CityInfoRepository cityInfoRepository;
    private final CityRepository cityRepository;

    @Override
    @Transactional
    public boolean deleteCityInfoById(Long id) {
        cityInfoRepository.removeById(id);
        return true;
    }

    @Override
    @Transactional
    public List<String> getAllCityInfoByCityName(String cityName) {
        City city = cityRepository.findCityByName(cityName);
        if (Objects.nonNull(city)) {
            Set<CityInfo> cityInfo = city.getCityInfo();
            if (Objects.nonNull(cityInfo)) {
                return cityInfo.stream()
                        .map(CityInfo::getCityInfo)
                        .collect(Collectors.toList());
            }
        }
        return null;
    }
}