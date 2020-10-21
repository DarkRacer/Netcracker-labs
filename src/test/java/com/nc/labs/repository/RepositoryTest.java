package com.nc.labs.repository;

import com.nc.labs.entity.Client;
import com.nc.labs.entity.TvContract;
import com.nc.labs.enums.Gender;
import com.nc.labs.enums.PackageChannel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositoryTest {
    private final Repository<TvContract> tvContractRepository = new Repository<>();

    @Before
    public void setUp() {
        Client client1 = new Client(1, "Иванов", "Иван", "Иванович",
                LocalDate.of(1986, 2, 12), Gender.MALE, 2013, 892314);
        Client client2 = new Client(2, "Петров", "Пётр", "Петрович",
                LocalDate.of(1975, 4, 27), Gender.MALE, 2012, 853242);
        Client client3 = new Client(3, "Степанова", "Мария", "Степановна",
                LocalDate.of(1991, 7, 1), Gender.FEMALE, 2013, 832424);

        TvContract tvContract1 = new TvContract(1, LocalDate.of(2019, 12, 25),
                LocalDate.of(2020, 12, 25), client1, PackageChannel.DEFAULT);
        TvContract tvContract2 = new TvContract(2, LocalDate.of(2019, 11, 15),
                LocalDate.of(2020, 11, 15), client2, PackageChannel.PLUS);
        TvContract tvContract3 = new TvContract(3, LocalDate.of(2020, 8, 1),
                LocalDate.of(2021, 8, 1), client3, PackageChannel.PLUSPLUS);

        tvContractRepository.add(tvContract1);
        tvContractRepository.add(tvContract2);
        tvContractRepository.add(tvContract3);
    }

    @Test
    public void add() {
        Client client4 = new Client(4, "Сидоров", "Пётр", "Иванович",
                LocalDate.of(1982, 5, 19), Gender.MALE, 2014, 835621);
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = new ArrayList<>();

        for (int g = 1; g < 14; g++) {
            actual.add(g);
        }

        for (int i = 4; i < 14; i++) {
            tvContractRepository.add(new TvContract(i, LocalDate.of(2020, 9, 13),
                    LocalDate.of(2021, 9, 13), client4, PackageChannel.PLUS));
        }

        for (int k = 0; k < tvContractRepository.getSize(); k++) {
            expected.add(tvContractRepository.get(k).getId());
        }

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = new ArrayList<>();

        actual.add(1);
        actual.add(3);

        tvContractRepository.delete(1);

        for (int k = 0; k < tvContractRepository.getSize(); k++) {
            expected.add(tvContractRepository.get(k).getId());
        }

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void get() {
        List<Integer> actual = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();

        actual.add(1);
        actual.add(2);
        actual.add(3);

        for (int k = 0; k < tvContractRepository.getSize(); k++) {
            expected.add(tvContractRepository.get(k).getId());
        }

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getSize() {
        int actual = 3;
        int expected = tvContractRepository.getSize();

        Assert.assertEquals(expected, actual);
    }
}