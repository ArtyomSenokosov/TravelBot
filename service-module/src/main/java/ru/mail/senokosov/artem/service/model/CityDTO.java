package ru.mail.senokosov.artem.service.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class CityDTO {

    private Long id;
    private String cityName;
    private List<CityInfoDTO> cityInfo;
}