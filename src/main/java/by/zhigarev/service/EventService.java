package by.zhigarev.service;

import by.zhigarev.bean.Event;
import by.zhigarev.bean.info.EventInfo;
import by.zhigarev.service.exception.ServiceException;

import java.util.List;

public interface EventService {
    void createEvent(EventInfo event) throws ServiceException;

    Event getEventById(int id) throws ServiceException;

    List<Event> getAllFutureEvents() throws ServiceException;

    Event getEventByBetId(int id) throws ServiceException;

    void changeEventStatusToPastById(int id) throws ServiceException;
}
