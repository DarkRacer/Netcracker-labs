package com.nc.labs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@AllArgsConstructor
@Getter
@Setter
public abstract class Contract {
    private int id;
    private Calendar startDate;
    private Calendar endDate;
    private Client client;
}
