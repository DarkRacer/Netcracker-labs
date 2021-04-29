package com.nc.labs.db;

import lombok.Getter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class describes database connection
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class Connect {
    /**
     * URL for connection to database
     */
    private static String URL="jdbc:postgresql://localhost:5432/repository";

    /**
     * USERNAME for connection to database
     */
    private static String USERNAME="postgres";

    /**
     * PASSWORD for connection to database
     */
    private static String PASSWORD="54321";

    /**
     * Connection object
     */
    @Getter
    private Connection connection;

    /**
     * Logger for the connection
     */
    private static final Logger logger = LogManager.getLogger(Connect.class);

    /**
     * Constructor creates a connection to the database
     */
    public Connect() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            logger.info("Connection established");
        } catch (SQLException exception) {
            logger.error("Error: connection not established");
            exception.printStackTrace();
        }
    }
}
