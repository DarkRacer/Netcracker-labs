package com.nc.labs.validation.contract;

import com.nc.labs.enums.TypeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class describes the validity status of the contract
 * @author Maksim Shcherbakov
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public class ContractStatus {
    /**
     * Type status
     */
    private TypeStatus typeStatus;

    /**
     * Status message
     */
    private String message;

    /**
     * Verification line
     */
    private int line;

    /**
     * Verification field
     */
    private String field;

    /**
     * Contract status constructor
     * @param typeStatus type status
     * @param line The line where the check was performed
     */
    public ContractStatus(TypeStatus typeStatus, int line){
        this.typeStatus = typeStatus;
        this.line = line;
    }

    /**
     * The method checks the error status
     * @param contractStatus the status of the contract
     * @return true - no error; false - there is an error
     */
    public static boolean checkStatus(ContractStatus contractStatus){
        return !contractStatus.getTypeStatus().equals(TypeStatus.ERROR);
    }

    /**
     * This method returns all information about the contract status
     * @return information about the contract status
    */
    @Override
    public String toString() {
        return  "Type: " + typeStatus +
                " Message: " + message +
                " Line: " + line +
                " Field: " + field;
    }
}
