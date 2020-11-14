package com.furkanisitan.springbootrestdemo.core.exceptions;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.fluentvalidator.exception.ValidationException;
import lombok.Getter;
import lombok.Setter;

public class FluentValidationException extends ValidationException {

    @Getter @Setter
    private String entityName;

    public FluentValidationException(String entityName, ValidationResult validationResult) {
        super(validationResult);
        this.entityName = entityName;
    }
}
