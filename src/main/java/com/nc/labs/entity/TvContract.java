package com.nc.labs.entity;

import com.nc.labs.enums.PackageChannel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Class describes a tv contract
 * @author Maksim Shcherbakov
 * @version 1.2
 */
@Setter
@Getter
public class TvContract extends Contract {
    /**
     * Contract channel package
     */
    private PackageChannel packageChannel;

    /**
     * The constructor creates a new tv contract object
     * @param id tv contract identifier
     * @param startDate tv contract start date
     * @param endDate tv contract end date
     * @param numberContract tv contract number
     * @param client tv contract client date
     * @param packageChannel contract channel package
     */
    public TvContract(final int id, final LocalDate startDate, final LocalDate endDate, final int numberContract,
                      final Client client, final PackageChannel packageChannel) {
        super(id, startDate, endDate, numberContract, client);
        this.packageChannel = packageChannel;
    }

    /**
     * This method returns all information about the tv contract
     * @return information about the tv contract
     */
    @Override
    public String toString() {
        return "Контракт цифрового телевидения " + "\n" + " id: " + getId() + "\n"
                + " Дата начала контракта: " + getStartDate() + "\n"
                + " Дата окончания контракта: " + getEndDate() + "\n"
                + " Номер контракта: " + getNumberContract() + "\n"
                + " Пакет каналов: " + packageChannel + "\n"
                + " Владелец контракта \n" +  getClient().toString();
    }
}
