package com.nc.labs.converter;

import com.nc.labs.enums.TypeContract;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

/**
 * Converter for the contract type
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class TypeConverter extends AbstractBeanField {
    /**
     * This method converts a string to contract type
     * @param string parameter to convert
     * @return contract type or null
     * @throws CsvDataTypeMismatchException This exception should be thrown when the provided string value for
     * conversion cannot be converted to the required type of the destination field
     * @throws CsvConstraintViolationException Reports the result of constraint violations.
     */
    @Override
    protected Object convert(String string) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        for(TypeContract typeContract : TypeContract.values()){
            if(typeContract.name().equals(string)){
                return typeContract;
            }
        }
        return null;
    }
}
