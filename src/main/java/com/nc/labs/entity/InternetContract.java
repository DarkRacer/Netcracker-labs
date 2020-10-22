package com.nc.labs.entity;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Class describes a internet contract
 * @author Maksim Shcherbakov
 * @version 1.0
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
     * @param client internet contract client date
     * @param maximumSpeed the maximum speed on the contract
     */
    public InternetContract(int id, LocalDate startDate, LocalDate endDate, Client client, int maximumSpeed) {
        super(id, startDate, endDate, client);
        this.maximumSpeed = maximumSpeed;
    }
}
