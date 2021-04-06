package by.zhigarev.dao;

import by.zhigarev.bean.info.SignUpInfo;
import by.zhigarev.bean.info.SignInInfo;
import by.zhigarev.bean.User;
import by.zhigarev.dao.exception.DAOException;
import by.zhigarev.dao.exception.DuplicateLoginException;

public interface UserDAO {
    User signIn(SignInInfo signUpInfo) throws DAOException;
    User signUp(SignUpInfo signUpInfo) throws DAOException, DuplicateLoginException;
    boolean changeUser(User user) throws DAOException, DuplicateLoginException;
    boolean changePasswordById(int userId, String newPassword) throws DAOException;
    boolean checkLogin(String login) throws DAOException;
}
