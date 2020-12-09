package com.nc.labs.validation.client;

import com.nc.labs.entity.Client;
import com.nc.labs.enums.Status;
import com.nc.labs.validation.Message;
import com.nc.labs.validation.Validator;
import org.apache.log4j.Logger;

/**
 * The class validates the first name
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class FirstNameValidator implements Validator<Client> {
    /**
     * Logger for the validator
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * The method does validation the first name
     * @param objectForValidation object for validation
     * @return validation message
     */
    @Override
    public Message validate(final Client objectForValidation) {
        if (objectForValidation.getFirstName() == null) {
            loggerValidator.error(new Message("The firstName field must not be empty",
                    Status.ERROR, "firstName"));

            return new Message("The firstName field must not be empty", Status.ERROR, "firstName");
        } else if (objectForValidation.getFirstName().matches("\\d+")) {
            loggerValidator.warn(new Message("The firstName field must not contain numbers",
                    Status.RED_RISK, "firstName"));

            return new Message("The firstName field must not contain numbers",
                    Status.RED_RISK, "firstName");
        } else {
            loggerValidator.info(new Message(Status.OK, "firstName"));

            return new Message(Status.OK, "firstName");
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
