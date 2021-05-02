package by.zhigarev.dao.impl;


import by.zhigarev.bean.OutcomeType;
import by.zhigarev.dao.OutcomeTypeDAO;
import by.zhigarev.dao.connection.ConnectionPool;
import by.zhigarev.dao.connection.impl.ConnectionPoolImpl;
import by.zhigarev.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OutcomeTypeDAOImpl implements OutcomeTypeDAO {
    private static final String SQL_SELECT_TYPE_BY_ID = "select * from outcometypes where id = ?";
    private static final String SQL_SELECT_ALL = "select * from outcometypes";
    private static final String SQL_INSERT = "insert into outcometypes(title) values(?)";
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
    private static final String MESSAGE_SQL_EXCEPTION = "SQL exception dao layer";
    private static final OutcomeTypeDAOImpl instance = new OutcomeTypeDAOImpl();

    public static OutcomeTypeDAOImpl getInstance() {
        return instance;
    }

    @Override
    public OutcomeType getTypeById(int id) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        OutcomeType outcomeType = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_SELECT_TYPE_BY_ID);
            ps.setInt(OutcomeTypeIndexes.ID, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                outcomeType = new OutcomeType(rs.getInt(OutcomeTypeIndexes.ID), rs.getString(OutcomeTypeIndexes.TYPE));
            }
            return outcomeType;
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    @Override
    public List<OutcomeType> getAllTypes() throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        List<OutcomeType> outcomeTypes = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OutcomeType outcomeType = new OutcomeType(
                        rs.getInt(OutcomeTypeIndexes.ID),
                        rs.getString(OutcomeTypeIndexes.TYPE));
                outcomeTypes.add(outcomeType);
            }
            return outcomeTypes;
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    @Override
    public void addType(String type) throws DAOException {
        final int TYPE_INSERT_INDEX = 1;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_INSERT);
            ps.setString(TYPE_INSERT_INDEX, type);
            ps.execute();
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    private static class OutcomeTypeIndexes {
        private static final int ID = 1;
        private static final int TYPE = 2;
    }
}
