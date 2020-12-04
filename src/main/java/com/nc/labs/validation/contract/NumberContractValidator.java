package com.nc.labs.validation.contract;

import com.nc.labs.entity.Contract;
import com.nc.labs.enums.Status;
import com.nc.labs.validation.Message;
import com.nc.labs.validation.Validator;
import org.apache.log4j.Logger;

/**
 * The class validates the number contract
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class NumberContractValidator implements Validator<Contract> {
    /**
     * Logger for the validator
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * The method does validation the number contract
     * @param objectForValidation object for validation
     * @return validation message
     */
    @Override
    public Message validate(Contract objectForValidation) {
        if (objectForValidation.getNumberContract() == 0){
            loggerValidator.error(new Message("This field must only contain numbers",
                    Status.ERROR, "numberContract"));

            return new Message("This field must only contain numbers", Status.ERROR, "numberContract");
        }
        else if (objectForValidation.getNumberContract() < 0) {
            loggerValidator.error(new Message("This field must only contain positive numbers",
                    Status.ERROR, "numberContract"));

            return new Message("This field must only contain positive numbers",
                    Status.ERROR, "numberContract");
        }
        else {
            loggerValidator.info(new Message(Status.OK, "numberContract"));

            return new Message(Status.OK, "numberContract");
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
