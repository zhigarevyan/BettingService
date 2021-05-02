package by.zhigarev.service;

import by.zhigarev.bean.Event;
import by.zhigarev.bean.EventResult;
import by.zhigarev.bean.info.EventResultInfo;
import by.zhigarev.service.exception.ServiceException;

import java.util.List;

public interface EventResultService {
    List<EventResult> getAllResults() throws ServiceException;

    EventResult getResultByEvent(Event event) throws ServiceException;

    void createResultForEvent(EventResultInfo eventResult) throws ServiceException;
}
