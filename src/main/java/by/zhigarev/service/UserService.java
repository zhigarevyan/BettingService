package by.zhigarev.service;

import by.zhigarev.bean.SignInInfo;
import by.zhigarev.bean.SignUpInfo;
import by.zhigarev.bean.User;
import by.zhigarev.dao.exception.DAOException;
import by.zhigarev.service.exception.ServiceException;

public interface UserService {
    User signIn(SignInInfo signUpInfo) throws ServiceException;
    boolean signUp(SignUpInfo signUpInfo) throws ServiceException;
}
