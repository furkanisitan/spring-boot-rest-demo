package com.furkanisitan.springbootrestdemo.restapi;

import com.furkanisitan.springbootrestdemo.business._abstract.ICityService;
import com.furkanisitan.springbootrestdemo.business._abstract.ICountryLanguageService;
import com.furkanisitan.springbootrestdemo.business._abstract.ICountryService;
import com.furkanisitan.springbootrestdemo.entities.concrete.City;
import com.furkanisitan.springbootrestdemo.entities.concrete.Country;
import com.furkanisitan.springbootrestdemo.entities.concrete.CountryLanguage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final ICountryService countryService;
    private final ICityService cityService;
    private final ICountryLanguageService countryLanguageService;

    public CountryController(ICountryService countryService, ICityService cityService, ICountryLanguageService countryLanguageService) {
        this.countryService = countryService;
        this.cityService = cityService;
        this.countryLanguageService = countryLanguageService;
    }

    @GetMapping
    public List<Country> getAll() {
        return countryService.getAll();
    }

    @GetMapping("/{code}")
    public Country getByCode(@PathVariable String code) {
        return countryService.getByCode(code);
    }

    @GetMapping("/{code}/cities")
    public List<City> getCitiesByCode(@PathVariable String code) {
        return cityService.getAllByCountryCode(code);
    }

    @GetMapping("/{code}/country-languages")
    public List<CountryLanguage> getCountryLanguagesByCode(@PathVariable String code) {
        return countryLanguageService.getAllByCountryCode(code);
    }

    @PostMapping("/add")
    public void add(@RequestBody Country country) {
        countryService.add(country);
    }

    @PostMapping("/update")
    public void update(@RequestBody Country country) {
        countryService.update(country);
    }

    @PostMapping("/delete-by-code")
    public void deleteByCode(@RequestParam String code) {
        countryService.deleteByCode(code);
    }

}
