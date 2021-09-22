package ru.mail.senokosov.artem.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mail.senokosov.artem.service.CityInfoService;
import ru.mail.senokosov.artem.service.exception.ServiceException;

import static ru.mail.senokosov.artem.web.constant.PathConstant.*;

@RestController
@RequestMapping(REST_API_CITY_PATH)
@RequiredArgsConstructor
@Log4j2
public class CityInfoController {

    private final CityInfoService cityInfoService;

    @DeleteMapping(value = CITY_INFO_PATH + "/{id}")
    public ResponseEntity<Void> deleteCityInfoById(@PathVariable Long id) throws ServiceException {
        boolean deleteById = cityInfoService.deleteCityInfoById(id);
        if (deleteById) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}