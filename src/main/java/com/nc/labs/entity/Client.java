package com.nc.labs.entity;

import com.nc.labs.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Setter
@Getter
@AllArgsConstructor
public class Client {
    private int id;
    private String surname;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private int numberPassport;
    private int seriesPassport;

    public int getAge() {
        LocalDate currentDate = LocalDate.now();

        return Period.between(this.dateOfBirth, currentDate).getYears();
    }
}
