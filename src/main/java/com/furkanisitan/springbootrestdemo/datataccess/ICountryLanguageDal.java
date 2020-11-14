package com.furkanisitan.springbootrestdemo.datataccess;

import com.furkanisitan.springbootrestdemo.entities.complextypes.CountryLanguageId;
import com.furkanisitan.springbootrestdemo.entities.concrete.CountryLanguage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICountryLanguageDal extends org.springframework.data.jpa.repository.JpaRepository<CountryLanguage, CountryLanguageId> {

    List<CountryLanguage> findAllByCountryLanguageId_CountryCode(String countryCode);

    List<CountryLanguage> findAllByCountryLanguageId_Language(String language);

    boolean existsByCountryLanguageId_CountryCode(String countryCode);
}
