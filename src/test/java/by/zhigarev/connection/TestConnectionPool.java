package by.zhigarev.connection;

import by.zhigarev.dao.exception.ConnectionPoolInitError;
import by.zhigarev.dao.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestConnectionPool {
    private static final TestConnectionPool instance = new TestConnectionPool();

    private static final Logger logger = Logger.getLogger(TestConnectionPool.class);

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final int CONNECTION_COUNT = 5;

    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";

    private static final String MESSAGE_DRIVER_NOT_FOUND = "Driver not found";
    private static final String MESSAGE_SQL_PROBLEM = "Sql connection problem";
    private static final String MESSAGE_NO_FREE_CONNECTION = "No free connection";
    private static final String MESSAGE_CONNECTION_CLOSING_PROBLEM = "Closing connection problem.";
    private static final String DB_RESOURCE = "dbtest";

    private static BlockingQueue<Connection> freeConnections;
    private static BlockingQueue<Connection> activeConnections;

    private TestConnectionPool() {
        initPool();
    }

    public static TestConnectionPool getInstance() {
        return instance;
    }

    private void initPool() throws ConnectionPoolInitError {

        ResourceBundle resourceBundle = ResourceBundle.getBundle(DB_RESOURCE, Locale.getDefault());

        String url = resourceBundle.getString(DB_URL);
        String user = resourceBundle.getString(DB_USER);
        String password = resourceBundle.getString(DB_PASSWORD);


        freeConnections = new ArrayBlockingQueue<>(CONNECTION_COUNT);
        activeConnections = new ArrayBlockingQueue<>(CONNECTION_COUNT);

        try {
            Class.forName(DRIVER);

            for (int count = 0; count < CONNECTION_COUNT; count++) {

                Connection connection;
                connection = DriverManager.getConnection(url, user, password);

                freeConnections.add(connection);
            }

        } catch (ClassNotFoundException e) {

            logger.error(MESSAGE_DRIVER_NOT_FOUND, e);
            throw new ConnectionPoolInitError(MESSAGE_DRIVER_NOT_FOUND, e);

        } catch (SQLException e) {

            logger.error(MESSAGE_SQL_PROBLEM, e);
            throw new ConnectionPoolInitError(MESSAGE_SQL_PROBLEM, e);
        }
    }

    public Connection getConnection() throws DAOException {

        Connection connection;
        connection = freeConnections.poll();

        if (connection == null) {

            logger.error(MESSAGE_NO_FREE_CONNECTION);
            throw new DAOException(MESSAGE_NO_FREE_CONNECTION);
        }

        activeConnections.add(connection);

        return connection;
    }

    private void releaseConnection(Connection connection) {

        activeConnections.remove(connection);
        freeConnections.add(connection);

    }

    public void clearConnectionPool() throws DAOException {

        closeConnectionQueue(freeConnections);
        closeConnectionQueue(activeConnections);

    }

    private void closeConnectionQueue(BlockingQueue<Connection> connectionQueue) throws DAOException {

        Connection connection;

        while ((connection = connectionQueue.poll()) != null) {
            try {
                connection.close();

            } catch (SQLException e) {
                throw new DAOException(MESSAGE_CONNECTION_CLOSING_PROBLEM, e);
            }
        }

    }

    public void closeConnection(Connection connection, PreparedStatement ps) throws DAOException {
        try {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                releaseConnection(connection);
            }
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_CONNECTION_CLOSING_PROBLEM, e);
        }
    }
}
