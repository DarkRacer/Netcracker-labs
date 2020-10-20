package com.nc.labs.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.Calendar;

@Setter
@Getter
public class СellularContract extends Contract{
    private int minutes;
    private int mbInternet;
    private int sms;

    public СellularContract(int id, Calendar startDate, Calendar endDate, Client client, int minutes, int mbInternet, int sms){
        super(id, startDate, endDate, client);
        this.minutes = minutes;
        this.mbInternet = mbInternet;
        this.sms = sms;
    }
}
