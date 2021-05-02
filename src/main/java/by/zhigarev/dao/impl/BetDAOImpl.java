package by.zhigarev.dao.impl;

import by.zhigarev.bean.Event;
import by.zhigarev.bean.info.BetInfo;
import by.zhigarev.dao.BetDAO;
import by.zhigarev.dao.connection.ConnectionPool;
import by.zhigarev.dao.connection.impl.ConnectionPoolImpl;
import by.zhigarev.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BetDAOImpl implements BetDAO {
    private static final BetDAOImpl instance = new BetDAOImpl();
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
    private static final String MESSAGE_SQL_EXCEPTION = "SQL Exception on dao layer";

    private static final String SQL_GET_ALL_EVENT_BETS = "select * from bets where event_id = ? and status = 1";
    private static final String SQL_DELETE_BET_BY_ID = "delete from bets where id = ?";
    private static final String SQL_UPDATE_BET_STATUS = "update bets set status = ? where id = ?";
    private static final String SQL_GET_BET_BY_ID = "select * from bets where id = ?";
    private static final String SQL_INSERT_EVENT_BETS = "insert into bets(event_id, outcome, status,offer) values (?,?,?,?)";

    public static BetDAOImpl getInstance() {
        return instance;
    }

    @Override
    public List<BetInfo> getAllBetsOfEvent(Event event) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        List<BetInfo> bets = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_GET_ALL_EVENT_BETS);
            ps.setInt(InsertBetIndexes.EVENT_ID, event.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BetInfo bet = new BetInfo(rs.getInt(BetIndexes.ID),
                        rs.getInt(BetIndexes.EVENT_ID),
                        rs.getInt(BetIndexes.OUTCOME),
                        rs.getInt(BetIndexes.STATUS),
                        rs.getDouble(BetIndexes.OFFER));
                bets.add(bet);
            }
            return bets;
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    public List<BetInfo> getAllBetsOfEventById(int eventId) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        List<BetInfo> bets = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_GET_ALL_EVENT_BETS);
            ps.setInt(InsertBetIndexes.EVENT_ID, eventId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BetInfo bet = new BetInfo(rs.getInt(BetIndexes.ID),
                        rs.getInt(BetIndexes.EVENT_ID),
                        rs.getInt(BetIndexes.OUTCOME),
                        rs.getInt(BetIndexes.STATUS),
                        rs.getDouble(BetIndexes.OFFER));
                bets.add(bet);
            }
            return bets;
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    @Override
    public void deleteBetById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_DELETE_BET_BY_ID);
            ps.setInt(BetIndexes.ID, id);
            ps.execute();
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    @Override
    public void createBetForEvent(BetInfo betInfo) throws DAOException {
        final int BET_STATUS = 1;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_INSERT_EVENT_BETS);
            ps.setInt(InsertBetIndexes.EVENT_ID, betInfo.getEventId());
            ps.setInt(InsertBetIndexes.OUTCOME, betInfo.getOutcome());
            ps.setInt(InsertBetIndexes.STATUS, BET_STATUS);
            ps.setDouble(InsertBetIndexes.OFFER, betInfo.getOffer());
            ps.execute();
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        }
    }

    @Override
    public BetInfo getBetById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        BetInfo bet = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_GET_BET_BY_ID);
            ps.setInt(InsertBetIndexes.EVENT_ID, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bet = new BetInfo(rs.getInt(BetIndexes.ID),
                        rs.getInt(BetIndexes.EVENT_ID),
                        rs.getInt(BetIndexes.OUTCOME),
                        rs.getInt(BetIndexes.STATUS),
                        rs.getDouble(BetIndexes.OFFER));

            }
            return bet;
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    @Override
    public void updateBetStatusById(int betId, int betStatusId) throws DAOException {
        final int BET_STATUS = 1;
        final int BET_ID = 2;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_UPDATE_BET_STATUS);
            ps.setInt(BET_STATUS, betStatusId);
            ps.setInt(BET_ID, betId);
            ps.execute();
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }


    private static class BetIndexes {
        private static final int ID = 1;
        private static final int EVENT_ID = 2;
        private static final int OUTCOME = 3;
        private static final int STATUS = 4;
        private static final int OFFER = 5;
    }

    private static class InsertBetIndexes {
        private static final int EVENT_ID = 1;
        private static final int OUTCOME = 2;
        private static final int STATUS = 3;
        private static final int OFFER = 4;
    }
}
