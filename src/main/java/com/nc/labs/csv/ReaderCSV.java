package com.nc.labs.csv;

import com.nc.labs.entity.*;
import com.nc.labs.enums.PackageChannel;
import com.nc.labs.enums.TypeContract;
import com.nc.labs.repository.Repository;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.*;

/**
 * Class for parsing a csv file
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class ReaderCSV {

    /**
     * This method parses the csv file
     * @param contractRepository repository where data is entered
     * @param fileName file name
     */
    public void read(Repository<Contract> contractRepository, String fileName) {
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
    private void converting (ContractCSV contractCSV, Repository<Contract> contractRepository) {
        if (contractCSV.check()){
            String[] strings = contractCSV.getAddInfo().split(",");
            Client client = createClient(contractCSV);

            if (contractCSV.getType() == TypeContract.TV){
                TvContract tvContract = createTvContract(contractCSV, client, strings[0]);

                if (contractRepository.getSize() != 0){
                    if (checkExists(tvContract, contractRepository)){
                        contractRepository.add(tvContract);
                    }
                }
                else contractRepository.add(tvContract);

            }
            else if (contractCSV.getType() == TypeContract.Cellular){
                CellularContract cellularContract = createCellularContract(contractCSV, client, strings);

                if (contractRepository.getSize() != 0){
                    if (checkExists(cellularContract, contractRepository)){
                        contractRepository.add(cellularContract);
                    }
                }
                else contractRepository.add(cellularContract);
            }
            else {
               InternetContract internetContract = createInternetContract(contractCSV, client, strings[0]);

                if (contractRepository.getSize() != 0){
                    if (checkExists(internetContract, contractRepository)){
                        contractRepository.add(internetContract);
                    }
                }
                else contractRepository.add(internetContract);
            }
        }
    }

    /**
     * This method creates a client
     * @param contractCSV data from the file
     * @return client
     */
    private Client createClient (ContractCSV contractCSV){
        return new Client(contractCSV.getIdClient(), contractCSV.getSurname(), contractCSV.getFirstName(),
                contractCSV.getPatronymic(), contractCSV.getDateOfBirth(), contractCSV.getGender(),
                contractCSV.getNumberPassport(), contractCSV.getSeriesPassport());
    }

    /**
     * This method creates a tv contract
     * @param contractCSV data from the file
     * @param client contract client
     * @param string channel package
     * @return tv contract or null
     */
    private TvContract createTvContract(ContractCSV contractCSV, Client client, String string){
        for(PackageChannel packageChannel : PackageChannel.values()) {
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
    private CellularContract createCellularContract(ContractCSV contractCSV, Client client, String[] strings) {
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
    private InternetContract createInternetContract(ContractCSV contractCSV, Client client, String string){
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
    private boolean checkExists(TvContract tvContract, Repository<Contract> contractRepository){
        return  tvContract != null &&
                contractRepository.search(contract -> contract.getClient().getNumberPassport() ==
                        tvContract.getClient().getNumberPassport()
                        && contract.getClient().getSeriesPassport() == tvContract.getClient().getSeriesPassport()
                        && contract.getStartDate().equals(tvContract.getStartDate())
                        && contract.getClass().getName().equals(tvContract.getClass().getName())) == null;
    }

    /**
     * This method checks the cellular contract for uniqueness
     * @param cellularContract cellular contract to check
     * @param contractRepository the repository where it is checked
     * @return true - does not exist or false - exists
     */
    private boolean checkExists(CellularContract cellularContract, Repository<Contract> contractRepository){
        return  contractRepository.search(contract -> contract.getClient().getNumberPassport() ==
                cellularContract.getClient().getNumberPassport()
                && contract.getClient().getSeriesPassport() == cellularContract.getClient().getSeriesPassport()
                && contract.getStartDate().equals(cellularContract.getStartDate())
                && contract.getClass().getName().equals(cellularContract.getClass().getName())) == null;
    }

    /**
     * This method checks the internet contract for uniqueness
     * @param internetContract internet contract to check
     * @param contractRepository the repository where it is checked
     * @return true - does not exist or false - exists
     */
    private boolean checkExists(InternetContract internetContract, Repository<Contract> contractRepository){
        return  contractRepository.search(contract -> contract.getClient().getNumberPassport() ==
                internetContract.getClient().getNumberPassport()
                && contract.getClient().getSeriesPassport() == internetContract.getClient().getSeriesPassport()
                && contract.getStartDate().equals(internetContract.getStartDate())
                && contract.getClass().getName().equals(internetContract.getClass().getName())) == null;
    }
}
