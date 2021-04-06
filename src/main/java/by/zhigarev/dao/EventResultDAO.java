package by.zhigarev.dao;

import by.zhigarev.bean.Event;
import by.zhigarev.bean.EventResult;
import by.zhigarev.bean.info.EventResultInfo;
import by.zhigarev.dao.exception.DAOException;

import java.util.List;

public interface EventResultDAO {
    List<EventResultInfo> getAllResults() throws DAOException;
    EventResultInfo getResultByEvent(Event event) throws DAOException;
    boolean createResultForEvent(EventResultInfo eventResult) throws DAOException;
}
