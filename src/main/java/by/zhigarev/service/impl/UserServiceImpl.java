package by.zhigarev.service.impl;

import by.zhigarev.bean.SignInInfo;
import by.zhigarev.bean.SignUpInfo;
import by.zhigarev.bean.User;
import by.zhigarev.dao.DAOProvider;
import by.zhigarev.dao.UserDAO;
import by.zhigarev.dao.exception.DAOException;
import by.zhigarev.service.UserService;
import by.zhigarev.service.exception.ServiceException;
import by.zhigarev.service.validator.UserValidator;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
    private static final UserService instance = new UserServiceImpl();

    private static final String MESSAGE_SIGN_IN_VALIDATION_EXCEPTION = "SignIn validation exception.Wrong login or password";
    private static final String MESSAGE_SIGN_IN_EXCEPTION = "SignIn exception";
    private static final String MESSAGE_SIGN_UP_EXCEPTION = "SignUp exception";
    private static final String MESSAGE_SIGN_UP_VALIDATION_EXCEPTION = "SignUp validation exception.Wrong fields";

    public static UserService getInstance() {
        return instance;
    }

    @Override
    public User signIn(SignInInfo signInInfo) throws ServiceException {
        if(!UserValidator.isValidUserSignIn(signInInfo)){
            throw new ServiceException(MESSAGE_SIGN_IN_VALIDATION_EXCEPTION);
        }
        User user = null;
        try{
            user = userDAO.signIn(signInInfo);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_SIGN_IN_EXCEPTION,e);
        }
        return user;
    }

    @Override
    public boolean signUp(SignUpInfo signUpInfo) throws ServiceException {
        if(!UserValidator.isValidUserSignUp(signUpInfo)){
            throw new ServiceException(MESSAGE_SIGN_UP_VALIDATION_EXCEPTION);
        }
        try {
            return userDAO.signUp(signUpInfo);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_SIGN_UP_EXCEPTION,e);
        }
    }
}
