package com.furkanisitan.springbootrestdemo.business.validationrules.fluentvalidatior;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.predicate.ComparablePredicate;
import com.furkanisitan.springbootrestdemo.business._abstract.ICountryService;
import com.furkanisitan.springbootrestdemo.core.helpers.ErrorMessageHelper;
import com.furkanisitan.springbootrestdemo.entities.concrete.City;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeLessThanOrEqual;

@Component
public class CityValidator extends AbstractValidator<City> {

    private final ICountryService countryService;

    public CityValidator(ICountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void rules() {

        ruleFor(City::getName)
                .must(not(stringEmptyOrNull())).withMessage(ErrorMessageHelper.emptyOrNull("Name")).withFieldName("name").withAttempedValue(City::getName)
                .must(stringSizeLessThanOrEqual(35)).when(not(stringEmptyOrNull())).withMessage(ErrorMessageHelper.sizeLessThanOrEqual("Name", 35)).withFieldName("name").withAttempedValue(City::getName);

        ruleFor(City::getCountryCode)
                .must(countryService::existById).when(not(stringEmptyOrNull())).withMessage(ErrorMessageHelper.notExist("CountryCode")).withFieldName("countryCode").withAttempedValue(City::getCountryCode);

        ruleFor(City::getDistrict).must(not(stringEmptyOrNull())).withMessage(ErrorMessageHelper.emptyOrNull("District")).withFieldName("district").withAttempedValue(City::getDistrict)
                .must(stringSizeLessThanOrEqual(20)).when(not(stringEmptyOrNull())).withMessage(ErrorMessageHelper.sizeLessThanOrEqual("District", 20)).withFieldName("district").withAttempedValue(City::getDistrict);

        ruleFor(City::getPopulation)
                .must(ComparablePredicate.greaterThan(0)).withMessage(ErrorMessageHelper.nullOrZero("Population")).withFieldName("population").withAttempedValue(City::getPopulation);

    }

}