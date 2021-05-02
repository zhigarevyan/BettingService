package by.zhigarev.dao;

import by.zhigarev.bean.Event;
import by.zhigarev.bean.info.BetInfo;
import by.zhigarev.dao.exception.DAOException;

import java.util.List;

/**
 * Interface provides methods to interact with Bet data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface BetDAO {
    /**
     * Connects to database and return list of bets that linked to an event.
     *
     * @param event is event.
     * @return List of {@link BetInfo} with all matching bets.
     * @throws DAOException when problems with database connection occurs.
     */
    List<BetInfo> getAllBetsOfEvent(Event event) throws DAOException;

    /**
     * Connects to database and creates bet.
     *
     * @param betInfo is info of bet.
     * @throws DAOException when problems with database connection occurs.
     */
    void createBetForEvent(BetInfo betInfo) throws DAOException;

    /**
     * Connects to database and return bet by id.
     *
     * @param id is id of bet
     * @return BetInfo {@link BetInfo}.
     * @throws DAOException when problems with database connection occurs.
     */
    BetInfo getBetById(int id) throws DAOException;

    /**
     * Connects to database and updates status of bet.
     *
     * @param betId       is id of bet.
     * @param betStatusId is id of status to change.
     * @throws DAOException when problems with database connection occurs.
     */
    void updateBetStatusById(int betId, int betStatusId) throws DAOException;

    /**
     * Connects to database and return list of bets that linked to an event.
     *
     * @param eventId is id of event.
     * @return List of {@link BetInfo} with all matching bets.
     * @throws DAOException when problems with database connection occurs.
     */
    List<BetInfo> getAllBetsOfEventById(int eventId) throws DAOException;

    /**
     * Connects to database and delete bet by id.
     *
     * @param id is id of bet.
     * @throws DAOException when problems with database connection occurs.
     */
    void deleteBetById(int id) throws DAOException;

}
