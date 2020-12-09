package com.nc.labs.csv;

import com.nc.labs.entity.*;
import com.nc.labs.enums.PackageChannel;
import com.nc.labs.enums.Status;
import com.nc.labs.enums.TypeContract;
import com.nc.labs.validation.Validator;
import com.nc.labs.validation.client.*;
import com.nc.labs.validation.contract.*;
import com.nc.labs.repository.ClientRepository;
import com.nc.labs.repository.Repository;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for parsing a csv file
 * @author Maksim Shcherbakov
 * @version 1.2
 */
public class ReaderCSV {
    /**
     * Object of the ClientRepository class
     */
    private static final ClientRepository clientRepository = new ClientRepository();

    private static List<Validator<? extends Object>> validators = new ArrayList<>();
    static {
        validators.add(new IdContractValidator());
        validators.add(new StartDateValidator());
        validators.add(new EndDateValidator());
        validators.add(new NumberContractValidator());
        validators.add(new IdClientValidator());
        validators.add(new SurnameValidator());
        validators.add(new FirstNameValidator());
        validators.add(new PatronymicValidator());
        validators.add(new DateOfBirthValidator());
        validators.add(new GenderValidator());
        validators.add(new PassportNumberValidator());
        validators.add(new PassportSeriesValidator());
        validators.add(new PackageChannelValidator());
        validators.add(new MinuteValidator());
        validators.add(new GbInternetValidator());
        validators.add(new SmsValidator());
        validators.add(new MaximumSpeedValidator());
    }

