package com.nc.labs.db;

import com.nc.labs.entity.CellularContract;
import com.nc.labs.entity.Client;
import com.nc.labs.entity.Contract;
import com.nc.labs.entity.InternetContract;
import com.nc.labs.entity.TvContract;
import com.nc.labs.enums.TypeContract;
import com.nc.labs.repository.Repository;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Objects;

/**
 * Class describes work with database
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class JDBCRepo {
    /**
     * Object for setup connection to database
     */
    private static Connect connect;

    /**
     * Query for inserting contracts
     */
    private final static String INSERT_CONTRACTS = "INSERT INTO public.contracts(" +
            "id, start_date, end_date, number_contract, minutes, gb_internet, sms, maximum_speed, package_channel, contract_type, client_id)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    /**
     * Query for inserting clients
     */
    private final static String INSERT_CLIENTS = "INSERT INTO public.clients(" +
            "id, surname, first_name, patronymic, date_of_birth, gender, number_passport, series_passport)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    /**
     * Query for checking existence and getting contract
     */
    private final static String CHECK_EXISTENCE_CONTRACT = "SELECT c.* from public.contracts c where c.id = ?";

    /**
     * Query for checking existence and getting client
     */
    private final static String CHECK_EXISTENCE_CLIENT = "SELECT c.* from public.clients c where c.id = ?";

    /**
     * Query for getting contracts
     */
    private final static String SELECT_CONTRACTS = "SELECT c.* from public.contracts c";

    /**
     * Logger for the work with database
     */
    private static final Logger logger = LogManager.getLogger(JDBCRepo.class);

    /**
     * Method creates database tables
     */
    public void initializationDb(){
        connect = new Connect();
        try {
            Statement statement = connect.getConnection().createStatement();
            statement.executeUpdate("drop table if exists public.contracts");
            statement.executeUpdate("drop table if exists public.clients");

            ScriptRunner sc = new ScriptRunner(connect.getConnection());
            InputStream resourceAsStream = connect.getClass().getClassLoader().
                    getResourceAsStream("db.sql");
            assert resourceAsStream != null;
            sc.runScript(new InputStreamReader(resourceAsStream));
            logger.info("Database initialization was successful");
            connect.getConnection().close();
        } catch (SQLException exception) {
            logger.error("Error: Database initialization failed", exception);
            exception.printStackTrace();
        }

    }

    /**
     * Method saving contracts repository in database
     * @param repository contracts repository for saving
     * @throws SQLException handling an error when closing a connection
     */
    public void saveContracts(Repository<Contract> repository) throws SQLException {
        connect = new Connect();
        for (Contract contract: repository.getAll()) {
            if (contract != null) {
                if (checkContract(contract)) {
                    if (contract.getClient() != null) {
                        saveClient(contract.getClient());
                        try {
                            PreparedStatement preparedStatement = connect.getConnection().prepareStatement(INSERT_CONTRACTS);

                            preparedStatement.setInt(1, contract.getId());
                            preparedStatement.setDate(2, Date.valueOf(contract.getStartDate()));
                            preparedStatement.setDate(3, Date.valueOf(contract.getEndDate()));
                            preparedStatement.setInt(4, contract.getNumberContract());
                            preparedStatement.setInt(11, contract.getClient().getId());
                            preparedStatement = setSpecificFields(preparedStatement, contract);

                            if (preparedStatement != null) {
                                preparedStatement.executeUpdate();
                                logger.info("Contract " + contract.toString() + " saved");
                            }
                        } catch (SQLException exception) {
                            logger.error("Error saveContracts", exception);
                            exception.printStackTrace();
                        }
                    } else {
                        logger.error("Error: client null");
                    }
                } else {
                    logger.info("Contract " + contract.toString() + " already exists");
                }
            } else {
                logger.error("Error: contract null");
            }
        }
        connect.getConnection().close();
    }

    /**
     * Method saving client in database
     * @param client client for saving
     */
    public void saveClient(Client client) {
        if (checkClient(client)) {
            try {
                PreparedStatement preparedStatement = connect.getConnection().prepareStatement(INSERT_CLIENTS);

                preparedStatement.setInt(1, client.getId());
                preparedStatement.setString(2, client.getSurname());
                preparedStatement.setString(3, client.getFirstName());
                preparedStatement.setString(4, client.getPatronymic());
                preparedStatement.setDate(5, Date.valueOf(client.getDateOfBirth()));
                preparedStatement.setString(6, client.getGender().toString());
                preparedStatement.setInt(7, client.getNumberPassport());
                preparedStatement.setInt(8, client.getSeriesPassport());

                preparedStatement.executeUpdate();

                logger.info("Client" + client.toString() + " saved");
            } catch (SQLException exception) {
                logger.error("Error saveClient", exception);
                exception.printStackTrace();
            }
        } else {
            logger.info("Client " + client.toString() + " already exists");
        }
    }

    /**
     * Method checking existences client in database
     * @param client client for checking
     */
    public boolean checkClient (Client client) {
        Integer clientId = null;
        try {
            PreparedStatement preparedStatement = connect.getConnection().prepareStatement(CHECK_EXISTENCE_CLIENT);
            preparedStatement.setInt(1, client.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                clientId = resultSet.getInt(1);
            }
            logger.info("Checking client was successfully");
        } catch (SQLException exception) {
            logger.error("Error checking client", exception);
            exception.printStackTrace();
        }
        return clientId == null;
    }

    /**
     * Method checking existences contract in database
     * @param contract contract for checking
     */
    public boolean checkContract (Contract contract) {
        Integer contractId = null;
        try {
            PreparedStatement preparedStatement = connect.getConnection().prepareStatement(CHECK_EXISTENCE_CONTRACT);
            preparedStatement.setInt(1, contract.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                contractId = resultSet.getInt(1);
            }
            logger.info("Checking contract was successfully");
        } catch (SQLException exception) {
            logger.error("Error checking contract", exception);
            exception.printStackTrace();
        }
        return contractId == null;
    }

    /**
     * Method setting specific fields in query for saving contract
     * @param preparedStatement preparedStatement to database
     * @param contract contract for saving
     */
    private PreparedStatement setSpecificFields(PreparedStatement preparedStatement, Contract contract) {
        try {
            if (contract instanceof InternetContract) {
                preparedStatement.setNull(5, Types.INTEGER);
                preparedStatement.setNull(6, Types.INTEGER);
                preparedStatement.setNull(7, Types.INTEGER);
                preparedStatement.setInt(8, ((InternetContract) contract).getMaximumSpeed());
                preparedStatement.setNull(9, Types.VARCHAR);
                preparedStatement.setString(10, TypeContract.Internet.toString());
                logger.info("Setting specific fields for internet contract was successfully");
                return preparedStatement;
            } else if (contract instanceof CellularContract) {
                preparedStatement.setInt(5, ((CellularContract) contract).getMinutes());
                preparedStatement.setInt(6, ((CellularContract) contract).getGbInternet());
                preparedStatement.setInt(7, ((CellularContract) contract).getSms());
                preparedStatement.setNull(8, Types.INTEGER);
                preparedStatement.setNull(9, Types.VARCHAR);
                preparedStatement.setString(10, TypeContract.Cellular.toString());
                logger.info("Setting specific fields for cellular contract was successfully");
                return preparedStatement;
            } else if (contract instanceof TvContract){
                preparedStatement.setNull(5, Types.INTEGER);
                preparedStatement.setNull(6, Types.INTEGER);
                preparedStatement.setNull(7, Types.INTEGER);
                preparedStatement.setNull(8, Types.INTEGER);
                preparedStatement.setString(9, ((TvContract) contract).getPackageChannel().toString());
                preparedStatement.setString(10, TypeContract.TV.toString());
                logger.info("Setting specific fields for tv contract was successfully");
                return preparedStatement;
            }
        } catch (SQLException exception) {
            logger.error("Error setting specific fields", exception);
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Method getting database dump in contacts repository
     * @param contractRepository contacts repository for saving dump
     * @throws SQLException handling an error when closing a connection
     */
    public void dump (Repository<Contract> contractRepository) throws SQLException {
        connect = new Connect();
        try {
            PreparedStatement preparedStatement = connect.getConnection().prepareStatement(SELECT_CONTRACTS);
            ResultSet resultSetContract = preparedStatement.executeQuery();
            while (resultSetContract.next()) {
                if (Objects.equals(resultSetContract.getString(10), TypeContract.Internet.toString())) {
                    InternetContract internetContract = new InternetContract(
                            resultSetContract.getInt(1),
                            String.valueOf(resultSetContract.getDate(2)),
                            String.valueOf(resultSetContract.getDate(3)),
                            resultSetContract.getInt(4),
                            getClient(resultSetContract.getInt(11)),
                            resultSetContract.getInt(8)
                    );
                    contractRepository.add(internetContract);
                } else if (Objects.equals(resultSetContract.getString(10), TypeContract.Cellular.toString())) {
                    CellularContract cellularContract = new CellularContract(
                            resultSetContract.getInt(1),
                            String.valueOf(resultSetContract.getDate(2)),
                            String.valueOf(resultSetContract.getDate(3)),
                            resultSetContract.getInt(4),
                            getClient(resultSetContract.getInt(11)),
                            resultSetContract.getInt(5),
                            resultSetContract.getInt(6),
                            resultSetContract.getInt(7)
                    );
                    contractRepository.add(cellularContract);
                } else if (Objects.equals(resultSetContract.getString(10), TypeContract.TV.toString())){
                    TvContract tvContract = new TvContract(
                            resultSetContract.getInt(1),
                            String.valueOf(resultSetContract.getDate(2)),
                            String.valueOf(resultSetContract.getDate(3)),
                            resultSetContract.getInt(4),
                            getClient(resultSetContract.getInt(11)),
                            resultSetContract.getString(9)
                    );
                    contractRepository.add(tvContract);
                }
            }
        } catch (SQLException exception) {
            logger.error("Error getting dump", exception);
            exception.printStackTrace();
        }
        connect.getConnection().close();
        logger.info("Getting database dump in contacts repository was successfully");
    }

    /**
     * Method getting client by client id
     * @param clientId client id for getting client
     * @return client by client id
     */
    public Client getClient (final Integer clientId) throws SQLException {
        try {
            PreparedStatement preparedStatement = connect.getConnection().prepareStatement(CHECK_EXISTENCE_CLIENT);
            preparedStatement.setInt(1, clientId);
            ResultSet resultSetClient = preparedStatement.executeQuery();


            while (resultSetClient.next()) {
                return new Client(
                        resultSetClient.getInt(1),
                        resultSetClient.getString(2),
                        resultSetClient.getString(3),
                        resultSetClient.getString(4),
                        String.valueOf(resultSetClient.getDate(5)),
                        resultSetClient.getString(6),
                        resultSetClient.getInt(7),
                        resultSetClient.getInt(8));
            }
        } catch (SQLException exception) {
            logger.error("Error getting client", exception);
            exception.printStackTrace();
        }
        return null;
    }
}
