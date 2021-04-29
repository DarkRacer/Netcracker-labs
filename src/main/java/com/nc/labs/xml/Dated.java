package com.nc.labs.xml;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Class for marshal and unmarshal LocalDate
 * @author Maksim Shcherbakov
 * @version 1.0
 */
@XmlType
public class Dated {
    /**
     * Field for saving value
     */
    @XmlAttribute(name="value")
    public String value;
}
