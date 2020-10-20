package com.nc.labs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Calendar;

@Setter
@Getter
@AllArgsConstructor
public class Client {
    private int id;
    private String surname;
    private String firstName;
    private String lastName;
    private Calendar dateOfBirth;
    private String gender;
    private String numberPassport;
    private String seriesPassport;
}
