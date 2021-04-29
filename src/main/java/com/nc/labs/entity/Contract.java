package com.nc.labs.entity;

import com.nc.labs.xml.DatedAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Class describes the contract
 * @author Maksim Shcherbakov
 * @version 1.1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
        CellularContract.class,
        InternetContract.class,
        TvContract.class})
public abstract class Contract {
    /**
     * Contract identifier
     */
    private int id;

    /**
     * Contract start date
     */
    @XmlJavaTypeAdapter(DatedAdapter.class)
    private LocalDate startDate;

    /**
     * Contract end date
     */
    @XmlJavaTypeAdapter(DatedAdapter.class)
    private LocalDate endDate;

    /**
     * Contract number
     */
    private int numberContract;

    /**
     * Contract client
     */
    private Client client;
}
