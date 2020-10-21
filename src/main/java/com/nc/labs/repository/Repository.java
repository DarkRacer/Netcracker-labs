package com.nc.labs.repository;

public class Repository<T> {
    private final int size = 10;
    private Object[] arrayObjects = new Object[size];
    private int pointer = 0;

    public void add(T object) {
        if (pointer == (arrayObjects.length - 1)) {
            resize(arrayObjects.length + 5);
        }

        arrayObjects[pointer] = object;
        pointer++;
    }

    private void resize(int newSize) {
        Object[] array = new Object[newSize];

        System.arraycopy(arrayObjects, 0, array, 0, pointer);
        arrayObjects = array;
    }

    public void delete(int index) {
        if (index < arrayObjects.length) {
            arrayObjects[index] = null;
            pointer--;

            for (int i = index; i < pointer; i++) {
                Object buffer = arrayObjects[i];

                arrayObjects[i] = arrayObjects[i + 1];
                arrayObjects[i + 1] = buffer;
            }
        }
    }

    public T get(int index) {
        if (index < arrayObjects.length) {
            return (T) arrayObjects[index];
        }
        else {
            return null;
        }
    }

    public int getSize() {
        return pointer;
    }
}
