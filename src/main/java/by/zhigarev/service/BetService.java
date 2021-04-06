package by.zhigarev.service;

import by.zhigarev.bean.Bet;
import by.zhigarev.bean.Event;
import by.zhigarev.bean.info.BetInfo;
import by.zhigarev.service.exception.DuplicateBetException;
import by.zhigarev.service.exception.ServiceException;

import java.util.List;

public interface BetService {
    List<Bet> getAllBetsOfEvent(Event event) throws  ServiceException;
    Bet getBetById(int id) throws  ServiceException;
    boolean createBetForEvent(BetInfo betInfo) throws  ServiceException;
    boolean updateBetStatusById(int betId, int betStatusId)throws ServiceException;
    void deleteBetById(int betId)throws ServiceException;
}
