package ru.mail.senokosov.artem.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mail.senokosov.artem.service.CityInfoService;
import ru.mail.senokosov.artem.service.CityService;
import ru.mail.senokosov.artem.service.exception.ServiceException;

import static ru.mail.senokosov.artem.web.constant.PathConstant.*;

@RestController
@RequestMapping(value = REST_API_CITY_PATH,
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CityInfoController {

    private final CityInfoService cityInfoService;
    private final CityService cityService;

    @DeleteMapping(value = CITY_INFO_PATH + "/{id}")
    public ResponseEntity<Void> deleteCityInfoById(@PathVariable Long id) throws ServiceException {
        boolean deleteInfoById = cityInfoService.deleteCityInfoById(id);
        if (deleteInfoById) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}