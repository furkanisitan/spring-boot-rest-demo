package com.furkanisitan.springbootrestdemo.entities.enums;

import lombok.Getter;

public enum IsOfficialEnum {

    T("T"), F("F");

    @Getter
    private final String value;

    IsOfficialEnum(String value) {
        this.value = value;
    }

}
