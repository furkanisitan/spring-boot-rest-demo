package com.furkanisitan.springbootrestdemo.entities.enums;

import lombok.Getter;

public enum ContinentEnum {
    Asia("Asia"),
    Africa("Africa"),
    Antarctica("Antarctica"),
    Europe("Europe"),
    North_America("North America"),
    Oceania("Oceania"),
    South_America("South America");

    @Getter
    private final String value;

    ContinentEnum(String value) {
        this.value = value;
    }

}
