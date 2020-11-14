package com.furkanisitan.springbootrestdemo.datataccess;

import com.furkanisitan.springbootrestdemo.entities.concrete.City;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICityDal extends org.springframework.data.jpa.repository.JpaRepository<City, Integer> {

    List<City> findAllByCountryCode(String countryCode);

    boolean existsByCountryCode(String countryCode);
}

