package com.furkanisitan.springbootrestdemo.business.concrete;

import com.furkanisitan.springbootrestdemo.business._abstract.ICityService;
import com.furkanisitan.springbootrestdemo.business._abstract.ICountryService;
import com.furkanisitan.springbootrestdemo.business.validationrules.fluentvalidatior.CityValidator;
import com.furkanisitan.springbootrestdemo.core.aspects.annotations.AspectBusinessValidation;
import com.furkanisitan.springbootrestdemo.core.aspects.annotations.AspectEntityExist;
import com.furkanisitan.springbootrestdemo.datataccess.ICityDal;
import com.furkanisitan.springbootrestdemo.entities.concrete.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityManager implements ICityService {

    private final ICityDal cityDal;

    public CityManager(ICityDal cityDal) {
        this.cityDal = cityDal;
    }

    @Override
    public List<City> getAll() {
        return cityDal.findAll();
    }

    @AspectEntityExist(dbOperation = AspectEntityExist.DbOperation.READ, service = ICountryService.class)
    @Override
    public List<City> getAllByCountryCode(String countryCode) {
        return cityDal.findAllByCountryCode(countryCode);
    }

    @AspectEntityExist(dbOperation = AspectEntityExist.DbOperation.READ, service = ICityService.class)
    @Override
    public City getById(int id) {
        return cityDal.findById(id).get();
    }

    @AspectBusinessValidation(CityValidator.class)
    @AspectEntityExist(dbOperation = AspectEntityExist.DbOperation.CREATE, service = ICityService.class)
    @Override
    public void add(City city) {
        cityDal.save(city);
    }

    @AspectBusinessValidation(CityValidator.class)
    @AspectEntityExist(dbOperation = AspectEntityExist.DbOperation.UPDATE, service = ICityService.class)
    @Override
    public void update(City city) {
        cityDal.save(city);
    }

    @AspectEntityExist(dbOperation = AspectEntityExist.DbOperation.READ, service = ICityService.class)
    @Override
    public void deleteById(int id) {
        cityDal.deleteById(id);
    }

    @Override
    public boolean existByCountryCode(String countryCode) {
        return cityDal.existsByCountryCode(countryCode);
    }

    @Override
    public boolean existById(Integer id) {
        return cityDal.existsById(id);
    }
}
