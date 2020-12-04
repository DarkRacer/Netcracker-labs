package com.nc.labs.validation.client;

import com.nc.labs.entity.Client;
import com.nc.labs.enums.Status;
import com.nc.labs.validation.Message;
import com.nc.labs.validation.Validator;
import org.apache.log4j.Logger;

import java.time.LocalDate;

/**
 * The class validates the date of birth
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class DateOfBirthValidator implements Validator<Client> {
    /**
     * Logger for the validator
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * The method does validation the date of birth
     * @param objectForValidation object for validation
     * @return validation message
     */
    @Override
    public Message validate(Client objectForValidation) {
        if (objectForValidation.getDateOfBirth() == null){
            loggerValidator.error(new Message("This field must contain only the date(DD.MM. YYYY)",
                    Status.ERROR, "dateOfBirth"));

            return new Message("This field must contain only the date(DD.MM. YYYY)",
                    Status.ERROR, "dateOfBirth");
        }
        else if(objectForValidation.getDateOfBirth().isAfter(LocalDate.now())
                || objectForValidation.getDateOfBirth().equals(LocalDate.now())){
            loggerValidator.warn(new Message("The date cannot be later than the date that is today",
                    Status.RED_RISK, "dateOfBirth"));

            return new Message("The date cannot be later than the date that is today",
                    Status.RED_RISK, "dateOfBirth");
        }
        else {
            loggerValidator.info(new Message(Status.OK, "dateOfBirth"));

            return new Message(Status.OK, "dateOfBirth");
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
