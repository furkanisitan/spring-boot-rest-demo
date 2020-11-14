package com.furkanisitan.springbootrestdemo.core.aspects.annotations;

import com.furkanisitan.springbootrestdemo.core.business.IEntityAspectService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AspectEntityExist {

    /**
     * Returns the service class that checks if the entity exists.
     *
     * @return the service class that checks if the entity exists.
     */
    Class<? extends IEntityAspectService> service();

    /**
     * Returns the DbOperation enum. (CREATE, READ, UPDATE)
     *
     * @return the DbOperation enum.
     */
    DbOperation dbOperation();

    enum DbOperation {CREATE, READ, UPDATE}
}
