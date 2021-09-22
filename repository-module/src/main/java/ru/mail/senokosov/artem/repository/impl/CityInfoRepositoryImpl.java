package ru.mail.senokosov.artem.repository.impl;

import org.springframework.stereotype.Repository;
import ru.mail.senokosov.artem.repository.CityInfoRepository;
import ru.mail.senokosov.artem.repository.model.CityInfo;

@Repository
public class CityInfoRepositoryImpl extends GenericRepositoryImpl<Long, CityInfo> implements CityInfoRepository {
}
