package ru.mail.senokosov.artem.repository;

import org.springframework.stereotype.Repository;
import ru.mail.senokosov.artem.repository.model.City;

@Repository
public interface CityRepository extends GenericRepository<Long, City> {

    City findCityByNameIgnoreCaseContaining(String name);

    City findCityByName(String name);
}