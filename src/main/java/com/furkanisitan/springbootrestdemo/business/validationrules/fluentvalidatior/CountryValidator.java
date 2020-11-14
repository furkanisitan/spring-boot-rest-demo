package com.furkanisitan.springbootrestdemo.business.validationrules.fluentvalidatior;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.predicate.ComparablePredicate;
import com.furkanisitan.springbootrestdemo.core.helpers.EnumHelper;
import com.furkanisitan.springbootrestdemo.core.helpers.ErrorMessageHelper;
import com.furkanisitan.springbootrestdemo.entities.concrete.Country;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeLessThanOrEqual;

@Component
public class CountryValidator extends AbstractValidator<Country> {

    @Override
    public void rules() {

        ruleFor(Country::getCode)
                .must(not(stringEmptyOrNull())).withMessage(ErrorMessageHelper.emptyOrNull("Code")).withFieldName("code").withAttempedValue(Country::getCode)
                .must(stringSizeLessThanOrEqual(3)).when(not(stringEmptyOrNull())).withMessage(ErrorMessageHelper.sizeLessThanOrEqual("Code", 3)).withFieldName("code").withAttempedValue(Country::getCode);

        ruleFor(Country::getName)
                .must(not(stringEmptyOrNull())).withMessage(ErrorMessageHelper.emptyOrNull("Name")).withFieldName("name").withAttempedValue(Country::getName)
                .must(stringSizeLessThanOrEqual(52)).when(not(stringEmptyOrNull())).withMessage(ErrorMessageHelper.sizeLessThanOrEqual("Name", 52)).withFieldName("name").withAttempedValue(Country::getName);

        ruleFor(Country::getRegion)
                .must(not(stringEmptyOrNull())).withMessage(ErrorMessageHelper.emptyOrNull("Region")).withFieldName("region").withAttempedValue(Country::getRegion)
                .must(stringSizeLessThanOrEqual(26)).when(not(stringEmptyOrNull())).withMessage(ErrorMessageHelper.sizeLessThanOrEqual("Region", 26)).withFieldName("region").withAttempedValue(Country::getRegion);

        ruleFor(Country::getPopulation)
                .must(ComparablePredicate.greaterThan(0)).withMessage(ErrorMessageHelper.nullOrZero("Population")).withFieldName("population").withAttempedValue(Country::getPopulation);

        ruleFor(Country::getContinent)
                .must(EnumHelper::isContinentEnum).withMessage(ErrorMessageHelper.acceptedValue("Continent", EnumHelper.getContinentEnumValuesAsString())).withFieldName("continent").withAttempedValue(Country::getContinent);
    }

}
