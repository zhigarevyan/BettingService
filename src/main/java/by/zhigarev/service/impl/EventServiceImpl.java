package by.zhigarev.service.impl;

import by.zhigarev.bean.Bet;
import by.zhigarev.bean.Event;
import by.zhigarev.bean.EventStatus;
import by.zhigarev.bean.Participant;
import by.zhigarev.bean.info.EventInfo;
import by.zhigarev.dao.DAOProvider;
import by.zhigarev.dao.EventDAO;
import by.zhigarev.dao.EventStatusDAO;
import by.zhigarev.dao.ParticipantDAO;
import by.zhigarev.dao.exception.DAOException;
import by.zhigarev.service.EventService;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.exception.ServiceException;
import by.zhigarev.service.exception.ValidationException;
import by.zhigarev.service.validator.Validator;

import java.util.ArrayList;
import java.util.List;


public class EventServiceImpl implements EventService {
    private static final EventServiceImpl instance = new EventServiceImpl();
    private static final EventDAO eventDAO = DAOProvider.getEventDAO();
    private static final ParticipantDAO participantDAO = DAOProvider.getParticipantDAO();
    private static final EventStatusDAO eventStatusDAO = DAOProvider.getEventStatusDAO();

    private static final String MESSAGE_CREATE_EVENT_EXCEPTION = "cant create event";
    private static final String MESSAGE_CHANGE_STATUS_TO_PAST = "cant change status of event";
    private static final String MESSAGE_GET_FUTURE_EVENTS_EXCEPTION = "cant get future events";
    private static final String MESSAGE_GET_EVENT_BY_ID_EXCEPTION = "cant get event by id";
    private static final String MESSAGE_GET_EVENT_BY_BET_ID_EXCEPTION = "cant get event by bet id";
    private static final String MESSAGE_VALIDATION_EXCEPTION = "validation exception";

    public static EventServiceImpl getInstance() {
        return instance;
    }


    @Override
    public void createEvent(EventInfo event) throws ServiceException {
        if (!Validator.isValidEvent(event)) {
            throw new ValidationException(MESSAGE_VALIDATION_EXCEPTION);
        }
        try {
            eventDAO.createEvent(event);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_CREATE_EVENT_EXCEPTION, e);
        }
    }

    @Override
    public Event getEventById(int id) throws ServiceException {
        if (!Validator.isValidId(id)) {
            throw new ValidationException(MESSAGE_VALIDATION_EXCEPTION);
        }
        Event event = null;
        try {
            EventInfo eventInfo = eventDAO.getEventById(id);
            Participant p1 = participantDAO.getParticipantById(eventInfo.getFirstParticipant());
            Participant p2 = participantDAO.getParticipantById(eventInfo.getSecondParticipant());
            EventStatus eventStatus = eventStatusDAO.getStatusById(eventInfo.getStatus());
            event = new Event(eventInfo.getId(), p1, p2, eventInfo.getLocation(), eventInfo.getInfo(), eventInfo.getStartDateTime(), eventStatus);
            return event;
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_GET_EVENT_BY_ID_EXCEPTION, e);
        }
    }

    @Override
    public List<Event> getAllFutureEvents() throws ServiceException {
        final int STATUS = 1;
        try {
            List<Event> events = new ArrayList<>();

            for (EventInfo eventInfo : eventDAO.getAllFutureEvents()) {
                Participant p1 = participantDAO.getParticipantById(eventInfo.getFirstParticipant());
                Participant p2 = participantDAO.getParticipantById(eventInfo.getSecondParticipant());
                EventStatus eventStatus = eventStatusDAO.getStatusById(STATUS);
                Event event = new Event(eventInfo.getId(), p1, p2, eventInfo.getLocation(), eventInfo.getInfo(), eventInfo.getStartDateTime(), eventStatus);
                events.add(event);
            }
            return events;
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_GET_FUTURE_EVENTS_EXCEPTION, e);
        }
    }

    @Override
    public Event getEventByBetId(int id) throws ServiceException {
        if (!Validator.isValidId(id)) {
            throw new ValidationException(MESSAGE_VALIDATION_EXCEPTION);
        }
        try {
            Bet bet = ServiceProvider.getBetService().getBetById(id);
            return getEventById(bet.getEvent().getId());
        } catch (ServiceException e) {
            throw new ServiceException(MESSAGE_GET_EVENT_BY_BET_ID_EXCEPTION);
        }
    }

    @Override
    public void changeEventStatusToPastById(int id) throws ServiceException {
        if (!Validator.isValidId(id)) {
            throw new ValidationException(MESSAGE_VALIDATION_EXCEPTION);
        }
        try {
            eventDAO.changeEventStatusToPastById(id);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_CHANGE_STATUS_TO_PAST, e);
        }
    }
}
