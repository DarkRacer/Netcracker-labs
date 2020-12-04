package com.nc.labs.validation.contract;

import com.nc.labs.entity.Contract;
import com.nc.labs.enums.Status;
import com.nc.labs.validation.Message;
import com.nc.labs.validation.Validator;
import org.apache.log4j.Logger;

import java.time.LocalDate;

/**
 * The class validates the start date
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class StartDateValidator implements Validator<Contract> {
    /**
     * Logger for the validator
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * The method does validation the start date
     * @param objectForValidation object for validation
     * @return validation message
     */
    @Override
    public Message validate(Contract objectForValidation) {
        if (objectForValidation.getStartDate() == null){
            loggerValidator.error(new Message("This field must contain only the date(DD.MM. YYYY)",
                    Status.ERROR, "startDate"));

            return new Message("This field must contain only the date(DD.MM. YYYY)",
                    Status.ERROR, "startDate");
        }
        else if(objectForValidation.getStartDate().isAfter(LocalDate.now())
                || objectForValidation.getStartDate().equals(LocalDate.now())){
            loggerValidator.warn(new Message("The date cannot be later than the date that is today",
                    Status.RED_RISK, "startDate"));

            return new Message("The date cannot be later than the date that is today",
                    Status.RED_RISK, "startDate");
        }
        else {
            loggerValidator.info(new Message(Status.OK, "startDate"));
            return new Message(Status.OK, "startDate");
        }
    }

    /**
     * The method returns the class for which the validator is used
     * @return class for which the validator is used
     */
    @Override
    public Class<?> getClassValidation() {
        return Contract.class;
    }
}
