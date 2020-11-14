package com.furkanisitan.springbootrestdemo.entities.concrete;

import com.furkanisitan.springbootrestdemo.core.entities.IEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "country")
@Data
public class Country implements IEntity {

    @Id
    @Column(name = "Code", length = 3, nullable = false)
    private String code;

    @Column(name = "Name", length = 52, nullable = false)
    private String name;

    @Column(name = "Continent", nullable = false)
    private String continent;

    @Column(name = "Region", length = 26, nullable = false)
    private String region;

    @Column(name = "Population", nullable = false)
    private int population;

    @Column(name = "LifeExpectancy", columnDefinition = "decimal(3,1)")
    private BigDecimal lifeExpectancy;
}
