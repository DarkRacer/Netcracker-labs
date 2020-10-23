package com.nc.labs.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Class describes a cellular contract
 * @author Maksim Shcherbakov
 * @version 1.1
 */
@Setter
@Getter
public class CellularContract extends Contract {
    /**
     * Minutes under contract
     */
    private int minutes;

    /**
     * MB Internet under contract
     */
    private int mbInternet;

    /**
     * SMS under contract
     */
    private int sms;

    /**
     * The constructor creates a new cellular contract object
     * @param id cellular contract identifier
     * @param startDate cellular contract start date
     * @param endDate cellular contract end date
     * @param numberContract cellular contract number
     * @param client cellular contract client date
     * @param minutes Minutes under contract
     * @param mbInternet MB Internet under contract
     * @param sms SMS under contract
     */
    public CellularContract(int id, LocalDate startDate, LocalDate endDate, int numberContract, Client client, int minutes, int mbInternet, int sms) {
        super(id, startDate, endDate, numberContract, client);
        this.minutes = minutes;
        this.mbInternet = mbInternet;
        this.sms = sms;
    }
}
