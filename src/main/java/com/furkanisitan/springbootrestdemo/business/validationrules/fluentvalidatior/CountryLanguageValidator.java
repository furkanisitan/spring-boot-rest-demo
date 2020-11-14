package com.furkanisitan.springbootrestdemo.business.validationrules.fluentvalidatior;

import br.com.fluentvalidator.AbstractValidator;
import com.furkanisitan.springbootrestdemo.core.helpers.EnumHelper;
import com.furkanisitan.springbootrestdemo.core.helpers.ErrorMessageHelper;
import com.furkanisitan.springbootrestdemo.entities.concrete.CountryLanguage;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

@Component
public class CountryLanguageValidator extends AbstractValidator<CountryLanguage> {

    private final CountryLanguageIdValidator countryLanguageIdValidator;

    public CountryLanguageValidator(CountryLanguageIdValidator countryLanguageIdValidator) {
        this.countryLanguageIdValidator = countryLanguageIdValidator;
    }

    @Override
    public void rules() {

        ruleFor(CountryLanguage::getCountryLanguageId)
                .must(not(nullValue())).withMessage(ErrorMessageHelper.emptyOrNull("CountryLanguageId")).withFieldName("countryLanguageId").withAttempedValue(CountryLanguage::getCountryLanguageId)
                .whenever(not(nullValue())).withValidator(countryLanguageIdValidator);

        ruleFor(CountryLanguage::getIsOfficial)
                .must(EnumHelper::isOfficialEnum).withMessage(ErrorMessageHelper.acceptedValue("IsOfficial", EnumHelper.getIsOfficialEnumValuesAsString())).withFieldName("isOfficial").withAttempedValue(CountryLanguage::getIsOfficial);

        ruleFor(CountryLanguage::getPercentage)
                .must(not(nullValue())).withMessage(ErrorMessageHelper.emptyOrNull("Percentage")).withFieldName("percentage").withAttempedValue(CountryLanguage::getPercentage);
    }

}
