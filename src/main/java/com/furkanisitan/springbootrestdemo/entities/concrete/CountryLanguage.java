package com.furkanisitan.springbootrestdemo.entities.concrete;

import com.furkanisitan.springbootrestdemo.core.entities.IEntity;
import com.furkanisitan.springbootrestdemo.entities.complextypes.CountryLanguageId;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "countrylanguage")
@Data
public class CountryLanguage implements IEntity {

    @EmbeddedId
    private CountryLanguageId countryLanguageId;

    @Column(name = "IsOfficial", nullable = false)
    private String isOfficial;

    @Column(name = "Percentage", columnDefinition = "decimal(4,1)", nullable = false)
    private BigDecimal percentage;

}
