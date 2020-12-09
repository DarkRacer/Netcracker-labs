package com.nc.labs.validation.contract;

import com.nc.labs.entity.CellularContract;
import com.nc.labs.enums.Status;
import com.nc.labs.validation.Message;
import com.nc.labs.validation.Validator;
import org.apache.log4j.Logger;

/**
 * The class validates the gb internet
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class GbInternetValidator implements Validator<CellularContract> {
    /**
     * Logger for the validator
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * The method does validation the gb internet
     * @param objectForValidation object for validation
     * @return validation message
     */
    @Override
    public Message validate(final CellularContract objectForValidation) {
        if (objectForValidation.getGbInternet() == 0) {
            loggerValidator.error(new Message("This field must only contain numbers",
                    Status.ERROR, "gbInternet"));

            return new Message("This field must only contain numbers", Status.ERROR, "gbInternet");
        } else if (objectForValidation.getGbInternet() < 0) {
            loggerValidator.error(new Message("This field must only contain positive numbers",
                    Status.ERROR, "gbInternet"));

            return new Message("This field must only contain positive numbers", Status.ERROR, "gbInternet");
        } else {
            loggerValidator.info(new Message(Status.OK, "gbInternet"));

            return new Message(Status.OK, "gbInternet");
        }
    }

    /**
     * The method returns the class for which the validator is used
     * @return class for which the validator is used
     */
    @Override
    public Class<?> getClassValidation() {
        return CellularContract.class;
    }
}
