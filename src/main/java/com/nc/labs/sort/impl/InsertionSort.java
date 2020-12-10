package com.nc.labs.sort.impl;

import com.nc.labs.entity.Contract;
import com.nc.labs.sort.ISorter;

import java.util.Comparator;

/**
 * This class describes insertion sorting
 * @param <T> This describes type parameter
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class InsertionSort<T> implements ISorter<T> {

    /**
     * Override method for sorting by insertion
     * @param contracts Array for sorting
     * @param comparator Sorting criterion
     */
    @Override
    public void sort(final Contract[] contracts, final Comparator<T> comparator) {
        for (int i = 0; i < contracts.length && contracts[i] != null; i++) {
            Contract value = contracts[i];
            int j = i - 1;
            for (; j >= 0 && contracts[j] != null; j--) {
                if (comparator.compare((T) contracts[i], (T) contracts[j]) < 0) {
                    contracts[j + 1] = contracts[j];
                } else {
                    break;
                }
            }
            contracts[j + 1] = value;
        }
    }
}
