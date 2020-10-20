package com.nc.labs.entity;

import com.nc.labs.enums.PackageChannel;
import lombok.Getter;
import lombok.Setter;
import java.util.Calendar;

@Setter
@Getter
public class TvContract extends Contract{
    private PackageChannel packageChannel;

    public TvContract(int id, Calendar startDate, Calendar endDate, Client client, PackageChannel packageChannel){
        super(id, startDate, endDate, client);
        this.packageChannel = packageChannel;
    }
}
