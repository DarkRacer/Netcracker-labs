package com.nc.labs.sort;

import com.nc.labs.entity.Contract;

import java.util.Comparator;

/**
 * The interface describes how to sort
 * @param <T> This describes type parameter
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public interface ISorter<T> {
    /**
     * Method for sorting
     * @param contracts Array for sorting
     * @param comparator Sorting criterion
     */
    void sort(Contract[] contracts, Comparator<T> comparator);
}
