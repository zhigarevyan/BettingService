package by.zhigarev.dao;

import by.zhigarev.bean.Event;
import by.zhigarev.bean.info.EventResultInfo;
import by.zhigarev.dao.exception.DAOException;

import java.util.List;

/**
 * Interface provides methods to interact with EventResult data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface EventResultDAO {
    /**
     * Connects to database and return list of event results.
     *
     * @return List of {@link EventResultInfo} with all event results.
     * @throws DAOException when problems with database connection occurs.
     */
    List<EventResultInfo> getAllResults() throws DAOException;

    /**
     * Connects to database and return event result by event.
     *
     * @param event {@link Event}is event
     * @return EventResultInfo {@link EventResultInfo} object with info about event result.
     * @throws DAOException when problems with database connection occurs.
     */
    EventResultInfo getResultByEvent(Event event) throws DAOException;

    /**
     * Connects to database and return list of event results.
     *
     * @param eventResult is object with info about event result
     * @throws DAOException when problems with database connection occurs.
     */
    void createResultForEvent(EventResultInfo eventResult) throws DAOException;
}
