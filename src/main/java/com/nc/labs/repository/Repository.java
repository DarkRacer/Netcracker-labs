package com.nc.labs.repository;

import com.nc.labs.di.Inject;
import com.nc.labs.entity.Contract;
import com.nc.labs.sort.ISorter;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.function.Predicate;

/**
 * This class describes a repository for storing various contracts
 * @param <T> This describes type parameter
 * @author Maksim Shcherbakov
 * @version 1.2
 */
@NoArgsConstructor
public class Repository<T> {
    /**
     * The initial size of the array
     */
    private final int size = 10;

    /**
     * Array for storing contracts
     */
    private Contract[] arrayContract = new Contract[size];

    /**
     * Number of contracts added
     */
    private int pointer = 0;

    /**
     * Sorting algorithm
     */
    @Inject
    private ISorter sorter;

    /**
     * The method adds a contract to the repository
     * @param contract contract to add
     */
    public void add(final Contract contract) {
        if (check(contract)) {
            if (pointer == (arrayContract.length - 1)) {
                resize(arrayContract.length + 5);
            }

            arrayContract[pointer] = contract;
            pointer++;
        } else {
            System.out.println("Контракт с таким id уже существует");
        }
    }

    /**
     * The method expands the repository
     * @param newSize the new array size
     */
    private void resize(final int newSize) {
        Contract[] array = new Contract[newSize];

        System.arraycopy(arrayContract, 0, array, 0, pointer);
        arrayContract = array;
    }

    /**
     * This method deletes the contract from the repository
     * @param id Contract identifier
     */
    public void delete(final int id) {
        int index = pointer;

        for (int i = 0; i <= pointer; i++) {
            if (arrayContract[i].getId() == id) {
                arrayContract[i] = null;
                index = i;
                break;
            }
        }

        for (int i = index; i < pointer; i++) {
            Contract buffer = arrayContract[i];

            arrayContract[i] = arrayContract[i + 1];
            arrayContract[i + 1] = buffer;
        }

        pointer--;
    }

    /**
     * This method returns the contract from the repository
     * @param id contract identifier
     * @return repository element or null
     */
    public Contract get(final int id) {
        for (int i = 0; i <= pointer; i++) {
            if (arrayContract[i] != null && arrayContract[i].getId() == id) {
                return arrayContract[i];
            }
        }

        return null;
    }

    /**
     * This method returns the repository size
     * @return repository size
     */
    public int getSize() {
        return pointer;
    }

    /**
     * This method checks whether the contract can be added to the repository
     * @param contract contract to add
     * @return check result
     */
    private boolean check(final Contract contract) {
        if (pointer != 0) {
            for (Contract contract1 : arrayContract) {
                if (contract1 != null && contract != null) {
                    if (contract1.getId() == contract.getId()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * This method search for contacts by criteria
     * @param criterion search criterion
     * @return found contracts
     */
    public Contract[] search(final Predicate<T> criterion) {
        Contract[] array = new Contract[arrayContract.length];
        int g = 0;

        for (Contract contract : arrayContract) {
            if (contract != null) {
                if (criterion.test((T) contract)) {
                    array[g] = contract;
                    g++;
                }
            }
        }

        if (g == 0) {
            return null;
        } else {
            return array;
        }
    }

    /**
     * This method sorts by criteria
     * @param comparator sorting criterion
     */
    public void sort(final Comparator<T> comparator) {
        sorter.sort(arrayContract, comparator);
    }
}
