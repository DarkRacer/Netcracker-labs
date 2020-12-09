package com.nc.labs.validation.contract;

import com.nc.labs.entity.Contract;
import com.nc.labs.enums.Status;
import com.nc.labs.validation.Message;
import com.nc.labs.validation.Validator;
import org.apache.log4j.Logger;

/**
 * The class validates the end date
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class EndDateValidator implements Validator<Contract> {
    /**
     * Logger for the validator
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * The method does validation the end date
     * @param objectForValidation object for validation
     * @return validation message
     */
    @Override
    public Message validate(final Contract objectForValidation) {
        if (objectForValidation.getEndDate() == null) {
            loggerValidator.error(new Message("This field must contain only the date(DD.MM. YYYY)",
                    Status.ERROR, "endDate"));

            return new Message("This field must contain only the date(DD.MM. YYYY)",
                    Status.ERROR, "endDate");
        } else if (objectForValidation.getStartDate().isAfter(objectForValidation.getEndDate())) {
            loggerValidator.warn(new Message("The date cannot be later than the contract start date",
                    Status.RED_RISK, "endDate"));

            return new Message("The date cannot be later than the contract start date",
                    Status.RED_RISK, "endDate");
        } else if (objectForValidation.getEndDate().equals(objectForValidation.getStartDate())) {
            loggerValidator.warn(new Message("The end date is equal to the start date",
                    Status.YELLOW_RISK, "endDate"));

            return new Message("The end date is equal to the start date", Status.YELLOW_RISK, "endDate");
        } else {
            loggerValidator.info(new Message(Status.OK, "endDate"));

            return new Message(Status.OK, "endDate");
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
