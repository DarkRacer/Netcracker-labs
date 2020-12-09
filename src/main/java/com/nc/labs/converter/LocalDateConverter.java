package com.nc.labs.converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Converter to LocalDate
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class LocalDateConverter extends AbstractBeanField {
    /**
     * This method converts a string to LocalDate
     * @param string parameter to convert
     * @return LocalDate or null
     * @throws CsvDataTypeMismatchException This exception should be thrown when the provided string value for
     * conversion cannot be converted to the required type of the destination field
     * @throws CsvConstraintViolationException Reports the result of constraint violations.
     */
    @Override
    protected Object convert(final String string) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (string.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate parse = LocalDate.parse(string, formatter);
            return parse;
        }
        return null;
    }
}
