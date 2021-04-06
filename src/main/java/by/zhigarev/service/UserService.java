package by.zhigarev.service;

import by.zhigarev.bean.info.SignInInfo;
import by.zhigarev.bean.info.SignUpInfo;
import by.zhigarev.bean.User;
import by.zhigarev.dao.exception.DuplicateLoginException;
import by.zhigarev.service.exception.ServiceException;

public interface UserService {
    User signIn(SignInInfo signUpInfo) throws ServiceException;
    User signUp(SignUpInfo signUpInfo) throws ServiceException, DuplicateLoginException;
    boolean changeUser(User user) throws ServiceException, DuplicateLoginException;
    boolean changePasswordById(int userId, String newPassword) throws ServiceException;
}
