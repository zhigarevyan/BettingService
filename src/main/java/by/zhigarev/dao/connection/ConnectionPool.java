package by.zhigarev.dao.connection;

import by.zhigarev.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface ConnectionPool {
    Connection getConnection() throws DAOException;

    void closeConnection(Connection connection, PreparedStatement ps) throws DAOException;
}
