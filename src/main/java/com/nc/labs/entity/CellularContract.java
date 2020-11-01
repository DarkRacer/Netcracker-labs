package com.nc.labs.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Class describes a cellular contract
 * @author Maksim Shcherbakov
 * @version 1.2
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
    private int gbInternet;

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
     * @param gbInternet GB Internet under contract
     * @param sms SMS under contract
     */
    public CellularContract(int id, LocalDate startDate, LocalDate endDate, int numberContract, Client client, int minutes, int gbInternet, int sms) {
        super(id, startDate, endDate, numberContract, client);
        this.minutes = minutes;
        this.gbInternet = gbInternet;
        this.sms = sms;
    }

    /**
     * This method returns all information about the cellular contract
     * @return information about the cellular contract
     */
    @Override
    public String toString() {
        return "Контракт сотовой связи " + "\n" +
                " id: " + getId() + "\n" +
                " Дата начала контракта: " + getStartDate() + "\n" +
                " Дата окончания контракта: " + getEndDate() + "\n" +
                " Номер контракта: " + getNumberContract() + "\n" +
                " Количество минут: " + minutes + "\n" +
                " SMS: " + sms + "\n" +
                " GB Internet: " + gbInternet + "\n" +
                " Владелец контракта \n" +  getClient().toString();
    }
}
