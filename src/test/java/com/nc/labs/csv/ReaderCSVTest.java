package com.nc.labs.csv;

import com.nc.labs.di.Injector;
import com.nc.labs.entity.*;
import com.nc.labs.repository.Repository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * The class checks the operation of methods of the ReaderCSV class
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class ReaderCSVTest {
    private Injector injector = new Injector();
    /**
     * Object of the Repository class
     */
    private Repository<Contract> contractRepository;

    /**
     * The initial conditions for the tests
     */
    @Before
    public void setUp() {
        try {
            ReaderCSV readerCSV = injector.inject(new ReaderCSV());
            contractRepository = injector.inject(new Repository<>());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method checks whether the parsing is correct
     */
    @Test
    public void read() {
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = new ArrayList<>();
        ReaderCSV readerCSV = new ReaderCSV();

        actual.add(8);
        actual.add(1);
        actual.add(2);
        actual.add(3);
        actual.add(4);
        actual.add(5);
        actual.add(6);
        actual.add(9);
        actual.add(10);

        readerCSV.read(contractRepository, "src\\main\\resources\\contracts.csv");

        expected.add(contractRepository.getSize());
        expected.add(contractRepository.get(1).getId());
        expected.add(contractRepository.get(2).getId());
        expected.add(contractRepository.get(3).getId());
        expected.add(contractRepository.get(4).getId());
        expected.add(contractRepository.get(5).getId());
        expected.add(contractRepository.get(6).getId());
        expected.add(contractRepository.get(9).getId());
        expected.add(contractRepository.get(10).getId());


        Assert.assertEquals(expected, actual);
    }
}