package com.furkanisitan.springbootrestdemo.datataccess;

import com.furkanisitan.springbootrestdemo.entities.concrete.Country;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryDal extends org.springframework.data.jpa.repository.JpaRepository<Country, String> {
}
