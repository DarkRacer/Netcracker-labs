package com.nc.labs.xml;

import com.nc.labs.csv.ReaderCSV;
import com.nc.labs.db.JDBCRepo;
import com.nc.labs.di.Injector;
import com.nc.labs.entity.Contract;
import com.nc.labs.repository.Repository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * The class checks the operation of methods of the XMLRepo class
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class XMLRepoTest {
    /**
     * Object of the Injector class
     */
    private Injector injector = new Injector();
    /**
     * Object of the Repository class
     */
    private Repository<Contract> contractRepository;

    /**
     * Object of the ReaderCSV class
     */
    private ReaderCSV readerCSV;

    /**
     * The initial conditions for the tests
     */
    @Before
    public void setUp() {
        try {
            readerCSV = injector.inject(new ReaderCSV());
            contractRepository = injector.inject(new Repository<>());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method checks saving contracts repository in XML
     */
    @Test
    public void saveContracts() throws JAXBException {
        XMLRepo xmlRepo = new XMLRepo();
        readerCSV.read(contractRepository, "src\\main\\resources\\contracts.csv");

        xmlRepo.saveContracts(contractRepository, "src\\main\\resources\\repository.xml");
    }

    /**
     * This method checks getting contracts repository from XML
     */
    @Test
    public void dumpContracts() throws JAXBException, IllegalAccessException, FileNotFoundException {
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = new ArrayList<>();
        XMLRepo xmlRepo = new XMLRepo();
        readerCSV.read(contractRepository, "src\\main\\resources\\contracts.csv");
        Repository<Contract> repository = injector.inject(new Repository<>());
        xmlRepo.dump(repository, "src\\main\\resources\\repository.xml");

        actual.add(contractRepository.getSize());
        actual.add(contractRepository.get(1).getId());
        actual.add(contractRepository.get(2).getId());
        actual.add(contractRepository.get(3).getId());
        actual.add(contractRepository.get(4).getId());
        actual.add(contractRepository.get(5).getId());
        actual.add(contractRepository.get(6).getId());
        actual.add(contractRepository.get(9).getId());
        actual.add(contractRepository.get(10).getId());

        expected.add(repository.getSize());
        expected.add(repository.get(1).getId());
        expected.add(repository.get(2).getId());
        expected.add(repository.get(3).getId());
        expected.add(repository.get(4).getId());
        expected.add(repository.get(5).getId());
        expected.add(repository.get(6).getId());
        expected.add(repository.get(9).getId());
        expected.add(repository.get(10).getId());

        Assert.assertEquals(expected, actual);
    }
}
