package com.furkanisitan.springbootrestdemo.restapi;

import com.furkanisitan.springbootrestdemo.business._abstract.ICountryLanguageService;
import com.furkanisitan.springbootrestdemo.entities.complextypes.CountryLanguageId;
import com.furkanisitan.springbootrestdemo.entities.concrete.CountryLanguage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country-languages")
public class CountryLanguageController {

    private final ICountryLanguageService countryLanguageService;

    public CountryLanguageController(ICountryLanguageService countryLanguageService) {
        this.countryLanguageService = countryLanguageService;
    }

    @GetMapping
    public List<CountryLanguage> getAll() {
        return countryLanguageService.getAll();
    }

    @GetMapping("/{countryCode}")
    public List<CountryLanguage> getAllByCountryCode(@PathVariable String countryCode) {
        return countryLanguageService.getAllByCountryCode(countryCode);
    }

    @GetMapping("/get-all-by-language/{language}")
    public List<CountryLanguage> getAllByLanguage(@PathVariable String language) {
        return countryLanguageService.getAllByLanguage(language);
    }

    @GetMapping("/{countryCode}/{language}")
    public CountryLanguage getByLanguageAndCountryCode(@PathVariable String countryCode, @PathVariable String language) {
        return countryLanguageService.getByCountryLanguageId(new CountryLanguageId(countryCode, language));
    }

    @PostMapping("/add")
    public void add(@RequestBody CountryLanguage countryLanguage) {
        countryLanguageService.add(countryLanguage);
    }

    @PostMapping("/update")
    public void update(@RequestBody CountryLanguage countryLanguage) {
        countryLanguageService.update(countryLanguage);
    }

    @PostMapping("/delete-by-country-language-id")
    public void deleteByCountryLanguageId(@RequestBody CountryLanguageId countryLanguageId) {
        countryLanguageService.deleteByCountryLanguageId(countryLanguageId);
    }
}