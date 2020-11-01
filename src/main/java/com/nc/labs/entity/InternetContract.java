package com.nc.labs.entity;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Class describes a internet contract
 * @author Maksim Shcherbakov
 * @version 1.2
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
    public InternetContract(int id, LocalDate startDate, LocalDate endDate, int numberContract, Client client, int maximumSpeed) {
        super(id, startDate, endDate, numberContract, client);
        this.maximumSpeed = maximumSpeed;
    }

    /**
     * This method returns all information about the internet contract
     * @return information about the internet contract
     */
    @Override
    public String toString() {
        return "Контракт проводного интернета " + "\n" +
                " id: " + getId() + "\n" +
                " Дата начала контракта: " + getStartDate() + "\n" +
                " Дата окончания контракта: " + getEndDate() + "\n" +
                " Номер контракта: " + getNumberContract() + "\n" +
                " Максимальная скорость соединения: " + maximumSpeed + "\n" +
                " Владелец контракта \n" +  getClient().toString();
    }
}
