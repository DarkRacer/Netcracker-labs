package com.nc.labs.validation;

/**
 * Validator interface
 * @param <T> generic class for validation
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public interface Validator<T> {
    /**
     * The method does validation
     * @param objectForValidation object for validation
     * @return validation message
     */
    Message validate(T objectForValidation);

    /**
     * The method returns the class for which the validator is used
     * @return class for which the validator is used
     */
    Class<?> getClassValidation();
}
