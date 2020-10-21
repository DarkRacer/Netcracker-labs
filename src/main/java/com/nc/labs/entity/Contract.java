package com.nc.labs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public abstract class Contract {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Client client;
}
