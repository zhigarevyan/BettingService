package by.zhigarev.dao.impl;

import by.zhigarev.bean.Account;
import by.zhigarev.bean.User;
import by.zhigarev.bean.info.UserBetInfo;
import by.zhigarev.dao.UserBetDAO;
import by.zhigarev.dao.connection.ConnectionPool;
import by.zhigarev.dao.connection.impl.ConnectionPoolImpl;
import by.zhigarev.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserBetDAOImpl implements UserBetDAO {
    ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
    private static final UserBetDAOImpl instance = new UserBetDAOImpl();
    private static final String MESSAGE_SQL_EXCEPTION = "sql exception on dao layer";

    private static final String SQL_ADD_USER_BET = "insert into userbets(account_id,bet_id,timestamp,bet_amount) values(?,?,?,?)";
    private static final String SQL_GET_ALL_USER_BETS = "select * from userbets where account_id = ? order by timestamp desc";
    private static final String SQL_GET_ALL_USER_BETS_BY_BET_ID = "select * from userbets where bet_id = ?";
    private static final String SQL_GET_USER_BET_BY_ID = "select * from userbets where id = ?";

    public static UserBetDAOImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addUserBet(UserBetInfo userBetInfo) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_ADD_USER_BET);
            ps.setInt(UserBetInsertIndexes.ACCOUNT_ID,userBetInfo.getAccountId());
            ps.setInt(UserBetInsertIndexes.BET_ID,userBetInfo.getBetId());
            ps.setDate(UserBetInsertIndexes.TIMESTAMP, new java.sql.Date(userBetInfo.getTimeStamp().getTime()));
            ps.setDouble(UserBetInsertIndexes.BET_AMOUNT,userBetInfo.getBetAmount());
            return ps.execute();
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    @Override
    public List<UserBetInfo> getAllAccountBets(Account account) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        List<UserBetInfo> userBets = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_GET_ALL_USER_BETS);
            ps.setInt(UserBetIndexes.ID,account.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                UserBetInfo userBetInfo = null;

                userBetInfo = new UserBetInfo(
                  rs.getInt(UserBetIndexes.ID),
                  rs.getInt(UserBetIndexes.ACCOUNT_ID),
                  rs.getInt(UserBetIndexes.BET_ID),
                  rs.getDate(UserBetIndexes.TIMESTAMP),
                  rs.getDouble(UserBetIndexes.BET_AMOUNT)
                );
                userBets.add(userBetInfo);
            }
            return userBets;

        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    @Override
    public UserBetInfo getUserBetById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        UserBetInfo userBetInfo = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_GET_USER_BET_BY_ID);
            ps.setInt(UserBetIndexes.ID,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                userBetInfo = new UserBetInfo(
                        rs.getInt(UserBetIndexes.ID),
                        rs.getInt(UserBetIndexes.ACCOUNT_ID),
                        rs.getInt(UserBetIndexes.BET_ID),
                        rs.getDate(UserBetIndexes.TIMESTAMP),
                        rs.getDouble(UserBetIndexes.BET_AMOUNT)
                );
            }
          return userBetInfo;

        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    @Override
    public List<UserBetInfo> getAllBetsByBetId(int betId) throws DAOException {
        int BET_ID = 1;
        Connection connection = null;
        PreparedStatement ps = null;
        List<UserBetInfo> userBets = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_GET_ALL_USER_BETS_BY_BET_ID);
            ps.setInt(BET_ID,betId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserBetInfo userBetInfo = null;
                userBetInfo = new UserBetInfo(
                        rs.getInt(UserBetIndexes.ID),
                        rs.getInt(UserBetIndexes.ACCOUNT_ID),
                        rs.getInt(UserBetIndexes.BET_ID),
                        rs.getDate(UserBetIndexes.TIMESTAMP),
                        rs.getDouble(UserBetIndexes.BET_AMOUNT)
                );
                userBets.add(userBetInfo);
            }
            return userBets;
        }catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    private static final class UserBetInsertIndexes {
        private static final int ACCOUNT_ID = 1;
        private static final int BET_ID = 2;
        private static final int TIMESTAMP = 3;
        private static final int BET_AMOUNT = 4;
    }

    private static final class UserBetIndexes {
        private static final int ID = 1;
        private static final int ACCOUNT_ID = 2;
        private static final int BET_ID = 3;
        private static final int TIMESTAMP = 4;
        private static final int BET_AMOUNT = 5;
    }
}