    /**
     * This method parses the csv file
     * @param contractRepository repository where data is entered
     * @param fileName file name
     */
    public void read(final Repository<Contract> contractRepository, final String fileName) {
        try {
            CsvToBeanBuilder<ContractCSV> beanBuilder = new CsvToBeanBuilder<>(
                    new InputStreamReader(new FileInputStream(fileName)));
            beanBuilder.withSeparator(';');
            beanBuilder.withType(ContractCSV.class).withSkipLines(1).withIgnoreLeadingWhiteSpace(true);
            beanBuilder.build().parse().forEach(contractCSV -> converting(contractCSV, contractRepository));

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * This method converts data from a file to contract data
     * @param contractCSV data from the file
     * @param contractRepository repository where data is entered
     */
    private void converting(final ContractCSV contractCSV, final Repository<Contract> contractRepository) {
        String[] strings = contractCSV.getAddInfo().split(",");
        Client client = clientRepository.createClient(contractCSV.getIdClient(), contractCSV.getSurname(),
                contractCSV.getFirstName(), contractCSV.getPatronymic(), contractCSV.getDateOfBirth(),
                contractCSV.getGender(), contractCSV.getNumberPassport(), contractCSV.getSeriesPassport());

        if (client != null) {
            if (contractCSV.getType() == TypeContract.TV) {
                TvContract tvContract = createTvContract(contractCSV, client, strings[0]);

                if (tvContract != null && validation(tvContract)) {
                    if (contractRepository.getSize() != 0) {
                        if (checkExists(tvContract, contractRepository)) {
                            contractRepository.add(tvContract);
                        }
                    } else {
                        contractRepository.add(tvContract);
                    }
                }
            } else if (contractCSV.getType() == TypeContract.Cellular) {
                CellularContract cellularContract = createCellularContract(contractCSV, client, strings);

                if (cellularContract != null && validation(cellularContract)) {
                    if (contractRepository.getSize() != 0) {
                        if (checkExists(cellularContract, contractRepository)) {
                            contractRepository.add(cellularContract);
                        }
                    } else {
                        contractRepository.add(cellularContract);
                    }
                }
            } else {
                InternetContract internetContract = createInternetContract(contractCSV, client, strings[0]);

                if (internetContract != null && validation(internetContract)) {
                    if (contractRepository.getSize() != 0) {
                        if (checkExists(internetContract, contractRepository)) {
                            contractRepository.add(internetContract);
                        }
                    } else {
                        contractRepository.add(internetContract);
                    }
                }
            }
        }
    }

    /**
     * This method creates a tv contract
     * @param contractCSV data from the file
     * @param client contract client
     * @param string channel package
     * @return tv contract or null
     */
    private TvContract createTvContract(final ContractCSV contractCSV, final Client client, final String string) {
        for (PackageChannel packageChannel : PackageChannel.values()) {
            if (packageChannel.name().equals(string)) {
                return new TvContract(contractCSV.getId(), contractCSV.getStartDate(),
                        contractCSV.getEndDate(), contractCSV.getNumberContract(), client, packageChannel);
            }
        }
        return null;
    }

    /**
     * This method creates a cellular contract
     * @param contractCSV data from the file
     * @param client contract client
     * @param strings additional information about the cellular contract
     * @return cellular contract or null
     */
    private CellularContract createCellularContract(final ContractCSV contractCSV, final Client client,
                                                    final String[] strings) {
        for (String string : strings) {
            if (string.matches("\\d+")) {
                int minutes = Integer.parseInt(strings[0]);
                int gbInternet = Integer.parseInt(strings[1]);
                int sms = Integer.parseInt(strings[2]);

                return new CellularContract(contractCSV.getId(), contractCSV.getStartDate(),
                    contractCSV.getEndDate(), contractCSV.getNumberContract(), client, minutes, gbInternet, sms);
            }
        }
        return null;
    }

    /**
     * This method creates a internet contract
     * @param contractCSV data from the file
     * @param client contract client
     * @param string the maximum speed on the contract
     * @return internet contract or null
     */
    private InternetContract createInternetContract(final ContractCSV contractCSV, final Client client,
                                                    final String string) {
        if (string.matches("\\d+")) {
            int maximumSpeed = Integer.parseInt(string);

            return new InternetContract(contractCSV.getId(), contractCSV.getStartDate(),
                    contractCSV.getEndDate(), contractCSV.getNumberContract(), client, maximumSpeed);
        }
        return null;
    }

    /**
     * This method checks the TV contract for uniqueness
     * @param tvContract tv contract to check
     * @param contractRepository the repository where it is checked
     * @return true - does not exist or false - exists
     */
    private boolean checkExists(final TvContract tvContract, final Repository<Contract> contractRepository) {
        return  tvContract != null
                && contractRepository.search(contract -> contract.getClient() == tvContract.getClient()
                        && contract.getStartDate().equals(tvContract.getStartDate())
                        && contract.getClass().getName().equals(tvContract.getClass().getName())) == null;
    }

    /**
     * This method checks the cellular contract for uniqueness
     * @param cellularContract cellular contract to check
     * @param contractRepository the repository where it is checked
     * @return true - does not exist or false - exists
     */
    private boolean checkExists(final CellularContract cellularContract, final Repository<Contract> contractRepository) {
        return  contractRepository.search(contract -> contract.getClient() == cellularContract.getClient()
                && contract.getStartDate().equals(cellularContract.getStartDate())
                && contract.getClass().getName().equals(cellularContract.getClass().getName())) == null;
    }

    /**
     * This method checks the internet contract for uniqueness
     * @param internetContract internet contract to check
     * @param contractRepository the repository where it is checked
     * @return true - does not exist or false - exists
     */
    private boolean checkExists(final InternetContract internetContract, final Repository<Contract> contractRepository) {
        return  contractRepository.search(contract -> contract.getClient() == internetContract.getClient()
                && contract.getStartDate().equals(internetContract.getStartDate())
                && contract.getClass().getName().equals(internetContract.getClass().getName())) == null;
    }

    /**
     * The method does the validation of the contract
     * @param contract the contract for validation
     * @return true - the contract was validated; false - the contract was not validated
     */
    private boolean validation(final Contract contract) {
        for (Validator validator : validators) {
            if (validator.getClassValidation().equals(Contract.class)) {
                if (validator.validate(contract).getStatus() == Status.ERROR) {
                    return false;
                }
            } else if (validator.getClassValidation().equals(contract.getClient().getClass())) {
                if (validator.validate(contract.getClient()).getStatus() == Status.ERROR) {
                    return false;
                }
            } else if (validator.getClassValidation().equals(contract.getClass())) {
                if (validator.validate(contract).getStatus() == Status.ERROR) {
                    return false;
                }
            }
        }
        return true;
    }
}
