package com.nc.labs.validation.client;

import com.nc.labs.entity.Client;
import com.nc.labs.enums.Status;
import com.nc.labs.validation.Message;
import com.nc.labs.validation.Validator;
import org.apache.log4j.Logger;

/**
 * The class validates the patronymic
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class PatronymicValidator implements Validator<Client> {
    /**
     * Logger for the validator
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * The method does validation the patronymic
     * @param objectForValidation object for validation
     * @return validation message
     */
    @Override
    public Message validate(final Client objectForValidation) {
        if (objectForValidation.getPatronymic() == null) {
            loggerValidator.error(new Message("The patronymic field must not be empty",
                    Status.ERROR, "patronymic"));

            return new Message("The patronymic field must not be empty", Status.ERROR, "patronymic");
        } else if (objectForValidation.getPatronymic().matches("\\d+")) {
            loggerValidator.warn(new Message("The patronymic field must not contain numbers",
                    Status.RED_RISK, "patronymic"));

            return new Message("The patronymic field must not contain numbers",
                    Status.RED_RISK, "patronymic");
        } else {
            loggerValidator.info(new Message(Status.OK, "patronymic"));

            return new Message(Status.OK, "patronymic");
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
