package by.zhigarev.service.impl;

import by.zhigarev.bean.Bet;
import by.zhigarev.bean.Event;
import by.zhigarev.bean.info.BetInfo;
import by.zhigarev.dao.BetDAO;
import by.zhigarev.dao.BetStatusDAO;
import by.zhigarev.dao.DAOProvider;
import by.zhigarev.dao.OutcomeTypeDAO;
import by.zhigarev.dao.exception.DAOException;
import by.zhigarev.service.BetService;
import by.zhigarev.service.EventService;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.exception.DuplicateBetException;
import by.zhigarev.service.exception.ServiceException;


import java.util.ArrayList;
import java.util.List;

public class BetServiceImpl implements BetService {
    private static final BetServiceImpl instance = new BetServiceImpl();
    private static final BetDAO betDAO = DAOProvider.getBetDAO();
    private static final OutcomeTypeDAO outcomeTypeDAO = DAOProvider.getOutcomeTypeDAO();
    private static final BetStatusDAO betStatusDAO = DAOProvider.getBetStatusDAO();
    private static final EventService eventService = ServiceProvider.getEventService();

    private static final String MESSAGE_GET_EVENT_BETS_EXCEPTION = "Cant get event bets exception";
    private static final String MESSAGE_CHANGE_BET_STATUS_EXCEPTION = "Cant change bet status exception";
    private static final String MESSAGE_CREATE_EVENT_BETS_EXCEPTION = "Cant create event bets exception";
    private static final String MESSAGE_DUPLICATE_BET_EXCEPTION = "Duplicate bet";
    private static final String MESSAGE_DELETE_BET_EXCEPTION = "Delete bet exception";

    public static BetServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Bet> getAllBetsOfEvent(Event event) throws ServiceException {
        List<Bet> bets = new ArrayList<>();
        try {
            List<BetInfo> betInfoList = betDAO.getAllBetsOfEvent(event);
            for (BetInfo betInfo : betInfoList) {
                Bet bet = new Bet(betInfo.getId(),
                        event,
                        outcomeTypeDAO.getTypeById(betInfo.getOutcome()),
                        betStatusDAO.getStatusById(betInfo.getStatus()),
                        betInfo.getOffer());
                bets.add(bet);
            }
            return bets;
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_GET_EVENT_BETS_EXCEPTION, e);
        }
    }

    @Override
    public Bet getBetById(int id) throws ServiceException {
        Bet bet;
        try {
            BetInfo betInfo = betDAO.getBetById(id);
            bet = new Bet(betInfo.getId(),
                    eventService.getEventById(betInfo.getEventId()),
                    outcomeTypeDAO.getTypeById(betInfo.getOutcome()),
                    betStatusDAO.getStatusById(betInfo.getStatus()),
                    betInfo.getOffer());
            return bet;
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_GET_EVENT_BETS_EXCEPTION, e);
        }
    }

    @Override
    public boolean createBetForEvent(BetInfo betInfo) throws ServiceException {
        try {
            List<BetInfo> betInfoList = betDAO.getAllBetsOfEventById(betInfo.getEventId());
            for(BetInfo betInfoItem : betInfoList){
                if(betInfoItem.getOutcome() == betInfo.getOutcome()){
                    throw new DuplicateBetException(MESSAGE_DUPLICATE_BET_EXCEPTION);
                }
            }
            return betDAO.createBetForEvent(betInfo);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_CREATE_EVENT_BETS_EXCEPTION, e);
        }
    }

    @Override
    public boolean updateBetStatusById(int betId, int betStatusId) throws ServiceException {
        try {
            return betDAO.updateBetStatusById(betId, betStatusId);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_CHANGE_BET_STATUS_EXCEPTION,e);
        }
    }

    @Override
    public void deleteBetById(int betId) throws ServiceException {
        try{
            betDAO.deleteBetById(betId);
        }catch (DAOException e) {
            throw new ServiceException(MESSAGE_DELETE_BET_EXCEPTION,e);
        }
    }
}
