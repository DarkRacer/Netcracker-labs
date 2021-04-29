package com.nc.labs.entity;

import com.nc.labs.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Class describes the client
 * @author Maksim Shcherbakov
 * @version 1.2
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

    /**
     * This method returns all information about the client
     * @return information about the client
     */
    @Override
    public String toString() {
        return  "   id: " + id + "\n"
                + "   Фамилия: " + surname + "\n"
                + "   Имя: " + firstName + "\n"
                + "   Отчество: " + patronymic + "\n"
                + "   Дата рождения: " + dateOfBirth + "\n"
                + "   Пол: " + gender + "\n"
                + "   Номер паспорта: " + numberPassport + "\n"
                + "   Серия паспорта: " + seriesPassport;
    }

    /**
     * The constructor creates a new client object
     * @param id client identifier
     * @param surname client surname
     * @param firstName client first name
     * @param patronymic client patronymic
     * @param dateOfBirth client date of birth
     * @param gender client gender
     * @param numberPassport client passport number
     * @param seriesPassport client passport series
     */
    public Client(final int id, final String surname, final String firstName, final String patronymic,
                  final String dateOfBirth, final String gender,
                  final int numberPassport, final int seriesPassport) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.patronymic = patronymic;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);

        for (Gender gen : Gender.values()) {
            if (gen.toString().equals(gender)) {
                this.gender = gen;
            }
        }

        this.numberPassport = numberPassport;
        this.seriesPassport = seriesPassport;
    }
}
