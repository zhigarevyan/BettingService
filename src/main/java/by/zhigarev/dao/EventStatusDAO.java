package by.zhigarev.dao;

import by.zhigarev.bean.EventStatus;
import by.zhigarev.dao.exception.DAOException;

/**
 * Interface provides methods to interact with EventStatus data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface EventStatusDAO {
    /**
     * Connects to database and return list of event results.
     *
     * @param id is id of status
     * @return EventStatus {@link EventStatus} .
     * @throws DAOException when problems with database connection occurs.
     */
    EventStatus getStatusById(int id) throws DAOException;
}
