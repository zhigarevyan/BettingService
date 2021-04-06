package by.zhigarev.dao.impl;

import by.zhigarev.bean.info.SignInInfo;
import by.zhigarev.bean.info.SignUpInfo;
import by.zhigarev.bean.User;
import by.zhigarev.dao.UserDAO;
import by.zhigarev.dao.connection.ConnectionPool;
import by.zhigarev.dao.connection.impl.ConnectionPoolImpl;
import by.zhigarev.dao.exception.DAOException;
import by.zhigarev.dao.exception.DuplicateLoginException;


import java.sql.*;

public class UserDAOImpl implements UserDAO {

    private static final String MESSAGE_SQL_EXCEPTION = "SQLException on DAO layer";
    private static final String MESSAGE_CANT_GET_USER_EXCEPTION = "Cant get user with this id";
    private static final String MESSAGE_CANT_CREATE_USER_EXCEPTION = "Cant create user";

    private static final String SQL_SIGN_UP = "insert into Users(login,password,name,surname,email,birthday,role) values (?,?,?,?,?,?,?)";
    private static final String SQL_GET_USER_BY_LOGIN = "select * from Users where login = ?";
    private static final String SQL_CHANGE_USER = "update users set login = ?, name = ?, surname= ?, email = ?, birthday  =? where id = ?";
    private static final String SQL_CHANGE_PASSWORD = "update users set password  = ? where id = ?";
    private static final String SQL_CHECK_LOGIN = "select * from users where login = ?";

    private static final int DUPLICATE_LOGIN_ERROR_CODE = 1062;

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

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_GET_USER_BY_LOGIN);

            ps.setString(SignUpIndexes.LOGIN, signInInfo.getLogin());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String password = rs.getString(UserParams.PASSWORD);

                if (!signInInfo.getPassword().equals(password)) {
                    return null;
                }

                user = new User(rs.getInt(UserParams.ID),
                        rs.getString(UserParams.LOGIN),
                        rs.getString(UserParams.PASSWORD),
                        rs.getString(UserParams.NAME),
                        rs.getString(UserParams.SURNAME),
                        rs.getString(UserParams.EMAIL),
                        rs.getDate(UserParams.BIRTHDAY),
                        rs.getInt(UserParams.ROLE));

            }
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
        return user;
    }

    @Override
    public User signUp(SignUpInfo signUpInfo) throws DAOException, DuplicateLoginException {
        final int DEFAULT_ROLE = 1;
        User user = null;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_SIGN_UP);
            ps.setString(SignUpIndexes.LOGIN, signUpInfo.getLogin());
            ps.setString(SignUpIndexes.PASSWORD, signUpInfo.getPassword());
            ps.setString(SignUpIndexes.NAME, signUpInfo.getName());
            ps.setString(SignUpIndexes.SURNAME, signUpInfo.getSurName());
            ps.setString(SignUpIndexes.EMAIL, signUpInfo.getEmail());
            ps.setDate(SignUpIndexes.BIRTHDAY, new Date(signUpInfo.getBirthdayDate().getTime()));
            ps.setInt(SignUpIndexes.ROLE, DEFAULT_ROLE);
            ps.execute();

            user = signIn(new SignInInfo(signUpInfo.getLogin(), signUpInfo.getPassword()));
        } catch (SQLException e) {
            if(e.getErrorCode() == DUPLICATE_LOGIN_ERROR_CODE) {
                throw new DuplicateLoginException(MESSAGE_SQL_EXCEPTION);
            }else {
                throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
            }
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
        return user;
    }

    @Override
    public boolean changeUser(User user) throws DAOException, DuplicateLoginException {
        int NAME_INDEX = 2;
        int SURNAME_INDEX =3;
        int EMAIL_INDEX =4;
        int BIRTHDAY_INDEX =5;
        int ID_INDEX =6;

        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_CHANGE_USER);
            ps.setString(SignUpIndexes.LOGIN,user.getLogin());
            ps.setString(NAME_INDEX,user.getName());
            ps.setString(SURNAME_INDEX,user.getSurName());
            ps.setString(EMAIL_INDEX,user.getEmail());
            ps.setDate(BIRTHDAY_INDEX,new Date(user.getBirthdayDate().getTime()));
            ps.setInt(ID_INDEX,user.getId());
            return ps.execute();
        }catch (SQLException e) {
            if(e.getErrorCode() == DUPLICATE_LOGIN_ERROR_CODE) {
                throw new DuplicateLoginException(MESSAGE_SQL_EXCEPTION);
            }else {
                throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
            }
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    @Override
    public boolean changePasswordById(int userId, String newPassword) throws DAOException {
        int PASSWORD_INDEX = 1;
        int USER_ID_INDEX = 2;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_CHANGE_PASSWORD);
            ps.setString(PASSWORD_INDEX,newPassword);
            ps.setInt(USER_ID_INDEX,userId);
            return  ps.execute();
        }catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    @Override
    public boolean checkLogin(String login) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_CHECK_LOGIN);
            ps.setString(SignUpIndexes.LOGIN,login);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
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
        private static final int ID = 1;
        private static final int LOGIN = 2;
        private static final int PASSWORD = 3;
        private static final int NAME = 4;
        private static final int SURNAME = 5;
        private static final int EMAIL = 6;
        private static final int BIRTHDAY = 7;
        private static final int ROLE = 8;
    }
}
