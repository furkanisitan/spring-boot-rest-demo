package com.furkanisitan.springbootrestdemo.business._abstract;

import com.furkanisitan.springbootrestdemo.core.business.IEntityAspectService;
import com.furkanisitan.springbootrestdemo.entities.complextypes.CountryLanguageId;
import com.furkanisitan.springbootrestdemo.entities.concrete.CountryLanguage;

import java.util.List;

public interface ICountryLanguageService extends IEntityAspectService<CountryLanguage, CountryLanguageId> {

    List<CountryLanguage> getAll();

    List<CountryLanguage> getAllByCountryCode(String countryCode);

    List<CountryLanguage> getAllByLanguage(String language);

    CountryLanguage getByCountryLanguageId(CountryLanguageId countryLanguageId);

    void add(CountryLanguage countryLanguage);

    void update(CountryLanguage countryLanguage);

    void deleteByCountryLanguageId(CountryLanguageId countryLanguageId);

    boolean existByCountryCode(String countryCode);
}
