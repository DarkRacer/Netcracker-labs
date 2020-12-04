package com.nc.labs.validation.client;

import com.nc.labs.entity.Client;
import com.nc.labs.enums.Status;
import com.nc.labs.validation.Message;
import com.nc.labs.validation.Validator;
import org.apache.log4j.Logger;

/**
 * The class validates the id client
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class IdClientValidator implements Validator<Client> {
    /**
     * Logger for the validator
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * The method does validation the id client
     * @param objectForValidation object for validation
     * @return validation message
     */
    @Override
    public Message validate(Client objectForValidation) {
        if (objectForValidation.getId() == 0){
            loggerValidator.error(new Message("This field must only contain numbers",
                    Status.ERROR, "idClient"));

            return new Message("This field must only contain numbers", Status.ERROR, "idClient");
        }
        else if (objectForValidation.getId() < 0) {
            loggerValidator.error(new Message("This field must only contain positive numbers",
                    Status.ERROR, "idClient"));

            return new Message("This field must only contain positive numbers",
                    Status.ERROR, "idClient");
        }
        else {
            loggerValidator.info(new Message(Status.OK, "idClient"));

            return new Message(Status.OK, "idClient");
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
