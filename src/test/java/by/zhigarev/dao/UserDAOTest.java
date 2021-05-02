package by.zhigarev.dao;

import by.zhigarev.bean.User;
import by.zhigarev.bean.info.SignInInfo;
import by.zhigarev.connection.TestConnectionPool;
import by.zhigarev.dao.exception.DAOException;
import by.zhigarev.dao.exception.WrongPasswordException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOTest {

    private static final String TEST_LOGIN = "kikikiki";
    private static final String TEST_PASS = "kikikiki";

    private static final TestConnectionPool connectionPoolTest = TestConnectionPool.getInstance();
    private static final UserDAO userDao = DAOProvider.getUserDAO();


    private static final String SQL_GET_USER_BY_LOGIN = "select * from Users where login = ?";

    private static final String MESSAGE_WRONG_PASSWORD_EXCEPTION = "Wrong password";
    private static final String MESSAGE_SQL_EXCEPTION = "SQLException on DAO layer";

    @Test
    public void signIn() throws DAOException {
        final int LOGIN = 1;
        User user = null;
        Connection connection = null;
        PreparedStatement ps = null;
        SignInInfo signInInfo = new SignInInfo(TEST_LOGIN, TEST_PASS);
        try {
            connection = connectionPoolTest.getConnection();
            ps = connection.prepareStatement(SQL_GET_USER_BY_LOGIN);

            ps.setString(LOGIN, signInInfo.getLogin());

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                String password = rs.getString(UserParams.PASSWORD);

                if (!signInInfo.getPassword().equals(password)) {
                    throw new WrongPasswordException(MESSAGE_WRONG_PASSWORD_EXCEPTION);
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
            connectionPoolTest.closeConnection(connection, ps);
        }
        User expectedUser = userDao.signIn(signInInfo);
        Assert.assertEquals(expectedUser, user);
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
