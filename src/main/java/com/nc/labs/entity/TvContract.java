package com.nc.labs.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.Calendar;

@Setter
@Getter
public class TvContract extends Contract{
    private String packageChannel;

    public TvContract(int id, Calendar startDate, Calendar endDate, Client client, String packageChannel){
        super(id, startDate, endDate, client);
        this.packageChannel = packageChannel;
    }
}
