package com.nc.labs.validation.contract;

import com.nc.labs.entity.CellularContract;
import com.nc.labs.enums.TypeStatus;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * This class does cellular contract validation
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class ValidatorContractCellular extends ValidatorContract {
    /**
     * Logger for information about verification
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * Verification status
     */
    private ContractStatus contractStatus;

    /**
     * The method does the verification of the cellular contract
     * @param cellularContract cellular contract for verification
     * @return verification result
     */
    public ContractStatus check(CellularContract cellularContract) {
        List<ContractStatus> contractStatuses = new ArrayList<>();

        contractStatuses.add(checkId(cellularContract.getId()));
        contractStatuses.add(checkStartDate(cellularContract.getStartDate()));
        contractStatuses.add(checkEndDate(cellularContract.getStartDate(),cellularContract.getEndDate()));
        contractStatuses.add(checkNumber(cellularContract.getNumberContract()));
        contractStatuses.add(checkMinute(cellularContract.getMinutes()));
        contractStatuses.add(checkSms(cellularContract.getSms()));
        contractStatuses.add(checkGbInternet(cellularContract.getGbInternet()));

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
     * This method checks the minute
     * @param minute minute for checks
     * @return verification status
     */
    private ContractStatus checkMinute(int minute){
        if (minute == 0){
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "This field must only contain numbers", getLine(), "minute");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else if (minute < 0) {
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "This field must only contain positive numbers", getLine(), "minute");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else {
            contractStatus = new ContractStatus(TypeStatus.OK, "OK", getLine(), "minute");

            loggerValidator.info(contractStatus);
            return contractStatus;
        }
    }

    /**
     * This method checks the sms
     * @param sms sms for checks
     * @return verification status
     */
    private ContractStatus checkSms(int sms){
        if (sms == 0){
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "This field must only contain numbers", getLine(), "sms");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else if (sms < 0) {
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "This field must only contain positive numbers", getLine(), "sms");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else {
            contractStatus = new ContractStatus(TypeStatus.OK, "OK", getLine(), "sms");

            loggerValidator.info(contractStatus);
            return contractStatus;
        }
    }

    /**
     * This method checks the gb internet
     * @param gbInternet gb internet for checks
     * @return verification status
     */
    private ContractStatus checkGbInternet(int gbInternet){
        if (gbInternet == 0){
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "This field must only contain numbers", getLine(), "gbInternet");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else if (gbInternet < 0) {
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "This field must only contain positive numbers", getLine(), "gbInternet");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else {
            contractStatus = new ContractStatus(TypeStatus.OK, "OK", getLine(), "gbInternet");

            loggerValidator.info(contractStatus);
            return contractStatus;
        }
    }

}
