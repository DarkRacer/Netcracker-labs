package com.nc.labs.enums;

/**
 * Enumeration of types of type status
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public enum TypeStatus {
    /**
     * There is no error
     */
    OK,

    /**
     * Error was found
     */
    ERROR,

    /**
     * Potential error
     */
    RED_RISK,

    /**
     * Possible error
     */
    YELLOW_RISK
}
