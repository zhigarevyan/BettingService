package by.zhigarev.dao.impl;

import by.zhigarev.bean.SignInInfo;
import by.zhigarev.bean.SignUpInfo;
import by.zhigarev.bean.User;
import by.zhigarev.dao.UserDAO;
import by.zhigarev.dao.connection.ConnectionPool;
import by.zhigarev.dao.connection.impl.ConnectionPoolImpl;
import by.zhigarev.dao.exception.DAOException;


import java.sql.*;

public class UserDAOImpl implements UserDAO {

    private static final String MESSAGE_SIGN_UP_EXCEPTION = "Exception with signUp";
    private static final String MESSAGE_SIGN_IN_EXCEPTION = "Exception with signIn";

    private static final String SIGN_UP_SQL = "insert into Users(login,password,name,surname,email,birthday,role) values (?,?,?,?,?,?,?)";

    private static final String GET_USER_BY_LOGIN_SQL = "select * from Users where login = ?";

    private static final UserDAOImpl instance = new UserDAOImpl();
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    public static UserDAOImpl getInstance() {
        return instance;
    }

    @Override
    public User signIn(SignInInfo signInInfo) throws DAOException {
        User user = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(GET_USER_BY_LOGIN_SQL);

            ps.setString(SignUpIndexes.LOGIN,signInInfo.getLogin());

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String password = rs.getString(UserParams.PASSWORD);

                if(!signInInfo.getPassword().equals(password)){
                    return null;
                }

                user = new User(rs.getInt(UserParams.id),
                        rs.getString(UserParams.LOGIN),
                        rs.getString(UserParams.PASSWORD),
                        rs.getString(UserParams.NAME),
                        rs.getString(UserParams.SURNAME),
                        rs.getString(UserParams.EMAIL),
                        rs.getDate(UserParams.BIRTHDAY),
                        rs.getInt(UserParams.ROLE));

            }

        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SIGN_IN_EXCEPTION,e);
        }
        finally {
            connectionPool.closeConnection(connection,ps);
        }
        return user;
    }

    @Override
    public boolean signUp(SignUpInfo signUpInfo) throws DAOException {
        final int role = 1;

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SIGN_UP_SQL);
            ps.setString(SignUpIndexes.LOGIN, signUpInfo.getLogin());
            ps.setString(SignUpIndexes.PASSWORD, signUpInfo.getPassword());
            ps.setString(SignUpIndexes.NAME, signUpInfo.getName());
            ps.setString(SignUpIndexes.SURNAME, signUpInfo.getSurName());
            ps.setString(SignUpIndexes.EMAIL, signUpInfo.getEmail());
            ps.setDate(SignUpIndexes.BIRTHDAY, new Date(signUpInfo.getBirthdayDate().getTime()));
            ps.setInt(SignUpIndexes.ROLE, role);
            ps.execute();

            return true;

        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SIGN_UP_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }


    private static class SignUpIndexes {
        private static final int LOGIN = 1;
        private static final int PASSWORD = 2;
        private static final int NAME = 3;
        private static final int SURNAME = 4;
        private static final int EMAIL = 5;
        private static final int BIRTHDAY = 6;
        private static final int ROLE = 7;
    }

    private static class UserParams {
        private static final int id = 1;
        private static final int LOGIN = 2;
        private static final int PASSWORD = 3;
        private static final int NAME = 4;
        private static final int SURNAME = 5;
        private static final int EMAIL = 6;
        private static final int BIRTHDAY = 7;
        private static final int ROLE = 8;
    }

    public static void main(String[] args) {
        UserDAO userDAO = UserDAOImpl.getInstance();
        try {
            //userDAO.signUp(new SignUpInfo("zhigarevyan","zhigarevyan","yan","zhigarev","zhigarevyan@gmail.com",new java.util.Date()));
            System.out.println(userDAO.signIn(new SignInInfo("zhigarevyan","zhigarevyan")));
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

}
