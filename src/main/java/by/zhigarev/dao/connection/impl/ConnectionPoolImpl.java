package by.zhigarev.dao.connection.impl;

import by.zhigarev.dao.connection.ConnectionPool;
import by.zhigarev.dao.exception.ConnectionPoolInitError;
import by.zhigarev.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPoolImpl implements ConnectionPool {
    private static final ConnectionPoolImpl instance = new ConnectionPoolImpl();

    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final int CONNECTION_COUNT = 20;

    private final String DB_RESOURCE_PATH = "db";
    private final String DB_URL = "db.url";
    private final String DB_USER = "db.user";
    private final String DB_PASSWORD = "db.password";

    private final String MESSAGE_DRIVER_NOT_FOUND = "Driver not found";
    private final String MESSAGE_SQL_PROBLEM = "Sql connection problem";
    private final String MESSAGE_NO_FREE_CONNECTION = "No free connection";
    private final String MESSAGE_CONNECTION_CLOSING_PROBLEM = "Closing connection problem.";


    private BlockingQueue<Connection> activeConnections;
    private BlockingQueue<Connection> freeConnections;

    private ConnectionPoolImpl() {
        initPool();
    }

    public static ConnectionPoolImpl getInstance(){ return instance;}

    private void initPool() throws ConnectionPoolInitError {

        ResourceBundle bundle = ResourceBundle.getBundle(DB_RESOURCE_PATH);

        String url = bundle.getString(DB_URL);
        String user =bundle.getString(DB_USER);
        String password =bundle.getString(DB_PASSWORD);

        freeConnections = new ArrayBlockingQueue<>(CONNECTION_COUNT);
        activeConnections = new ArrayBlockingQueue<>(CONNECTION_COUNT);

        try {
            Class.forName(DRIVER);

            for (int i = 0; i < CONNECTION_COUNT; i++) {
                Connection connection;
                connection = DriverManager.getConnection(url, user, password);

                freeConnections.add(connection);
            }
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolInitError(MESSAGE_DRIVER_NOT_FOUND, e);
        } catch (SQLException e) {
            throw new ConnectionPoolInitError(MESSAGE_SQL_PROBLEM, e);
        }
    }


    @Override
    public Connection getConnection() throws DAOException {
        Connection connection;
        connection = freeConnections.poll();
        if(connection ==null){
            throw new DAOException(MESSAGE_NO_FREE_CONNECTION);
        }

        activeConnections.add(connection);

        return connection;
    }

    public void releaseConnection(Connection connection) {

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

    @Override
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
