package com.furkanisitan.springbootrestdemo.core.helpers;

import com.furkanisitan.springbootrestdemo.entities.enums.ContinentEnum;
import com.furkanisitan.springbootrestdemo.entities.enums.IsOfficialEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumHelper {

    public static List<String> getContinentEnumValuesAsString() {
        return Arrays.stream(ContinentEnum.values()).map(ContinentEnum::getValue).collect(Collectors.toList());
    }

    public static boolean isContinentEnum(String s) {
        return Arrays.stream(ContinentEnum.values()).map(ContinentEnum::getValue).anyMatch(s::equalsIgnoreCase);
    }

    public static List<String> getIsOfficialEnumValuesAsString() {
        return Arrays.stream(IsOfficialEnum.values()).map(IsOfficialEnum::getValue).collect(Collectors.toList());
    }

    public static boolean isOfficialEnum(String s) {
        return Arrays.stream(IsOfficialEnum.values()).map(IsOfficialEnum::getValue).anyMatch(s::equalsIgnoreCase);
    }

}
