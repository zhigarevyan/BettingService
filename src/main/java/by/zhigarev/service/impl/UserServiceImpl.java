package by.zhigarev.service.impl;

import by.zhigarev.bean.User;
import by.zhigarev.bean.info.SignInInfo;
import by.zhigarev.bean.info.SignUpInfo;
import by.zhigarev.dao.DAOProvider;
import by.zhigarev.dao.UserDAO;
import by.zhigarev.dao.exception.DAOException;
import by.zhigarev.dao.exception.DuplicateLoginException;
import by.zhigarev.dao.exception.WrongPasswordException;
import by.zhigarev.service.UserService;
import by.zhigarev.service.exception.ServiceException;
import by.zhigarev.service.exception.ValidationException;
import by.zhigarev.service.exception.WrongLoginException;
import by.zhigarev.service.validator.Validator;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
    private static final UserService instance = new UserServiceImpl();

    private static final String MESSAGE_WRONG_LOGIN_EXCEPTION = "Wrong login exception";
    private static final String MESSAGE_VALIDATION_EXCEPTION = "Validation exception";
    private static final String MESSAGE_WRONG_PASSWORD_EXCEPTION = "Wrong password exception";
    private static final String MESSAGE_SIGN_IN_VALIDATION_EXCEPTION = "SignIn validation exception.Wrong login or password";
    private static final String MESSAGE_SIGN_IN_EXCEPTION = "SignIn exception";
    private static final String MESSAGE_SIGN_UP_EXCEPTION = "SignUp exception";
    private static final String MESSAGE_CHANGE_USER_EXCEPTION = "Change user exception";
    private static final String MESSAGE_CHANGE_PASSWORD_EXCEPTION = "Change password exception";
    private static final String MESSAGE_SIGN_UP_VALIDATION_EXCEPTION = "SignUp validation exception.Wrong fields";
    private static final String MESSAGE_CHANGE_USER_VALIDATION_EXCEPTION = "Change user validation exception.Wrong fields";
    private static final String MESSAGE_DUPLICATE_LOGIN_EXCEPTION = "duplicate login exception";

    public static UserService getInstance() {
        return instance;
    }

    @Override
    public User signIn(SignInInfo signInInfo) throws ServiceException {
        if (!Validator.isValidUserSignIn(signInInfo)) {
            throw new ValidationException(MESSAGE_SIGN_IN_VALIDATION_EXCEPTION);
        }
        User user;
        try {
            user = userDAO.signIn(signInInfo);
            if (user == null) {
                throw new WrongLoginException(MESSAGE_WRONG_LOGIN_EXCEPTION);
            }
        } catch (WrongPasswordException e) {
            throw new by.zhigarev.service.exception.WrongPasswordException(MESSAGE_WRONG_PASSWORD_EXCEPTION, e);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_SIGN_IN_EXCEPTION, e);
        }
        return user;
    }

    @Override
    public User signUp(SignUpInfo signUpInfo) throws ServiceException, DuplicateLoginException {
        if (!Validator.isValidUserSignUp(signUpInfo)) {
            throw new ValidationException(MESSAGE_SIGN_UP_VALIDATION_EXCEPTION);
        }
        try {
            return userDAO.signUp(signUpInfo);
        } catch (DuplicateLoginException e) {
            throw new DuplicateLoginException(MESSAGE_DUPLICATE_LOGIN_EXCEPTION, e);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_SIGN_UP_EXCEPTION, e);
        }
    }

    @Override
    public void changeUser(User user) throws ServiceException, DuplicateLoginException {
        if (!Validator.isValidUserSignUp(new SignUpInfo(user.getName(),
                user.getSurName(),
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getBirthdayDate()))) {
            throw new ValidationException(MESSAGE_CHANGE_USER_VALIDATION_EXCEPTION);
        }
        try {
            userDAO.changeUser(user);
        } catch (DuplicateLoginException e) {
            throw new DuplicateLoginException(MESSAGE_DUPLICATE_LOGIN_EXCEPTION, e);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_CHANGE_USER_EXCEPTION, e);
        }
    }

    @Override
    public void changePasswordById(int userId, String newPassword) throws ServiceException {
        if (!Validator.isValidId(userId)) {
            throw new ValidationException(MESSAGE_VALIDATION_EXCEPTION);
        }
        try {
            if (Validator.isValidPassword(newPassword)) {
                throw new WrongPasswordException(MESSAGE_WRONG_PASSWORD_EXCEPTION);
            }
            userDAO.changePasswordById(userId, newPassword);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_CHANGE_PASSWORD_EXCEPTION, e);
        }
    }
}
