package by.zhigarev.dao;

import by.zhigarev.bean.BetStatus;
import by.zhigarev.dao.exception.DAOException;

import java.util.List;

/**
 * Interface provides methods to interact with BetStatus data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface BetStatusDAO {
    /**
     * Connects to database and return bet status by id.
     *
     * @param id is id of bet status
     * @return BetStatus {@link BetStatus}.
     * @throws DAOException when problems with database connection occurs.
     */
    BetStatus getStatusById(int id) throws DAOException;

    /**
     * Connects to database and return list of bet statuses.
     *
     * @return List of {@link BetStatus} with all statuses.
     * @throws DAOException when problems with database connection occurs.
     */
    List<BetStatus> getAllStatuses() throws DAOException;
}
