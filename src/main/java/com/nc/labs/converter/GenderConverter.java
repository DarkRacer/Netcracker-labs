package com.nc.labs.converter;

import com.nc.labs.enums.Gender;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

/**
 * Converter to gender
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class GenderConverter extends AbstractBeanField {
    /**
     * This method converts a string to gender
     * @param string parameter to convert
     * @return gender or null
     * @throws CsvDataTypeMismatchException This exception should be thrown when the provided string value for
     * conversion cannot be converted to the required type of the destination field
     * @throws CsvConstraintViolationException Reports the result of constraint violations.
     */
    @Override
    protected Object convert(String string) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        for(Gender gender : Gender.values()){
            if(gender.name().equals(string)){
                return gender;
            }
        }
        return null;
    }
}
