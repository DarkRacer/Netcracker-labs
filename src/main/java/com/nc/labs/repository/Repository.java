package com.nc.labs.repository;

import com.nc.labs.entity.Contract;

public class Repository {
    private final int size = 10;
    private Contract[] arrayContract = new Contract[size];
    private int pointer = 0;

    public void add(Contract contract) {
            if (pointer == (arrayContract.length - 1)) {
                resize(arrayContract.length + 5);
            }

            contract.setId(pointer + 1);
            arrayContract[pointer] = contract;
            pointer++;
    }

    private void resize(int newSize) {
        Contract[] array = new Contract[newSize];

        System.arraycopy(arrayContract, 0, array, 0, pointer);
        arrayContract = array;
    }

    public void delete(int index) {
        if (index < arrayContract.length) {
            arrayContract[index] = null;
            pointer--;

            for (int i = index; i < pointer; i++) {
                Contract buffer = arrayContract[i];

                arrayContract[i] = arrayContract[i + 1];
                arrayContract[i + 1] = buffer;
            }
        }
    }

    public Contract get(int index) {
        if (index < arrayContract.length) {
            return arrayContract[index];
        }
        else {
            return null;
        }
    }

    public int getSize() {
        return pointer;
    }
}
