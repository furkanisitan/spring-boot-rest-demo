package com.furkanisitan.springbootrestdemo.core.aspects;

import com.furkanisitan.springbootrestdemo.core.aspects.annotations.AspectBusinessValidation;
import com.furkanisitan.springbootrestdemo.core.aspects.helpers.GenericHelper;
import com.furkanisitan.springbootrestdemo.core.crosscuttingconcerns.validation.fluentvalidator.ValidatorTool;
import com.furkanisitan.springbootrestdemo.core.entities.IEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

@Aspect
@Component
public class BusinessValidationAspect {

    private final BeanFactory beanFactory;

    public BusinessValidationAspect(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Before("@annotation(aspectBusinessValidation)")
    public void validate(JoinPoint joinPoint, AspectBusinessValidation aspectBusinessValidation) throws Throwable {

        // Get the entity class from the generic base class of the validator class.
        var entityClass = GenericHelper.getActualTypeArgumentClass(aspectBusinessValidation.value().getGenericSuperclass());

        var entities = Arrays.stream(joinPoint.getArgs()).filter(x -> x.getClass() == entityClass).toArray();

        // Get object of the validator class.
        var validator = beanFactory.getBean(aspectBusinessValidation.value());

        // Get fluentValidate method at runtime.
        var validateMethod = ValidatorTool.class.getMethod("fluentValidate", br.com.fluentvalidator.AbstractValidator.class, IEntity.class);

        // Throws an 'InvocationTargetException' when there is an error in methods that are executed by reflection.
        // It must be wrapped with try-catch for get the real exception.
        try {
            for (var entity : entities)
                validateMethod.invoke(null, validator, entity);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }
}

