package com.nc.labs.validation;

import com.nc.labs.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This class describes the validation message
 * @author Maksim Shcherbakov
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
public class Message {
    /**
     * Validation message
     */
    private String message;

    /**
     * The validation status
     */
    private Status status;

    /**
     * Validation field
     */
    private String field;

    /**
     * The constructor of the message for a successful validation
     * @param status the validation status
     * @param field validation field
     */
    public Message(Status status, String field){
        this.status = status;
        this.field = field;
    }

    /**
     * This method returns all information about the message
     * @return information about the message
     */
    @Override
    public String toString() {
        return  "Status: " + status +
                " Message: " + message +
                " Field: " + field;
    }
}
