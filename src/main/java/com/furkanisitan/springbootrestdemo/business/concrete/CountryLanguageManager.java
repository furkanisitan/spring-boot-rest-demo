package com.furkanisitan.springbootrestdemo.business.concrete;

import com.furkanisitan.springbootrestdemo.business._abstract.ICountryLanguageService;
import com.furkanisitan.springbootrestdemo.business._abstract.ICountryService;
import com.furkanisitan.springbootrestdemo.business.validationrules.fluentvalidatior.CountryLanguageValidator;
import com.furkanisitan.springbootrestdemo.core.aspects.annotations.AspectBusinessValidation;
import com.furkanisitan.springbootrestdemo.core.aspects.annotations.AspectEntityExist;
import com.furkanisitan.springbootrestdemo.datataccess.ICountryLanguageDal;
import com.furkanisitan.springbootrestdemo.entities.complextypes.CountryLanguageId;
import com.furkanisitan.springbootrestdemo.entities.concrete.CountryLanguage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryLanguageManager implements ICountryLanguageService {

    private final ICountryLanguageDal countryLanguageDal;

    public CountryLanguageManager(ICountryLanguageDal countryLanguageDal) {
        this.countryLanguageDal = countryLanguageDal;
    }

    @Override
    public List<CountryLanguage> getAll() {
        return countryLanguageDal.findAll();
    }

    @AspectEntityExist(dbOperation = AspectEntityExist.DbOperation.READ, service = ICountryService.class)
    @Override
    public List<CountryLanguage> getAllByCountryCode(String countryCode) {
        return countryLanguageDal.findAllByCountryLanguageId_CountryCode(countryCode);
    }

    @Override
    public List<CountryLanguage> getAllByLanguage(String language) {
        return countryLanguageDal.findAllByCountryLanguageId_Language(language);
    }

    @AspectEntityExist(dbOperation = AspectEntityExist.DbOperation.READ, service = ICountryLanguageService.class)
    @Override
    public CountryLanguage getByCountryLanguageId(CountryLanguageId countryLanguageId) {
        return countryLanguageDal.findById(countryLanguageId).get();
    }

    @AspectBusinessValidation(CountryLanguageValidator.class)
    @AspectEntityExist(dbOperation = AspectEntityExist.DbOperation.CREATE, service = ICountryLanguageService.class)
    @Override
    public void add(CountryLanguage countryLanguage) {
        countryLanguageDal.save(countryLanguage);
    }

    @AspectBusinessValidation(CountryLanguageValidator.class)
    @AspectEntityExist(dbOperation = AspectEntityExist.DbOperation.UPDATE, service = ICountryLanguageService.class)
    @Override
    public void update(CountryLanguage countryLanguage) {
        countryLanguageDal.save(countryLanguage);
    }

    @AspectEntityExist(dbOperation = AspectEntityExist.DbOperation.READ, service = ICountryLanguageService.class)
    @Override
    public void deleteByCountryLanguageId(CountryLanguageId countryLanguageId) {
        countryLanguageDal.deleteById(countryLanguageId);
    }

    @Override
    public boolean existByCountryCode(String countryCode) {
        return countryLanguageDal.existsByCountryLanguageId_CountryCode(countryCode);
    }

    @Override
    public boolean existById(CountryLanguageId countryLanguageId) {
        return countryLanguageDal.existsById(countryLanguageId);
    }
}
