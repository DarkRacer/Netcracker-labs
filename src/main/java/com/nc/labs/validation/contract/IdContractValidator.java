package com.nc.labs.validation.contract;

import com.nc.labs.entity.Contract;
import com.nc.labs.enums.Status;
import com.nc.labs.validation.Message;
import com.nc.labs.validation.Validator;
import org.apache.log4j.Logger;

/**
 * The class validates the id contract
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class IdContractValidator implements Validator<Contract> {
    /**
     * Logger for the validator
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * The method does validation the id contract
     * @param objectForValidation object for validation
     * @return validation message
     */
    @Override
    public Message validate(final Contract objectForValidation) {
            if (objectForValidation.getId() == 0) {
                loggerValidator.error(new Message("This field must only contain numbers",
                        Status.ERROR, "idContract"));

                return new Message("This field must only contain numbers", Status.ERROR, "idContract");
            } else if (objectForValidation.getId() < 0) {
                loggerValidator.error(new Message("This field must only contain positive numbers",
                        Status.ERROR, "idContract"));

                return new Message("This field must only contain positive numbers",
                        Status.ERROR, "idContract");
            } else {
                loggerValidator.info(new Message(Status.OK, "idContract"));

                return new Message(Status.OK, "idContract");
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
