import Managers.DatabaseConnector;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestDataConnection {


    @Test
    public void testConnectToDatabase() {
        //  connect to the database
        Connection connection = DatabaseConnector.connectToDatabase();

        // indication of successful connection
        assertNotNull(connection, "Connection should not be null");}}
