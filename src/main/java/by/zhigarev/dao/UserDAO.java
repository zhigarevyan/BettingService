package by.zhigarev.dao;

import by.zhigarev.bean.User;
import by.zhigarev.bean.info.SignInInfo;
import by.zhigarev.bean.info.SignUpInfo;
import by.zhigarev.dao.exception.DAOException;
import by.zhigarev.dao.exception.DuplicateLoginException;

/**
 * Interface provides methods to interact with User data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface UserDAO {
    /**
     * Connects to database and create User.
     *
     * @param SignInInfo {@link SignInInfo} is an object with info for SignIn.
     * @return User {@link User} is object with info about user
     * @throws DAOException when problems with database connection occurs.
     */
    User signIn(SignInInfo signUpInfo) throws DAOException;

    /**
     * Connects to database and create User.
     *
     * @param SignUpInfo {@link SignUpInfo} is an object with info for signUp.
     * @return User {@link User} is object with info about user
     * @throws DAOException            when problems with database connection occurs.
     * @throws DuplicateLoginException when duplicate login.
     */
    User signUp(SignUpInfo signUpInfo) throws DuplicateLoginException, DAOException;

    /**
     * Connects to database and changes User.
     *
     * @param User {@link User} is an object with info about user.
     * @throws DAOException            when problems with database connection occurs.
     * @throws DuplicateLoginException when duplicate login.
     */
    void changeUser(User user) throws DuplicateLoginException, DAOException;

    /**
     * Connects to database and changes User.
     *
     * @param userId      is id of user.
     * @param newPassword is new password.
     * @throws DAOException when problems with database connection occurs.
     */
    void changePasswordById(int userId, String newPassword) throws DAOException;
}
