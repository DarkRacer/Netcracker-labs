package com.nc.labs.validation.client;

import com.nc.labs.entity.Client;
import com.nc.labs.enums.Status;
import com.nc.labs.validation.Message;
import com.nc.labs.validation.Validator;
import org.apache.log4j.Logger;

/**
 * The class validates the passport number
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class PassportNumberValidator implements Validator<Client> {
    /**
     * Logger for the validator
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * The method does validation the passport number
     * @param objectForValidation object for validation
     * @return validation message
     */
    @Override
    public Message validate(final Client objectForValidation) {
        if (objectForValidation.getNumberPassport() == 0) {
            loggerValidator.error(new Message("This field must only contain numbers",
                    Status.ERROR, "passportNumber"));

            return new Message("This field must only contain numbers",
                    Status.ERROR, "passportNumber");
        } else if (objectForValidation.getNumberPassport() < 0) {
            loggerValidator.error(new Message("This field must only contain positive numbers",
                    Status.ERROR, "passportNumber"));

            return new Message("This field must only contain positive numbers",
                    Status.ERROR, "passportNumber");
        } else {
            loggerValidator.info(new Message(Status.OK, "passportNumber"));

            return new Message(Status.OK, "passportNumber");
        }
    }

    /**
     * The method returns the class for which the validator is used
     * @return class for which the validator is used
     */
    @Override
    public Class<?> getClassValidation() {
        return Client.class;
    }
}
