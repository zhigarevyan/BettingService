package by.zhigarev.service.impl;

import by.zhigarev.bean.Account;
import by.zhigarev.bean.User;
import by.zhigarev.bean.UserBet;
import by.zhigarev.bean.info.UserBetInfo;
import by.zhigarev.dao.AccountDAO;
import by.zhigarev.dao.DAOProvider;
import by.zhigarev.dao.UserBetDAO;
import by.zhigarev.dao.exception.DAOException;
import by.zhigarev.service.BetService;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.UserBetService;
import by.zhigarev.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class UserBetServiceImpl implements UserBetService {
    private static final UserBetServiceImpl instance = new UserBetServiceImpl();
    private static final UserBetDAO userBetDAO = DAOProvider.getUserBetDAO();
    private static final AccountDAO accountDAO = DAOProvider.getAccountDAO();
    private static final String MESSAGE_ADD_USER_BET_EXCEPTION = "Cant add user bet";
    private static final String MESSAGE_GET_ALL_USER_BETS_EXCEPTION = "Cant get all user bets";
    private static final String MESSAGE_GET_USER_BETS_BY_ID_EXCEPTION = "Cant get user bet by id";
    private static final BetService betService = ServiceProvider.getBetService();

    public static UserBetServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addUserBet(UserBetInfo userBetInfo) throws ServiceException {
        try {
            return userBetDAO.addUserBet(userBetInfo);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_ADD_USER_BET_EXCEPTION, e);
        }
    }

    @Override
    public List<UserBet> getAllAccountBets(Account account) throws ServiceException {

        List<UserBet> userBets = new ArrayList<>();

        try {
            List<UserBetInfo> userBetInfoList = userBetDAO.getAllAccountBets(account);
            for (UserBetInfo userBetInfo : userBetInfoList) {
                UserBet userBet = new UserBet(
                        userBetInfo.getId(),
                        accountDAO.getAccountById(userBetInfo.getAccountId()),
                        betService.getBetById(userBetInfo.getBetId()),
                        userBetInfo.getTimeStamp(),
                        userBetInfo.getBetAmount());
                userBets.add(userBet);
            }
            return userBets;
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_GET_ALL_USER_BETS_EXCEPTION, e);
        }
    }

    @Override
    public UserBet getUserBetById(int id) throws ServiceException {
        UserBet userBet = null;
        try {
            UserBetInfo userBetInfo = userBetDAO.getUserBetById(id);
            userBet = new UserBet(
                    userBetInfo.getId(),
                    accountDAO.getAccountById(userBetInfo.getAccountId()),
                    betService.getBetById(userBetInfo.getBetId()),
                    userBetInfo.getTimeStamp(),
                    userBetInfo.getBetAmount());
            return userBet;
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_GET_USER_BETS_BY_ID_EXCEPTION, e);
        }
    }

    @Override
    public List<UserBet> getAllBetsByBetId(int betId) throws ServiceException {
        List<UserBet> userBets = new ArrayList<>();

        try {
            List<UserBetInfo> userBetInfoList = userBetDAO.getAllBetsByBetId(betId);
            for (UserBetInfo userBetInfo : userBetInfoList) {
                UserBet userBet = new UserBet(
                        userBetInfo.getId(),
                        accountDAO.getAccountById(userBetInfo.getAccountId()),
                        betService.getBetById(userBetInfo.getBetId()),
                        userBetInfo.getTimeStamp(),
                        userBetInfo.getBetAmount());
                userBets.add(userBet);
            }
            return userBets;
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_GET_ALL_USER_BETS_EXCEPTION, e);
        }
    }
}
