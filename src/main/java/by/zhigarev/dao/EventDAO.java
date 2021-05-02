package by.zhigarev.dao;

import by.zhigarev.bean.info.EventInfo;
import by.zhigarev.dao.exception.DAOException;

import java.util.List;

/**
 * Interface provides methods to interact with Event data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface EventDAO {
    /**
     * Connects to database and return list of events.
     *
     * @return List of {@link EventInfo} with all future events.
     * @throws DAOException when problems with database connection occurs.
     */
    List<EventInfo> getAllFutureEvents() throws DAOException;

    /**
     * Connects to database and create event.
     *
     * @param event is info of event.
     * @throws DAOException when problems with database connection occurs.
     */
    void createEvent(EventInfo event) throws DAOException;

    /**
     * Connects to database and return event by id.
     *
     * @param id is id of event.
     * @return EventInfo {@link EventInfo} is object with info about event.
     * @throws DAOException when problems with database connection occurs.
     */
    EventInfo getEventById(int id) throws DAOException;

    /**
     * Connects to database and change event status by id.
     *
     * @param id is id of event.
     * @throws DAOException when problems with database connection occurs.
     */
    void changeEventStatusToPastById(int id) throws DAOException;
}
