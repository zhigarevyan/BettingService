package by.zhigarev.dao;

import by.zhigarev.dao.impl.UserDAOImpl;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    public static DAOProvider getInstance() {
        return instance;
    }

    private static final UserDAO userDAO = UserDAOImpl.getInstance();

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
