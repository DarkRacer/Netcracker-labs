package com.nc.labs.repository;

import com.nc.labs.entity.*;
import com.nc.labs.enums.Gender;
import com.nc.labs.enums.PackageChannel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The class checks the operation of methods of the Repository class
 * @author Maksim Shcherbakov
 * @version 1.2
 */
public class RepositoryTest {
    /**
     * Object of the Repository class
     */
    private final Repository contractRepository = new Repository();

    /**
     * The initial conditions for the tests
     */
    @Before
    public void setUp() {
        Client client1 = new Client(1, "Иванов", "Иван", "Иванович",
                LocalDate.of(1986, 2, 12), Gender.MALE, 2013, 892314);
        Client client2 = new Client(2, "Петров", "Пётр", "Петрович",
                LocalDate.of(1975, 4, 27), Gender.MALE, 2012, 853242);
        Client client3 = new Client(3, "Степанова", "Мария", "Степановна",
                LocalDate.of(1991, 7, 1), Gender.FEMALE, 2013, 832424);

        TvContract tvContract = new TvContract(1, LocalDate.of(2019, 11, 15),
                LocalDate.of(2020, 11, 15), 234364651,  client2, PackageChannel.PLUS);
        CellularContract cellularContract = new CellularContract(2, LocalDate.of(2020, 8, 1),
                LocalDate.of(2021, 8, 1), 123844533, client3, 1000, 51200, 100);
        InternetContract internetContract = new InternetContract(3, LocalDate.of(2019, 12, 25),
                LocalDate.of(2020, 12, 25), 562364324, client1, 300);

        contractRepository.add(tvContract);
        contractRepository.add(cellularContract);
        contractRepository.add(internetContract);
    }

    /**
     * This method tests whether the contract is added to the repository
     */
    @Test
    public void add() {
        Client client4 = new Client(4, "Сидоров", "Пётр", "Иванович",
                LocalDate.of(1982, 5, 19), Gender.MALE, 2014, 835621);
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = new ArrayList<>();

        for (int g = 1; g < 14; g++) {
            actual.add(g);
        }

        for (int i = 1; i < 14; i++) {
            contractRepository.add(new TvContract(i, LocalDate.of(2020, 9, 13),
                    LocalDate.of(2021, 9, 13), 747343453, client4, PackageChannel.PLUS));
        }

        for (int k = 1; k <= contractRepository.getSize(); k++) {
            expected.add(contractRepository.get(k).getId());
        }

        Assert.assertEquals(expected, actual);
    }

    /**
     * This method tests whether a contract can be deleted from the repository
     */
    @Test
    public void delete() {
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = new ArrayList<>();

        actual.add(2);
        actual.add(3);

        contractRepository.delete(1);

        expected.add(contractRepository.get(2).getId());
        expected.add(contractRepository.get(3).getId());

        Assert.assertEquals(expected, actual);
    }

    /**
     * This method tests if the search by contract ID is correct
     */
    @Test
    public void get() {
        List<Integer> actual = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();

        actual.add(1);
        actual.add(2);
        actual.add(3);

        expected.add(contractRepository.get(1).getId());
        expected.add(contractRepository.get(2).getId());
        expected.add(contractRepository.get(3).getId());

        Assert.assertEquals(expected, actual);
    }

    /**
     * This method tests whether the repository size is calculated correctly
     */
    @Test
    public void getSize() {
        int actual = 3;
        int expected = contractRepository.getSize();

        Assert.assertEquals(expected, actual);
    }

    /**
     * This method tests all functions of the Repository class
     */
    @Test
    public void testAllFunctions(){
        Client client4 = new Client(4, "Сидоров", "Пётр", "Иванович",
                LocalDate.of(1982, 5, 19), Gender.MALE, 2014, 835621);
        Client client5 = new Client(1, "Иванов", "Иван", "Иванович",
                LocalDate.of(1986, 2, 12), Gender.MALE, 2013, 892314);
        Client client6 = new Client(2, "Петров", "Пётр", "Петрович",
                LocalDate.of(1975, 4, 27), Gender.MALE, 2012, 853242);
        List<Integer> actual = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();

        actual.add(3);
        expected.add(contractRepository.getSize());

        contractRepository.add(new TvContract(4, LocalDate.of(2020, 6, 11),
                LocalDate.of(2021, 6, 11), 344752324, client4, PackageChannel.DEFAULT));
        contractRepository.add(new TvContract(5, LocalDate.of(2020, 1, 16),
                LocalDate.of(2021, 1, 16), 384539934, client5, PackageChannel.PLUS));
        contractRepository.add(new TvContract(6, LocalDate.of(2020, 8, 6),
                LocalDate.of(2021, 9, 13), 757324732, client6, PackageChannel.PLUSPLUS));

        contractRepository.add(new InternetContract(7, LocalDate.of(2020, 4, 9),
                LocalDate.of(2021, 4, 9), 647438438, client4, 400));
        contractRepository.add(new InternetContract(8, LocalDate.of(2020, 10, 13),
                LocalDate.of(2021, 10, 13), 531568352, client5, 500));
        contractRepository.add(new InternetContract(9, LocalDate.of(2020, 1, 3),
                LocalDate.of(2021, 1, 3), 825592452, client6, 600));

        contractRepository.add(new CellularContract(10, LocalDate.of(2020, 9, 3),
                LocalDate.of(2021, 9, 3), 747343453, client4, 1500, 46080, 150));
        contractRepository.add(new CellularContract(11, LocalDate.of(2020, 4, 28),
                LocalDate.of(2021, 4, 28), 747343453, client5, 900, 30720, 50));
        contractRepository.add(new CellularContract(12, LocalDate.of(2020, 4, 15),
                LocalDate.of(2021, 4, 15), 747343453, client6, 1200, 56320, 80));

        actual.add(12);
        expected.add(contractRepository.getSize());

        contractRepository.delete(10);

        actual.add(11);
        expected.add(contractRepository.getSize());

        actual.add(9);
        expected.add(contractRepository.get(9).getId());

        Assert.assertEquals(expected, actual);
    }
}