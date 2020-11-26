package com.nc.labs.validation.contract;

import com.nc.labs.enums.TypeStatus;
import lombok.Getter;
import org.apache.log4j.Logger;
import java.time.LocalDate;

/**
 * This class does contract validation
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class ValidatorContract {
    /**
     * Verification line
     */
    @Getter
    private static int line = 1;

    /**
     * Logger for information about verification
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * Verification status
     */
    private ContractStatus contractStatus;

    /**
     * This method checks the id contract
     * @param id id contract for checks
     * @return verification status
     */
    protected ContractStatus checkId(int id){
        if (id == 0){
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "This field must only contain numbers", line, "id");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else if (id < 0) {
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "This field must only contain positive numbers", line, "id");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else{
            contractStatus = new ContractStatus(TypeStatus.OK, "OK", line, "id");

            loggerValidator.info(contractStatus);
            return contractStatus;
        }
    }

    /**
     * This method checks the start date contract
     * @param startDate start date contract for checks
     * @return verification status
     */
    protected ContractStatus checkStartDate(LocalDate startDate){
        if (startDate == null){
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "This field must contain only the date(DD.MM. YYYY)", line, "startDate");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else if(startDate.isAfter(LocalDate.now()) || startDate.equals(LocalDate.now())){
            contractStatus = new ContractStatus(TypeStatus.RED_RISK,
                    "The date cannot be later than the date that is today", line, "startDate");

            loggerValidator.warn(contractStatus);
            return contractStatus;
        }
        else {
            contractStatus = new ContractStatus(TypeStatus.OK, "OK", line, "startDate");

            loggerValidator.info(contractStatus);
            return contractStatus;
        }
    }

    /**
     * This method checks the end date contract
     * @param startDate start date contract for checks
     * @param endDate end date contract for checks
     * @return verification status
     */
    protected ContractStatus checkEndDate(LocalDate startDate, LocalDate endDate){
        if (endDate == null){
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "This field must contain only the date(DD.MM. YYYY)", line, "endDate");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else if(startDate.isAfter(endDate)){
            contractStatus = new ContractStatus(TypeStatus.RED_RISK,
                    "The date cannot be later than the contract start date", line, "endDate");

            loggerValidator.warn(contractStatus);
            return contractStatus;
        }
        else if(endDate.equals(startDate)){
            contractStatus = new ContractStatus(TypeStatus.YELLOW_RISK,
                    "The end date is equal to the start date", line, "endDate");

            loggerValidator.warn(contractStatus);
            return contractStatus;
        }
        else {
            contractStatus = new ContractStatus(TypeStatus.OK, "OK", line, "endDate");

            loggerValidator.info(contractStatus);
            return contractStatus;
        }
    }

    /**
     * This method checks the number contract
     * @param numberContract number contract for checks
     * @return verification status
     */
    protected ContractStatus checkNumber(int numberContract){
        if (numberContract == 0){
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "This field must only contain numbers", line, "numberContract");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else if (numberContract < 0) {
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "This field must only contain positive numbers", line, "numberContract");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else {
            contractStatus = new ContractStatus(TypeStatus.OK, "OK", line, "numberContract");

            loggerValidator.info(contractStatus);
            return contractStatus;
        }
    }

    /**
     * This method increases the line counter
     * @return new value of the counter
     */
    protected int increaseLine(){
        line += 1;
        return line;
    }

    /**
     * This method clear the line counter
     */
    public static void clearLine(){
        line = 0;
    }
}
