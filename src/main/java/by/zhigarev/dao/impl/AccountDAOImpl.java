package by.zhigarev.dao.impl;

import by.zhigarev.bean.Account;
import by.zhigarev.bean.User;
import by.zhigarev.dao.AccountDAO;
import by.zhigarev.dao.connection.ConnectionPool;
import by.zhigarev.dao.connection.impl.ConnectionPoolImpl;
import by.zhigarev.dao.exception.DAOException;

import java.sql.*;
import java.util.Date;

public class AccountDAOImpl implements AccountDAO {

    private static final String MESSAGE_CANT_GET_ACCOUNT = "Cant get account for this user";
    private static final String MESSAGE_CANT_CREATE_ACCOUNT = "Cant create account for this user";
    private static final String MESSAGE_SQL_EXCEPTION = "Sql Exception on DAO layer";

    private static final String SQL_GET_ACCOUNT_BY_USER = "select * from Accounts where user= ? ";
    private static final String SQL_WRITE_OFF_MONEY = "update accounts set balance = balance - ? where id = ?;";
    private static final String SQL_PAY_MONEY = "update accounts set balance = balance + ? where id = ?;";
    private static final String SQL_GET_ACCOUNT_BY_ID = "select * from Accounts where id = ? ";
    private static final String SQL_CREATE_ACCOUNT_BY_USER = "insert into Accounts(user,balance,creation_date) values(?,?,?)";

    private static final AccountDAO instance = new AccountDAOImpl();
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    public static AccountDAO getInstance() {
        return instance;
    }

    @Override
    public Account getAccountByUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        Account account = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_GET_ACCOUNT_BY_USER);
            ps.setString(AccountBeanIndexes.ID_INDEX, String.valueOf(user.getId()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                account = new Account(rs.getInt(AccountBeanIndexes.ID_INDEX),
                        rs.getInt(AccountBeanIndexes.USER_ID_INDEX),
                        rs.getDouble(AccountBeanIndexes.BALANCE_INDEX),
                        rs.getDate(AccountBeanIndexes.CREATION_DATE_INDEX));
            }
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION,e);
        }finally {
            connectionPool.closeConnection(connection,ps);
        }
        return account;
    }

    @Override
    public Account getAccountById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        Account account = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_GET_ACCOUNT_BY_ID);
            ps.setString(AccountBeanIndexes.ID_INDEX, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                account = new Account(rs.getInt(AccountBeanIndexes.ID_INDEX),
                        rs.getInt(AccountBeanIndexes.USER_ID_INDEX),
                        rs.getDouble(AccountBeanIndexes.BALANCE_INDEX),
                        rs.getDate(AccountBeanIndexes.CREATION_DATE_INDEX));
            }
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION,e);
        }finally {
            connectionPool.closeConnection(connection,ps);
        }
        return account;
    }

    @Override
    public Account createAccountByUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_CREATE_ACCOUNT_BY_USER);
            ps.setInt(CreateAccountValues.USER_ID_INDEX, user.getId());
            ps.setDouble(CreateAccountValues.BALANCE_INDEX,CreateAccountValues.BALANCE);
            ps.setDate(CreateAccountValues.CREATION_DATE_INDEX, new java.sql.Date(new Date().getTime()));
            ps.execute();
            return getAccountByUser(user);
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION,e);
        }finally {
            connectionPool.closeConnection(connection,ps);
        }
    }

    @Override
    public boolean writeOffMoneyByAccountId(int accountId, double amount) throws DAOException {
        int AMOUNT_INDEX = 1;
        int ACCOUNT_ID_INDEX = 2;

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_WRITE_OFF_MONEY);
            ps.setDouble(AMOUNT_INDEX,amount);
            ps.setInt(ACCOUNT_ID_INDEX,accountId);
            return ps.execute();

        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION,e);
        }finally {
            connectionPool.closeConnection(connection,ps);
        }

    }

    @Override
    public boolean payMoneyByAccountId(int accountId, double amount) throws DAOException {
        int AMOUNT_INDEX = 1;
        int ACCOUNT_ID_INDEX = 2;

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_PAY_MONEY);
            ps.setDouble(AMOUNT_INDEX,amount);
            ps.setInt(ACCOUNT_ID_INDEX,accountId);
            return ps.execute();

        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION,e);
        }finally {
            connectionPool.closeConnection(connection,ps);
        }
    }


    private static class AccountBeanIndexes {
        private static int ID_INDEX = 1;
        private static int USER_ID_INDEX = 2;
        private static int BALANCE_INDEX = 3;
        private static int CREATION_DATE_INDEX = 4;
    }
    private static class CreateAccountValues {
        private static int USER_ID_INDEX = 1;
        private static int BALANCE_INDEX = 2;
        private static int CREATION_DATE_INDEX = 3;
        private static double BALANCE = 0;
    }

}
