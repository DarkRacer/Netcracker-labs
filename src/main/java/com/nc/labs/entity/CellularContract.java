package com.nc.labs.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class CellularContract extends Contract {
    private int minutes;
    private int mbInternet;
    private int sms;

    public CellularContract(int id, LocalDate startDate, LocalDate endDate, Client client, int minutes, int mbInternet, int sms) {
        super(id, startDate, endDate, client);
        this.minutes = minutes;
        this.mbInternet = mbInternet;
        this.sms = sms;
    }
}
