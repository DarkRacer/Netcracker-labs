package com.nc.labs.validation.contract;

import com.nc.labs.entity.TvContract;
import com.nc.labs.enums.Status;
import com.nc.labs.validation.Message;
import com.nc.labs.validation.Validator;
import org.apache.log4j.Logger;

/**
 * The class validates the package channel
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class PackageChannelValidator implements Validator<TvContract> {
    /**
     * Logger for the validator
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * The method does validation the package channel
     * @param objectForValidation object for validation
     * @return validation message
     */
    @Override
    public Message validate(final TvContract objectForValidation) {
        if (objectForValidation.getPackageChannel() == null) {
            loggerValidator.error(new Message("Channel package is not specified or specified incorrectly",
                    Status.ERROR, "packageChannel"));

            return new Message("Channel package is not specified or specified incorrectly",
                    Status.ERROR, "packageChannel");
        } else {
            loggerValidator.info(new Message(Status.OK, "packageChannel"));

            return new Message(Status.OK, "packageChannel");
        }
    }

    /**
     * The method returns the class for which the validator is used
     * @return class for which the validator is used
     */
    @Override
    public Class<?> getClassValidation() {
        return TvContract.class;
    }
}
