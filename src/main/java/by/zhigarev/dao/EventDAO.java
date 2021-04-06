package by.zhigarev.dao;

import by.zhigarev.bean.Event;
import by.zhigarev.bean.info.EventInfo;
import by.zhigarev.dao.exception.DAOException;

import java.util.List;

public interface EventDAO {
    List<EventInfo> getAllFutureEvents() throws DAOException;
    boolean createEvent(EventInfo event) throws DAOException;
    EventInfo getEventById(int id)throws DAOException;
    boolean deleteEventById(int eventId) throws DAOException;
    boolean updateEvent(Event oldEvent,Event newEvent) throws DAOException;
    List<EventInfo> getAllPastEvents() throws DAOException;
    boolean changeEventStatusToPastById(int id) throws DAOException;
}
