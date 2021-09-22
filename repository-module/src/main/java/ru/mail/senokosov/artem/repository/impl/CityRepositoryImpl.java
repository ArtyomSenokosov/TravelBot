package ru.mail.senokosov.artem.repository.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import ru.mail.senokosov.artem.repository.CityRepository;
import ru.mail.senokosov.artem.repository.model.City;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Log4j2
@Repository
public class CityRepositoryImpl extends GenericRepositoryImpl<Long, City> implements CityRepository {

    @Override
    public City findCityByNameIgnoreCaseContaining(String name) {
        String stringQuery = "SELECT c FROM City as c WHERE lower(c.city_name)=:lower(city_name)";
        Query query = entityManager.createQuery(stringQuery);
        query.setParameter("city_name", name);
        try {
            query.getSingleResult();
        } catch (NoResultException e) {
            log.error(e.getMessage(), e);
            return null;
        }
        return (City) query.getSingleResult();
    }

    @Override
    public City findCityByName(String name) {
        String stringQuery = "SELECT c FROM City as c WHERE c.city_name=:city_name";
        Query query = entityManager.createQuery(stringQuery);
        query.setParameter("city_name", name);
        try {
            query.getSingleResult();
        } catch (NoResultException e) {
            log.error(e.getMessage(), e);
            return null;
        }
        return (City) query.getSingleResult();
    }
}
