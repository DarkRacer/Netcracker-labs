package com.nc.labs.repository;

import com.nc.labs.entity.Client;
import com.nc.labs.enums.Gender;

import java.time.LocalDate;

/**
 * This class describes a repository for storing various clients
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class ClientRepository {
    /**
     * Array for storing clients
     */
    private static Client[] arrayClient = new Client[10];

    /**
     * Number of clients added
     */
    private static int pointer = 0;

    /**
     * This method creates a new client in the repository and returns it or returns an existing one
     * @param id id client
     * @param surname surname client
     * @param firstName firstName client
     * @param patronymic patronymic client
     * @param dateOfBirth dateOfBirth client
     * @param gender gender client
     * @param numberPassport numberPassport client
     * @param seriesPassport seriesPassport client
     * @return new client or existing one
     */
    public Client createClient(int id, String surname, String firstName, String patronymic, LocalDate dateOfBirth,
                               Gender gender, int numberPassport, int seriesPassport) {
        Client client = new Client(id, surname, firstName, patronymic, dateOfBirth, gender,
                numberPassport, seriesPassport);

            if (pointer != 0) {
                Client client1 = check(client);

                if (pointer == (arrayClient.length - 1)) {
                    resize();
                }

                if (client1 == null) {
                    arrayClient[pointer] = client;
                    pointer++;
                    return client;
                }
                else {
                    return client1;
                }
            }
            else {
                arrayClient[pointer] = client;
                pointer++;

                return client;
            }
    }


    /**
     * The method expands the repository
     */
    private void resize() {
        Client[] array = new Client[pointer + 5];

        System.arraycopy(arrayClient, 0, array, 0, pointer);
        arrayClient = array;
    }

    /**
     * This method checks the client for duplication
     * @param client the client to check
     * @return client in repository or null
     */
    public Client check(Client client) {

        for (Client client1 : arrayClient) {
            if (client1 != null)
            if (client1.getNumberPassport() == client.getNumberPassport()
                    && client1.getSeriesPassport() == client.getSeriesPassport()) {
                return client1;
            }

        }
        return null;
    }
}
