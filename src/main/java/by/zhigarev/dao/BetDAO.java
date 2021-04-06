package by.zhigarev.dao;

import by.zhigarev.bean.Event;
import by.zhigarev.bean.info.BetInfo;
import by.zhigarev.dao.exception.DAOException;

import java.util.List;

public interface BetDAO {
    List<BetInfo> getAllBetsOfEvent(Event event) throws DAOException;
    boolean createBetForEvent(BetInfo betInfo) throws DAOException;
    BetInfo getBetById(int id) throws DAOException;
    boolean updateBetStatusById(int betId, int betStatusId)throws DAOException;
    List<BetInfo> getAllBetsOfEventById(int eventId) throws DAOException;
    void deleteBetById(int id) throws DAOException;

}
