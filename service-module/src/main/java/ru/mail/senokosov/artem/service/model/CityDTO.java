package ru.mail.senokosov.artem.service.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CityDTO {

    private Long id;
    private String cityName;
    private List<CityInfoDTO> cityInfo = new ArrayList<>();
}