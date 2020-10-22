package com.nc.labs.repository;

import com.nc.labs.entity.Contract;

public class Repository {
    private final int size = 10;
    private Contract[] arrayContract = new Contract[size];
    private int pointer = 0;

    public void add(Contract contract) {
        if (check(contract)) {
            if (pointer == (arrayContract.length - 1)) {
                resize(arrayContract.length + 5);
            }

            arrayContract[pointer] = contract;
            pointer++;
        }
        else {
            System.out.println("Контракт с таким id уже существует");
        }
    }

    private void resize(int newSize) {
        Contract[] array = new Contract[newSize];

        System.arraycopy(arrayContract, 0, array, 0, pointer);
        arrayContract = array;
    }

    public void delete(int id) {
        int index = pointer;

        for (int i = 0; i <= pointer; i++){
            if(arrayContract[i].getId() == id){
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
    }

    public Contract get(int id) {
        for (int i = 0; i <= pointer; i++){
            if(arrayContract[i].getId() == id){
                return arrayContract[i];
            }
        }

        return null;
    }

    public int getSize() {
        return pointer;
    }

    private boolean check (Contract contract){
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
}
