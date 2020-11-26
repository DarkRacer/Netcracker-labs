package com.nc.labs.validation.contract;

import com.nc.labs.entity.InternetContract;
import com.nc.labs.enums.TypeStatus;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 * This class does internet contract validation
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class ValidatorContractInternet extends ValidatorContract{
    /**
     * Logger for information about verification
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * Verification status
     */
    private ContractStatus contractStatus;

    /**
     * The method does the verification of the internet contract
     * @param internetContract internet contract for verification
     * @return verification result
     */
    public ContractStatus check(InternetContract internetContract) {
        List<ContractStatus> contractStatuses = new ArrayList<>();

        contractStatuses.add(checkId(internetContract.getId()));
        contractStatuses.add(checkStartDate(internetContract.getStartDate()));
        contractStatuses.add(checkEndDate(internetContract.getStartDate(),internetContract.getEndDate()));
        contractStatuses.add(checkNumber(internetContract.getNumberContract()));
        contractStatuses.add(checkMaximumSpeed(internetContract.getMaximumSpeed()));

        for (ContractStatus contractStatus : contractStatuses){
            if (!contractStatus.getTypeStatus().equals(TypeStatus.OK)){
                increaseLine();
                return contractStatus;
            }
        }
        increaseLine();
        return new ContractStatus(TypeStatus.OK, getLine());
    }

    /**
     * This method checks the maximum speed
     * @param maximumSpeed maximum speed for checks
     * @return verification status
     */
    private ContractStatus checkMaximumSpeed(int maximumSpeed){
        if (maximumSpeed == 0){
            contractStatus = new ContractStatus(TypeStatus.ERROR, "This field must only contain numbers",
                    getLine(), "maximumSpeed");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else if (maximumSpeed < 0) {
            contractStatus = new ContractStatus(TypeStatus.ERROR, "This field must only contain positive numbers",
                    getLine(), "maximumSpeed");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else {
            contractStatus = new ContractStatus(TypeStatus.OK, "OK", getLine(), "maximumSpeed");

            loggerValidator.info(contractStatus);
            return contractStatus;
        }
    }
}
