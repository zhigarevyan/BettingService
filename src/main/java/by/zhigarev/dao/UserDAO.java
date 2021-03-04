package by.zhigarev.dao;

import by.zhigarev.bean.SignUpInfo;
import by.zhigarev.bean.SignInInfo;
import by.zhigarev.bean.User;
import by.zhigarev.dao.exception.DAOException;

public interface UserDAO {
    User signIn(SignInInfo signUpInfo) throws DAOException;
    boolean signUp(SignUpInfo signUpInfo) throws DAOException;
}
