package com.nc.labs.xml;

import com.nc.labs.db.JDBCRepo;
import com.nc.labs.entity.Contract;
import com.nc.labs.repository.Repository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Class describes work with XML
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class XMLRepo {
    /**
     * Logger for the work with XML
     */
    private static final Logger logger = LogManager.getLogger(JDBCRepo.class);

    /**
     * Method saving contracts repository in XML
     * @param contractRepository contracts repository for saving
     */
    public void saveContracts (Repository<Contract> contractRepository, String fileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(contractRepository.getClass());
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(contractRepository, new File(fileName));
            logger.info("Repository " + contractRepository + " saved");
        } catch (JAXBException exception)  {
            logger.error("Error: Saving contacts repository in XML failed", exception);
            exception.printStackTrace();
        }
    }

    /**
     * Method getting XML dump in contacts repository
     * @param contractRepository contacts repository for saving dump
     */
    public void dump (Repository<Contract> contractRepository, String fileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(contractRepository.getClass());
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileInputStream fileInputStream = new FileInputStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);

            for (Contract contract : ((Repository<Contract>) unmarshaller.unmarshal(inputStreamReader)).getAll()) {
                contractRepository.add(contract);
            }
            logger.info("Getting XML dump in contacts repository was successfully");
        } catch (FileNotFoundException | JAXBException exception) {
            logger.error("Error: Getting XML dump in contacts repository failed", exception);
            exception.printStackTrace();
        }
    }
}
