package com.furkanisitan.springbootrestdemo.entities.concrete;

import com.furkanisitan.springbootrestdemo.core.entities.IEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "city")
@Data
public class City implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "Name", length = 35, nullable = false)
    private String name;

    @Column(name = "CountryCode", length = 3, nullable = false)
    private String countryCode;

    @Column(name = "District", length = 20, nullable = false)
    private String district;

    @Column(name = "Population", nullable = false)
    private int population;
}
