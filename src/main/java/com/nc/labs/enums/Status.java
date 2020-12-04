package com.nc.labs.enums;

/**
 * Enumeration of types status
 * @author Maksim Shcherbakov
 * @version 1.1
 */
public enum Status {
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
