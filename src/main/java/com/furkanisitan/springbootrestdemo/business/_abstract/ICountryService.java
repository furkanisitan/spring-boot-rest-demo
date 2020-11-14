package com.furkanisitan.springbootrestdemo.business._abstract;

import com.furkanisitan.springbootrestdemo.core.business.IEntityAspectService;
import com.furkanisitan.springbootrestdemo.entities.concrete.Country;

import java.util.List;

public interface ICountryService extends IEntityAspectService<Country, String> {

    List<Country> getAll();

    Country getByCode(String code);

    void add(Country country);

    void update(Country country);

    void deleteByCode(String code);
}
