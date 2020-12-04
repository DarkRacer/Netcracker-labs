package com.nc.labs.validation.contract;

import com.nc.labs.entity.InternetContract;
import com.nc.labs.enums.Status;
import com.nc.labs.validation.Message;
import com.nc.labs.validation.Validator;
import org.apache.log4j.Logger;

/**
 * The class validates the maximum speed
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class MaximumSpeedValidator implements Validator<InternetContract> {
    /**
     * Logger for the validator
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * The method does validation the maximum speed
     * @param objectForValidation object for validation
     * @return validation message
     */
    @Override
    public Message validate(InternetContract objectForValidation) {
        if (objectForValidation.getMaximumSpeed() == 0){
            loggerValidator.error(new Message("This field must only contain numbers",
                    Status.ERROR, "maximumSpeed"));

            return new Message("This field must only contain numbers", Status.ERROR, "maximumSpeed");
        }
        else if (objectForValidation.getMaximumSpeed() < 0) {
            loggerValidator.error(new Message("This field must only contain positive numbers",
                    Status.ERROR, "maximumSpeed"));

            return new Message("This field must only contain positive numbers",
                    Status.ERROR, "maximumSpeed");
        }
        else {
            loggerValidator.info(new Message(Status.OK, "maximumSpeed"));

            return new Message(Status.OK, "maximumSpeed");
        }
    }

    /**
     * The method returns the class for which the validator is used
     * @return class for which the validator is used
     */
    @Override
    public Class<?> getClassValidation() {
        return InternetContract.class;
    }
}
