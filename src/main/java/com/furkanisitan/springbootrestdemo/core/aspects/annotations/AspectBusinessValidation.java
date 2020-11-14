package com.furkanisitan.springbootrestdemo.core.aspects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AspectBusinessValidation {

    /**
     * Returns the validator class to be used for validation.
     *
     * @return the validator class to be used for validation.
     */
    Class<? extends br.com.fluentvalidator.AbstractValidator<?>> value();
}