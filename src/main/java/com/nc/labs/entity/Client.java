package com.nc.labs.entity;

import com.nc.labs.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

/**
 * Class describes the client
 * @author Maksim Shcherbakov
 * @version 1.0
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    /**
     * Client identifier
     */
    private int id;

    /**
     * Client surname
     */
    private String surname;

    /**
     * Client first name
     */
    private String firstName;

    /**
     * Client patronymic
     */
    private String patronymic;

    /**
     * Client date of birth
     */
    private LocalDate dateOfBirth;

    /**
     * Client gender
     */
    private Gender gender;

    /**
     * Client passport number
     */
    private int numberPassport;

    /**
     * Client passport series
     */
    private int seriesPassport;

    /**
     * The method returns the client's age
     * @return client's current age
     */
    public int getAge() {
        LocalDate currentDate = LocalDate.now();

        return Period.between(this.dateOfBirth, currentDate).getYears();
    }
}
