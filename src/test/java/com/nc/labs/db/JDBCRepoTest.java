package com.nc.labs.db;

import com.nc.labs.csv.ReaderCSV;
import com.nc.labs.di.Injector;
import com.nc.labs.entity.Contract;
import com.nc.labs.repository.Repository;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.ContentHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * The class checks the operation of methods of the JDBCRepo class
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class JDBCRepoTest {
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
     * This method checks saving contracts repository in database
     */
    @Test
    public void saveContracts() throws SQLException {
        JDBCRepo jdbcRepo = new JDBCRepo();
        jdbcRepo.initializationDb();
        readerCSV.read(contractRepository, "src\\main\\resources\\contracts.csv");
        jdbcRepo.saveContracts(contractRepository);
    }

    /**
     * This method checks getting contracts repository from database
     */
    @Test
    public void dump() throws SQLException, IllegalAccessException {
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = new ArrayList<>();
        JDBCRepo jdbcRepo = new JDBCRepo();
        readerCSV.read(contractRepository, "src\\main\\resources\\contracts.csv");
        Repository<Contract> repository = injector.inject(new Repository<>());
        jdbcRepo.dump(repository);

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
