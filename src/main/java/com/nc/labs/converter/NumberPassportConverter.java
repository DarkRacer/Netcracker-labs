package com.nc.labs.converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

/**
 * Converter to number passport
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class NumberPassportConverter extends AbstractBeanField {
    /**
     * This method converts a string to number passport
     * @param string parameter to convert
     * @return number passport or null
     * @throws CsvDataTypeMismatchException This exception should be thrown when the provided string value for
     * conversion cannot be converted to the required type of the destination field
     * @throws CsvConstraintViolationException Reports the result of constraint violations.
     */
    @Override
    protected Object convert(String string) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (string.matches("\\d{6}")) {
            int parse = Integer.parseInt(string);
            return parse;
        }
        return null;
    }
}
