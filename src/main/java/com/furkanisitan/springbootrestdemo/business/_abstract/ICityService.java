package com.furkanisitan.springbootrestdemo.business._abstract;


import com.furkanisitan.springbootrestdemo.core.business.IEntityAspectService;
import com.furkanisitan.springbootrestdemo.entities.concrete.City;

import java.util.List;

public interface ICityService extends IEntityAspectService<City, Integer> {

    List<City> getAll();

    List<City> getAllByCountryCode(String countryCode);

    City getById(int id);

    void add(City city);

    void update(City city);

    void deleteById(int id);

    boolean existByCountryCode(String countryCode);
}
