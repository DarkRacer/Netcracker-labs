package com.nc.labs.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class describes a cellular contract
 * @author Maksim Shcherbakov
 * @version 1.3
 */
@Setter
@Getter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
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
    public CellularContract(final int id, final LocalDate startDate, final LocalDate endDate, final int numberContract,
                            final Client client, final int minutes, final int gbInternet, final int sms) {
        super(id, startDate, endDate, numberContract, client);
        this.minutes = minutes;
        this.gbInternet = gbInternet;
        this.sms = sms;
    }

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
    public CellularContract(final int id, final String startDate, final String endDate, final int numberContract,
                            final Client client, final int minutes, final int gbInternet, final int sms) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        this.setId(id);
        this.setStartDate(start);
        this.setEndDate(end);
        this.setNumberContract(numberContract);
        this.setClient(client);
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
        return "Контракт сотовой связи " + "\n"
                + " id: " + getId() + "\n"
                + " Дата начала контракта: " + getStartDate() + "\n"
                + " Дата окончания контракта: " + getEndDate() + "\n"
                + " Номер контракта: " + getNumberContract() + "\n"
                + " Количество минут: " + minutes + "\n"
                + " SMS: " + sms + "\n"
                + " GB Internet: " + gbInternet + "\n"
                + " Владелец контракта \n" +  getClient().toString();
    }
}
