package com.nc.labs.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class describes a internet contract
 * @author Maksim Shcherbakov
 * @version 1.3
 */
@Setter
@Getter
public class InternetContract extends Contract {
    /**
     * The maximum speed on the contract
     */
    private int maximumSpeed;

    /**
     * The constructor creates a new internet contract object
     * @param id internet contract identifier
     * @param startDate internet contract start date
     * @param endDate internet contract end date
     * @param numberContract internet contract number
     * @param client internet contract client date
     * @param maximumSpeed the maximum speed on the contract
     */
    public InternetContract(final int id, final LocalDate startDate, final LocalDate endDate, final int numberContract,
                            final Client client, final int maximumSpeed) {
        super(id, startDate, endDate, numberContract, client);
        this.maximumSpeed = maximumSpeed;
    }

    /**
     * The constructor creates a new internet contract object
     * @param id internet contract identifier
     * @param startDate internet contract start date
     * @param endDate internet contract end date
     * @param numberContract internet contract number
     * @param client internet contract client date
     * @param maximumSpeed the maximum speed on the contract
     */
    public InternetContract(final int id, final String startDate, final String endDate, final int numberContract,
                            final Client client, final int maximumSpeed) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        this.setId(id);
        this.setStartDate(start);
        this.setEndDate(end);
        this.setNumberContract(numberContract);
        this.setClient(client);
        this.maximumSpeed = maximumSpeed;
    }

    /**
     * This method returns all information about the internet contract
     * @return information about the internet contract
     */
    @Override
    public String toString() {
        return "Контракт проводного интернета " + "\n"
                + " id: " + getId() + "\n"
                + " Дата начала контракта: " + getStartDate() + "\n"
                + " Дата окончания контракта: " + getEndDate() + "\n"
                + " Номер контракта: " + getNumberContract() + "\n"
                + " Максимальная скорость соединения: " + maximumSpeed + "\n"
                + " Владелец контракта \n" +  getClient().toString();
    }
}
