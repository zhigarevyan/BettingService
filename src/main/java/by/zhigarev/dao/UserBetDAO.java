package by.zhigarev.dao;

import by.zhigarev.bean.Account;
import by.zhigarev.bean.info.UserBetInfo;
import by.zhigarev.dao.exception.DAOException;

import java.util.List;

/**
 * Interface provides methods to interact with UserBet data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface UserBetDAO {
    /**
     * Connects to database and create userBet.
     *
     * @param userBetInfo {@link UserBetInfo} is an object with info about userBet.
     * @throws DAOException when problems with database connection occurs.
     */
    void addUserBet(UserBetInfo userBetInfo) throws DAOException;

    /**
     * Connects to database and return list of userBetInfo.
     *
     * @param account {@link Account} is an object with info about account.
     * @return list of UserBetInfo {@link UserBetInfo} with all userBets of account.
     * @throws DAOException when problems with database connection occurs.
     */
    List<UserBetInfo> getAllAccountBets(Account account) throws DAOException;

    /**
     * Connects to database and return list of userBetInfo.
     *
     * @param id is id of userBet.
     * @return UserBetInfo {@link UserBetInfo} object with info of userBet.
     * @throws DAOException when problems with database connection occurs.
     */
    UserBetInfo getUserBetById(int id) throws DAOException;

    /**
     * Connects to database and return list of userBetInfo.
     *
     * @param betId is id of bet.
     * @return list of UserBetInfo {@link UserBetInfo} with all userBets of account.
     * @throws DAOException when problems with database connection occurs.
     */
    List<UserBetInfo> getAllBetsByBetId(int betId) throws DAOException;
}
