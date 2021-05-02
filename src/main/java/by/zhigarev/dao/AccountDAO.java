package by.zhigarev.dao;

import by.zhigarev.bean.Account;
import by.zhigarev.bean.User;
import by.zhigarev.dao.exception.DAOException;

/**
 * Interface provides methods to interact with Accounts data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface AccountDAO {


    /**
     * Connects to database and return account that linked to an user.
     *
     * @param User is user.
     * @return Account of user {@link Account}.
     * @throws DAOException when problems with database connection occurs.
     */
    Account getAccountByUser(User user) throws DAOException;

    /**
     * Connects to database and return account that linked to an user by id.
     *
     * @param id is user id.
     * @return Account of user {@link Account}.
     * @throws DAOException when problems with database connection occurs.
     */
    Account getAccountById(int id) throws DAOException;

    /**
     * Connects to database create and return created account.
     *
     * @param User is user for account.
     * @return Account of user {@link Account}.
     * @throws DAOException when problems with database connection occurs.
     */
    Account createAccountByUser(User user) throws DAOException;

    /**
     * Connects to database and write off money from account by account id.
     *
     * @param accountId is id of account.
     * @param amount    is amount of money to write off.
     * @throws DAOException when problems with database connection occurs.
     */
    void writeOffMoneyByAccountId(int accountId, double amount) throws DAOException;

    /**
     * Connects to database and pay money to account by account id.
     *
     * @param accountId is id of account.
     * @param amount    is amount of money to pay.
     * @throws DAOException when problems with database connection occurs.
     */
    void payMoneyByAccountId(int accountId, double amount) throws DAOException;

    /**
     * Connects to database and return balance of account.
     *
     * @param accountId is id of account.
     * @return balance of account
     * @throws DAOException when problems with database connection occurs.
     */
    double getBalanceById(int accountId) throws DAOException;
}
