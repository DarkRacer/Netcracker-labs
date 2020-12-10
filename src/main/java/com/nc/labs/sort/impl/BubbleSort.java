package com.nc.labs.sort.impl;

import com.nc.labs.entity.Contract;
import com.nc.labs.sort.ISorter;

import java.util.Comparator;

/**
 * This class describes bubble sorting
 * @param <T> This describes type parameter
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class BubbleSort<T> implements ISorter<T> {

    /**
     * Override method for sorting by bubble
     * @param contracts Array for sorting
     * @param comparator Sorting criterion
     */
    @Override
    public void sort(final Contract[] contracts, final Comparator<T> comparator) {
        for (int i = 1; i < contracts.length && contracts[i] != null; i++) {
            for (int j = 0; j < contracts.length && contracts[j] != null; j++) {
                if (comparator.compare((T) contracts[i], (T) contracts[j]) < 0) {
                    Contract contract = contracts[i];
                    contracts[i] = contracts[j];
                    contracts[j] = contract;
                }
            }
        }
    }

}
