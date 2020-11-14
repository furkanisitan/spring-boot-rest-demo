package com.furkanisitan.springbootrestdemo.entities.complextypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryLanguageId implements Serializable {

    @Column(name = "CountryCode", length = 3, nullable = false)
    private String countryCode;

    @Column(name = "Language", length = 30, nullable = false)
    private String language;
}
