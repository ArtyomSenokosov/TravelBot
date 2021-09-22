package ru.mail.senokosov.artem.repository.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "city_info")
public class CityInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "info")
    private String cityInfo;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "city_id")
    private City city;
}