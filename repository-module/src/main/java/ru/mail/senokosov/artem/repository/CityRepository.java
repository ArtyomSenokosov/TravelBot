package ru.mail.senokosov.artem.repository;

import ru.mail.senokosov.artem.repository.model.City;

public interface CityRepository extends GenericRepository<Long, City> {

    City findCityByName(String name);
}