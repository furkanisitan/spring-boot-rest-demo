package com.furkanisitan.springbootrestdemo.business.validationrules.fluentvalidatior;

import br.com.fluentvalidator.AbstractValidator;
import com.furkanisitan.springbootrestdemo.business._abstract.ICountryService;
import com.furkanisitan.springbootrestdemo.core.helpers.ErrorMessageHelper;
import com.furkanisitan.springbootrestdemo.entities.complextypes.CountryLanguageId;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeLessThanOrEqual;

@Component
class CountryLanguageIdValidator extends AbstractValidator<CountryLanguageId> {

    private final ICountryService countryService;

    public CountryLanguageIdValidator(ICountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void rules() {

        ruleFor(CountryLanguageId::getCountryCode)
                .must(countryService::existById).when(not(stringEmptyOrNull())).withMessage(ErrorMessageHelper.notExist("CountryCode")).withFieldName("countryCode").withAttempedValue(CountryLanguageId::getCountryCode);

        ruleFor(CountryLanguageId::getLanguage)
                .must(not(stringEmptyOrNull())).withMessage(ErrorMessageHelper.emptyOrNull("Language")).withFieldName("language").withAttempedValue(CountryLanguageId::getLanguage)
                .must(stringSizeLessThanOrEqual(30)).when(not(stringEmptyOrNull())).withMessage(ErrorMessageHelper.sizeLessThanOrEqual("Language", 30)).withFieldName("language").withAttempedValue(CountryLanguageId::getLanguage);
    }
}
