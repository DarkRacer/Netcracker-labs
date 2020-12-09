package com.nc.labs.validation.client;

import com.nc.labs.entity.Client;
import com.nc.labs.enums.Status;
import com.nc.labs.validation.Message;
import com.nc.labs.validation.Validator;
import org.apache.log4j.Logger;

/**
 * The class validates the gender
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class GenderValidator implements Validator<Client> {
    /**
     * Logger for the validator
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * The method does validation the gender
     * @param objectForValidation object for validation
     * @return validation message
     */
    @Override
    public Message validate(final Client objectForValidation) {
        if (objectForValidation.getGender() == null) {
            loggerValidator.error(new Message("Gender is not specified or specified incorrectly",
                    Status.ERROR, "gender"));

            return new Message("Gender is not specified or specified incorrectly", Status.ERROR, "gender");
        } else {
            loggerValidator.info(new Message(Status.OK, "gender"));

            return new Message(Status.OK, "gender");
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
