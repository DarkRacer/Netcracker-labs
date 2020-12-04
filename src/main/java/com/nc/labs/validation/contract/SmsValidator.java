package com.nc.labs.validation.contract;

import com.nc.labs.entity.CellularContract;
import com.nc.labs.enums.Status;
import com.nc.labs.validation.Message;
import com.nc.labs.validation.Validator;
import org.apache.log4j.Logger;

/**
 * The class validates the sms
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class SmsValidator implements Validator<CellularContract> {
    /**
     * Logger for the validator
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * The method does validation the sms
     * @param objectForValidation object for validation
     * @return validation message
     */
    @Override
    public Message validate(CellularContract objectForValidation) {
        if (objectForValidation.getSms() == 0){
            loggerValidator.error(new Message("This field must only contain numbers", Status.ERROR, "sms"));

            return new Message("This field must only contain numbers", Status.ERROR, "sms");
        }
        else if (objectForValidation.getSms() < 0) {
            loggerValidator.error(new Message("This field must only contain positive numbers",
                    Status.ERROR, "sms"));

            return new Message("This field must only contain positive numbers", Status.ERROR, "sms");
        }
        else {
            loggerValidator.info(new Message(Status.OK, "sms"));
            return new Message(Status.OK, "sms");
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
