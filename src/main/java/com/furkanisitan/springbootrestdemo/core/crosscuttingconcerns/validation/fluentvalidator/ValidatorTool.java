package com.furkanisitan.springbootrestdemo.core.crosscuttingconcerns.validation.fluentvalidator;

import br.com.fluentvalidator.AbstractValidator;
import com.furkanisitan.springbootrestdemo.core.exceptions.FluentValidationException;
import com.furkanisitan.springbootrestdemo.core.entities.IEntity;

public class ValidatorTool {

    public static <T extends IEntity> void fluentValidate(AbstractValidator<T> validator, T entity) {

        var result = validator.validate(entity);

        if (!result.isValid())
            throw new FluentValidationException(entity.getClass().getSimpleName(), result);
    }
}
