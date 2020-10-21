package com.nc.labs.entity;

import com.nc.labs.enums.Gender;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientTest {
    private Client client1;
    private Client client2;
    private Client client3;

    @Before
    public void setUp() {
        client1 = new Client(1, "Иванов", "Иван", "Иванович",
                LocalDate.of(1986, 2, 12), Gender.MALE, 2013, 892314);
        client2 = new Client(2, "Петров", "Пётр", "Петрович",
                LocalDate.of(1975, 4, 27), Gender.MALE, 2012, 853242);
        client3 = new Client(3, "Степанова", "Мария", "Степановна",
                LocalDate.of(1991, 7, 1), Gender.FEMALE, 2013, 832424);
    }

    @Test
    public void getAge() {
        List<Integer> actual = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();

        actual.add(34);
        actual.add(45);
        actual.add(29);

        expected.add(client1.getAge());
        expected.add(client2.getAge());
        expected.add(client3.getAge());

        Assert.assertEquals(expected, actual);
    }
}