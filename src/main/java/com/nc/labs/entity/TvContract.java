package com.nc.labs.entity;

import com.nc.labs.enums.PackageChannel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Class describes a tv contract
 * @author Maksim Shcherbakov
 * @version 1.1
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
    public TvContract(int id, LocalDate startDate, LocalDate endDate, int numberContract, Client client, PackageChannel packageChannel) {
        super(id, startDate, endDate, numberContract, client);
        this.packageChannel = packageChannel;
    }
}
