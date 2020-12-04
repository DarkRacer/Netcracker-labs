package com.nc.labs.validation.client;

import com.nc.labs.entity.Client;
import com.nc.labs.enums.Status;
import com.nc.labs.validation.Message;
import com.nc.labs.validation.Validator;
import org.apache.log4j.Logger;

/**
 * The class validates the surname
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class SurnameValidator implements Validator<Client> {
    /**
     * Logger for the validator
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * The method does validation the surname
     * @param objectForValidation object for validation
     * @return validation message
     */
    @Override
    public Message validate(Client objectForValidation) {
        if (objectForValidation.getSurname() == null){
            loggerValidator.error(new Message("The surname field must not be empty",
                    Status.ERROR, "surname"));

            return new Message("The surname field must not be empty", Status.ERROR, "surname");
        }
        else if (objectForValidation.getSurname().matches("\\d+")) {
            loggerValidator.warn(new Message("The surname field must not contain numbers",
                    Status.RED_RISK, "surname"));

            return new Message("The surname field must not contain numbers",
                    Status.RED_RISK, "surname");
        }
        else {
            loggerValidator.info(new Message(Status.OK, "surname"));

            return new Message(Status.OK, "surname");
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
