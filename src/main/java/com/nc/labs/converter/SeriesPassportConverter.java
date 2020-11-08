package com.nc.labs.converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

/**
 * Converter to series passport
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class SeriesPassportConverter extends AbstractBeanField {
    /**
     * This method converts a string to series passport
     * @param string parameter to convert
     * @return series passport or null
     * @throws CsvDataTypeMismatchException This exception should be thrown when the provided string value for
     * conversion cannot be converted to the required type of the destination field
     * @throws CsvConstraintViolationException Reports the result of constraint violations.
     */
    @Override
    protected Object convert(String string) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (string.matches("\\d{4}")) {
            int parse = Integer.parseInt(string);
            return parse;
        }
        return null;
    }
}