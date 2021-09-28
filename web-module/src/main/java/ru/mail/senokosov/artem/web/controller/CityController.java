package ru.mail.senokosov.artem.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mail.senokosov.artem.service.CityService;
import ru.mail.senokosov.artem.service.exception.ServiceException;
import ru.mail.senokosov.artem.service.model.CityDTO;

import java.util.List;
import java.util.Objects;

import static ru.mail.senokosov.artem.web.constant.PathConstant.CITY_PATH;
import static ru.mail.senokosov.artem.web.constant.PathConstant.REST_API_CITY_PATH;

@RestController
@RequestMapping(value = REST_API_CITY_PATH,
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Log4j2
public class CityController {

    private final CityService cityService;

    @GetMapping(value = CITY_PATH)
    public List<CityDTO> getAllCity() throws ServiceException {
        return cityService.getAllCity();
    }

    @SneakyThrows
    @GetMapping(value = CITY_PATH + "/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable Long id) throws ServiceException {
        CityDTO cityById = cityService.getCityById(id);
        if (Objects.nonNull(cityById)) {
            return new ResponseEntity<>(cityById, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @SneakyThrows
    @PostMapping(value = CITY_PATH)
    public ResponseEntity<CityDTO> addCity(@RequestBody CityDTO cityDTO) throws ServiceException {
        cityService.persist(cityDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDTO> updateCity(@PathVariable Long id,
                                              @RequestBody CityDTO cityDto) {
        CityDTO updatedCityDTO = null;
        try {
            updatedCityDTO = cityService.updateCity(id, cityDto);
        } catch (ru.mail.senokosov.artem.service.exception.ServiceException e) {
            e.printStackTrace();
        }
        ResponseEntity<CityDTO> response = new ResponseEntity<>(updatedCityDTO, HttpStatus.OK);
        log.info("{} -- Update city with ID={} with information {}", log.getName(), id, cityDto.toString());
        return response;
    }

    @DeleteMapping(value = CITY_PATH + "/{id}")
    public ResponseEntity<Void> deleteCityById(@PathVariable Long id) throws ServiceException {
        boolean deleteById = cityService.deleteCityById(id);
        if (deleteById) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}