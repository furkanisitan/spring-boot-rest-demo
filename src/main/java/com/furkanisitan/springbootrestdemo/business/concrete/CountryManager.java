package com.furkanisitan.springbootrestdemo.business.concrete;

import com.furkanisitan.springbootrestdemo.business._abstract.ICityService;
import com.furkanisitan.springbootrestdemo.business._abstract.ICountryLanguageService;
import com.furkanisitan.springbootrestdemo.business._abstract.ICountryService;
import com.furkanisitan.springbootrestdemo.business.validationrules.fluentvalidatior.CountryValidator;
import com.furkanisitan.springbootrestdemo.core.aspects.annotations.AspectBusinessValidation;
import com.furkanisitan.springbootrestdemo.core.aspects.annotations.AspectEntityExist;
import com.furkanisitan.springbootrestdemo.datataccess.ICountryDal;
import com.furkanisitan.springbootrestdemo.entities.concrete.Country;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class CountryManager implements ICountryService {

    private final ICountryDal countryDal;
    private final ICityService cityService;
    private final ICountryLanguageService countryLanguageService;

    public CountryManager(ICountryDal countryDal, ICityService cityService, ICountryLanguageService countryLanguageService) {
        this.countryDal = countryDal;
        this.cityService = cityService;
        this.countryLanguageService = countryLanguageService;
    }

    @Override
    public List<Country> getAll() {
        return countryDal.findAll();
    }

    @AspectEntityExist(dbOperation = AspectEntityExist.DbOperation.READ, service = ICountryService.class)
    @Override
    public Country getByCode(String code) {
        return countryDal.findById(code).get();
    }

    @AspectBusinessValidation(CountryValidator.class)
    @AspectEntityExist(dbOperation = AspectEntityExist.DbOperation.CREATE, service = ICountryService.class)
    @Override
    public void add(Country country) {
        countryDal.save(country);
    }

    @AspectBusinessValidation(CountryValidator.class)
    @AspectEntityExist(dbOperation = AspectEntityExist.DbOperation.UPDATE, service = ICountryService.class)
    @Override
    public void update(Country country) {
        countryDal.save(country);
    }

    @AspectEntityExist(dbOperation = AspectEntityExist.DbOperation.READ, service = ICountryService.class)
    @Override
    public void deleteByCode(String code) {

        if (cityService.existByCountryCode(code))
            throw new EntityExistsException(String.format("The country cannot be deleted because there are City records that use this {code='%s'} parameter as a foreign key.", code));

        if (countryLanguageService.existByCountryCode(code))
            throw new EntityExistsException(String.format("The country cannot be deleted because there are CountryLanguage records that use this {code='%s'} parameter as a foreign key.", code));

        countryDal.deleteById(code);
    }

    @Override
    public boolean existById(String id) {
        return countryDal.existsById(id);
    }
}
