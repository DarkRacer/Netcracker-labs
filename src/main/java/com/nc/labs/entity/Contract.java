package com.nc.labs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Class describes the contract
 * @author Maksim Shcherbakov
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Contract {
    /**
     * Contract identifier
     */
    private int id;

    /**
     * Contract start date
     */
    private LocalDate startDate;

    /**
     * Contract end date
     */
    private LocalDate endDate;

    /**
     * Contract client
     */
    private Client client;
}
