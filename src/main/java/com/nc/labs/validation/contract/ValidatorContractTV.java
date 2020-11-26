package com.nc.labs.validation.contract;

import com.nc.labs.entity.TvContract;
import com.nc.labs.enums.PackageChannel;
import com.nc.labs.enums.TypeStatus;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 * This class does tv contract validation
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class ValidatorContractTV extends ValidatorContract {
    /**
     * Logger for information about verification
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * Verification status
     */
    private ContractStatus contractStatus;

    /**
     * The method does the verification of the tv contract
     * @param tvContract tv contract for verification
     * @return verification result
     */
    public ContractStatus check(TvContract tvContract) {
        List<ContractStatus> contractStatuses = new ArrayList<>();

        contractStatuses.add(checkId(tvContract.getId()));
        contractStatuses.add(checkStartDate(tvContract.getStartDate()));
        contractStatuses.add(checkEndDate(tvContract.getStartDate(),tvContract.getEndDate()));
        contractStatuses.add(checkNumber(tvContract.getNumberContract()));
        contractStatuses.add(checkPackage(tvContract.getPackageChannel()));

        for (ContractStatus contractStatus : contractStatuses){
            if (!contractStatus.getTypeStatus().equals(TypeStatus.OK)){
                increaseLine();
                return contractStatus;
            }
        }
        increaseLine();
        return new ContractStatus(TypeStatus.OK, getLine());
    }

    /**
     * This method checks the package channel
     * @param packageChannel package channel for checks
     * @return verification status
     */
    private ContractStatus checkPackage(PackageChannel packageChannel){
        if (packageChannel == null){
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "Channel package is not specified or specified incorrectly", getLine(), "packageChannel");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else {
            contractStatus = new ContractStatus(TypeStatus.OK, "OK", getLine(), "packageChannel");

            loggerValidator.info(contractStatus);
            return contractStatus;
        }
    }
}
