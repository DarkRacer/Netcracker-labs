package com.nc.labs.entity;

import lombok.AllArgsConstructor;
import java.util.Calendar;

@AllArgsConstructor
public abstract class Contract {
    private int id;
    private Calendar startDate;
    private Calendar endDate;
    private Client client;
}
