package by.zhigarev.service.impl;

import by.zhigarev.bean.Event;
import by.zhigarev.bean.EventResult;
import by.zhigarev.bean.info.EventResultInfo;
import by.zhigarev.dao.DAOProvider;
import by.zhigarev.dao.EventResultDAO;
import by.zhigarev.dao.exception.DAOException;
import by.zhigarev.service.EventResultService;
import by.zhigarev.service.EventService;
import by.zhigarev.service.ParticipantService;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class EventResultServiceImpl implements EventResultService {
    private static final EventResultServiceImpl instance = new EventResultServiceImpl();
    private static final EventResultDAO eventResultDAO = DAOProvider.getEventResultDAO();
    private static final EventService eventService = ServiceProvider.getEventService();
    private static final ParticipantService participantService = ServiceProvider.getParticipantService();
    private static final String MESSAGE_GET_RESULT_BY_EVENT = "Cant get result by event";
    private static final String MESSAGE_GET_ALL_RESULTS = "Cant get all results";
    private static final String MESSAGE_CREATE_RESULT = "Cant create result";

    public static EventResultServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<EventResult> getAllResults() throws ServiceException {
        List<EventResult> eventResults = new ArrayList<>();
        try {
            List<EventResultInfo> eventResultInfoList = eventResultDAO.getAllResults();
            for (EventResultInfo eventResultInfo : eventResultInfoList) {
                EventResult eventResult = new EventResult(
                        eventResultInfo.getId(),
                        eventService.getEventById(eventResultInfo.getEventId()),
                        participantService.getParticipantById(eventResultInfo.getWinner()),
                        eventResultInfo.getFirstParticipantScore(),
                        eventResultInfo.getSecondParticipantScore());
                eventResults.add(eventResult);
            }
            return eventResults;
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_GET_ALL_RESULTS, e);
        }
    }

    @Override
    public EventResult getResultByEvent(Event event) throws ServiceException {
        EventResult eventResult = null;
        try {
            EventResultInfo eventResultInfo = eventResultDAO.getResultByEvent(event);
            eventResult = new EventResult(
                    eventResultInfo.getId(),
                    eventService.getEventById(eventResultInfo.getEventId()),
                    participantService.getParticipantById(eventResultInfo.getWinner()),
                    eventResultInfo.getFirstParticipantScore(),
                    eventResultInfo.getSecondParticipantScore());
            return eventResult;
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_GET_RESULT_BY_EVENT, e);
        }
    }

    @Override
    public void createResultForEvent(EventResultInfo eventResult) throws ServiceException {
        try {
            eventResultDAO.createResultForEvent(eventResult);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_CREATE_RESULT, e);
        }
    }
}
