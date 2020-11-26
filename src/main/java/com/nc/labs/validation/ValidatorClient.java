package com.nc.labs.validation;

import com.nc.labs.entity.Client;
import com.nc.labs.enums.Gender;
import com.nc.labs.enums.TypeStatus;
import com.nc.labs.validation.contract.ContractStatus;
import lombok.Getter;
import org.apache.log4j.Logger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class does client validation
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class ValidatorClient {
    /**
     * Verification line
     */
    @Getter
    private static int line = 1;

    /**
     * Logger for information about verification
     */
    private static final Logger loggerValidator = Logger.getLogger("Validator");

    /**
     * Verification status
     */
    private ContractStatus contractStatus;

    /**
     * The method does the verification of the client
     * @param client client for verification
     * @return verification result
     */
    public ContractStatus checkClient(Client client){
        List<ContractStatus> contractStatuses = new ArrayList<>();

        contractStatuses.add(checkIdClient(client.getId()));
        contractStatuses.add(checkSurname(client.getSurname()));
        contractStatuses.add(checkFirstName(client.getFirstName()));
        contractStatuses.add(checkPatronymic(client.getPatronymic()));
        contractStatuses.add(checkDateOfBirth(client.getDateOfBirth()));
        contractStatuses.add(checkGender(client.getGender()));
        contractStatuses.add(checkPassportNumber(client.getNumberPassport()));
        contractStatuses.add(checkPassportSeries(client.getSeriesPassport()));

        for (ContractStatus contractStatus : contractStatuses){
            if (!contractStatus.getTypeStatus().equals(TypeStatus.OK)){
                increaseLineClient();
                return contractStatus;
            }
        }
        increaseLineClient();
        return new ContractStatus(TypeStatus.OK, getLine());
    }

    /**
     * This method checks the id client
     * @param idClient id client for checks
     * @return verification status
     */
    private ContractStatus checkIdClient(int idClient){
        if (idClient == 0){
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "This field must only contain numbers", line, "idClient");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else if (idClient < 0) {
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "This field must only contain positive numbers", line, "idClient");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else {
            contractStatus = new ContractStatus(TypeStatus.OK, "OK", line, "idClient");

            loggerValidator.info(contractStatus);
            return contractStatus;
        }
    }

    /**
     * This method checks the surname client
     * @param surname surname for checks
     * @return verification status
     */
    private ContractStatus checkSurname(String surname){
        if (surname == null){
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "The surname field must not be empty", line, "surname");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else if (surname.matches("\\d+")) {
            contractStatus = new ContractStatus(TypeStatus.RED_RISK,
                    "The surname field must not contain numbers", line, "surname");

            loggerValidator.warn(contractStatus);
            return contractStatus;
        }
        else {
            contractStatus = new ContractStatus(TypeStatus.OK, "OK", line, "surname");

            loggerValidator.info(contractStatus);
            return contractStatus;
        }
    }

    /**
     * This method checks the first name client
     * @param firstName first name client for checks
     * @return verification status
     */
    private ContractStatus checkFirstName(String firstName){
        if (firstName == null){
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "The firstName field must not be empty", line, "firstName");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else if (firstName.matches("\\d+")) {
            contractStatus = new ContractStatus(TypeStatus.RED_RISK,
                    "The firstName field must not contain numbers", line, "firstName");

            loggerValidator.warn(contractStatus);
            return contractStatus;
        }
        else {
            contractStatus = new ContractStatus(TypeStatus.OK, "OK", line, "firstName");

            loggerValidator.info(contractStatus);
            return contractStatus;
        }
    }

    /**
     * This method checks the patronymic client
     * @param patronymic patronymic client for checks
     * @return verification status
     */
    private ContractStatus checkPatronymic(String patronymic){
        if (patronymic == null){
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "The patronymic field must not be empty", line, "patronymic");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else if (patronymic.matches("\\d+")) {
            contractStatus = new ContractStatus(TypeStatus.RED_RISK,
                    "The patronymic field must not contain numbers", line, "patronymic");

            loggerValidator.warn(contractStatus);
            return contractStatus;
        }
        else {
            contractStatus = new ContractStatus(TypeStatus.OK, "OK", line, "patronymic");

            loggerValidator.info(contractStatus);
            return contractStatus;
        }
    }

    /**
     * This method checks the date of birth client
     * @param dateOfBirth date of birth client for checks
     * @return verification status
     */
    private ContractStatus checkDateOfBirth(LocalDate dateOfBirth){
        if (dateOfBirth == null){
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "This field must contain only the date(DD.MM. YYYY)", line, "dateOfBirth");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else if(dateOfBirth.isAfter(LocalDate.now()) || dateOfBirth.equals(LocalDate.now())){
            contractStatus = new ContractStatus(TypeStatus.RED_RISK,
                    "The date cannot be later than the date that is today", line, "dateOfBirth");

            loggerValidator.warn(contractStatus);
            return contractStatus;
        }
        else {
            contractStatus = new ContractStatus(TypeStatus.OK, "OK", line, "dateOfBirth");

            loggerValidator.info(contractStatus);
            return contractStatus;
        }
    }

    /**
     * This method checks the gender client
     * @param gender gender client for checks
     * @return verification status
     */
    private ContractStatus checkGender(Gender gender){
        if (gender == null){
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "Gender is not specified or specified incorrectly", line, "gender");

            loggerValidator.error(contractStatus);
        }
        else {
            contractStatus = new ContractStatus(TypeStatus.OK, "OK", line, "gender");

            loggerValidator.info(contractStatus);
        }
        return contractStatus;
    }

    /**
     * This method checks the passport number client
     * @param passportNumber passport number client for checks
     * @return verification status
     */
    private ContractStatus checkPassportNumber(int passportNumber){
        if (passportNumber == 0){
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "This field must only contain numbers", line, "passportNumber");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else if (passportNumber < 0) {
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "This field must only contain positive numbers", line, "passportNumber");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else {
            contractStatus = new ContractStatus(TypeStatus.OK, "OK", line, "passportNumber");

            loggerValidator.info(contractStatus);
            return contractStatus;
        }
    }

    /**
     * This method checks the passport series client
     * @param passportSeries passport series client for checks
     * @return verification status
     */
    private ContractStatus checkPassportSeries(int passportSeries){
        if (passportSeries == 0){
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "This field must only contain numbers", line, "passportSeries");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else if (passportSeries < 0) {
            contractStatus = new ContractStatus(TypeStatus.ERROR,
                    "This field must only contain positive numbers", line, "passportSeries");

            loggerValidator.error(contractStatus);
            return contractStatus;
        }
        else {
            contractStatus = new ContractStatus(TypeStatus.OK, "OK", line, "passportSeries");

            loggerValidator.info(contractStatus);
            return contractStatus;
        }
    }

    /**
     * This method increases the line counter
     * @return new value of the counter
     */
    private int increaseLineClient(){
        line += 1;
        return line;
    }

    /**
     * This method clear the line counter
     */
    public static void clearLineClient(){
        line = 0;
    }
}
