package com.nc.labs.csv;

import com.nc.labs.converter.*;
import com.nc.labs.enums.Gender;
import com.nc.labs.enums.TypeContract;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * CSV file template
 * @author Maksim Shcherbakov
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractCSV {
    /**
     * Contract identifier
     */
    @CsvCustomBindByPosition(position = 0, converter = IntConverter.class)
    private int id;

    /**
     * Contract start date
     */
    @CsvCustomBindByPosition(position = 1, converter = LocalDateConverter.class)
    private LocalDate startDate;

    /**
     * Contract end date
     */
    @CsvCustomBindByPosition(position = 2, converter = LocalDateConverter.class)
    private LocalDate endDate;

    /**
     * Contract number
     */
    @CsvCustomBindByPosition(position = 3, converter = IntConverter.class)
    private int numberContract;

    /**
     * Client identifier
     */
    @CsvCustomBindByPosition(position = 4, converter = IntConverter.class)
    private int idClient;

    /**
     * Client surname
     */
    @CsvBindByPosition(position = 5)
    private String surname;

    /**
     * Client first name
     */
    @CsvBindByPosition(position = 6)
    private String firstName;

    /**
     * Client patronymic
     */
    @CsvBindByPosition(position = 7)
    private String patronymic;

    /**
     * Client date of birth
     */
    @CsvCustomBindByPosition(position = 8, converter = LocalDateConverter.class)
    private LocalDate dateOfBirth;

    /**
     * Client gender
     */
    @CsvCustomBindByPosition(position = 9, converter = GenderConverter.class)
    private Gender gender;

    /**
     * Client passport number
     */
    @CsvCustomBindByPosition(position = 11, converter = NumberPassportConverter.class)
    private int numberPassport;

    /**
     * Client passport series
     */
    @CsvCustomBindByPosition(position = 10, converter = SeriesPassportConverter.class)
    private int seriesPassport;

    /**
     * Type contact
     */
    @CsvCustomBindByPosition(position = 12, converter = TypeConverter.class)
    private TypeContract type;

    /**
     * Additional information about the contract
     */
    @CsvBindByPosition(position = 13)
    private String addInfo;

    /**
     * This method checks whether the data was converted correctly
     * @return true - correct or false - not correct
     */
    public boolean check() {
        return this.id != 0 && this.startDate != null && this.endDate != null
                && this.numberContract != 0 && this.idClient != 0
                && this.surname != null && this.firstName != null
                && this.dateOfBirth != null && this.gender != null
                && this.numberPassport != 0 && this.seriesPassport != 0
                && this.type != null && this.addInfo != null;
    }
}
