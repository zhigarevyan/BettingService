package by.zhigarev.dao.impl;


import by.zhigarev.bean.BetStatus;
import by.zhigarev.dao.BetStatusDAO;
import by.zhigarev.dao.connection.ConnectionPool;
import by.zhigarev.dao.connection.impl.ConnectionPoolImpl;
import by.zhigarev.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BetStatusDAOImpl implements BetStatusDAO {
    private static final String SQL_SELECT_STATUS_BY_ID = "select * from BetStatuses where id = ?";
    private static final String SQL_SELECT_ALL_STATUSES = "select * from BetStatuses";
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
    private static final String MESSAGE_SQL_EXCEPTION = "SQL exception dao layer";
    private static final String MESSAGE_SELECT_STATUS_EXCEPTION = "cant select status";
    private static final BetStatusDAOImpl instance = new BetStatusDAOImpl();

    public static BetStatusDAOImpl getInstance() {
        return instance;
    }

    @Override
    public BetStatus getStatusById(int id) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        BetStatus betStatus = null;
        try{
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_SELECT_STATUS_BY_ID);
            ps.setInt(BetStatusIndexes.ID,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                betStatus = new BetStatus(rs.getInt(BetStatusIndexes.ID),rs.getString(BetStatusIndexes.STATUS));
            }
            return betStatus;
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION,e);
        }finally {
            connectionPool.closeConnection(connection,ps);
        }
    }

    @Override
    public List<BetStatus> getAllStatuses() throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        List<BetStatus> betStatusList = new ArrayList<>();
        try{
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_SELECT_ALL_STATUSES);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                BetStatus betStatus = new BetStatus(
                        rs.getInt(BetStatusIndexes.ID),
                        rs.getString(BetStatusIndexes.STATUS));
                betStatusList.add(betStatus);
            }
            return betStatusList;
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION,e);
        }finally {
            connectionPool.closeConnection(connection,ps);
        }
    }

    private static class BetStatusIndexes {
        private static final int ID = 1;
        private static final int STATUS = 2;
    }
}
