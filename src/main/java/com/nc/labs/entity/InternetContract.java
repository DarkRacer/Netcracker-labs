package com.nc.labs.entity;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Setter
@Getter
public class InternetContract extends Contract {
    private int maximumSpeed;

    public InternetContract(int id, LocalDate startDate, LocalDate endDate, Client client, int maximumSpeed) {
        super(id, startDate, endDate, client);
        this.maximumSpeed = maximumSpeed;
    }
}
