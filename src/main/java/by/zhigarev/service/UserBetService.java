package by.zhigarev.service;

import by.zhigarev.bean.Account;
import by.zhigarev.bean.UserBet;
import by.zhigarev.bean.info.UserBetInfo;
import by.zhigarev.service.exception.ServiceException;

import java.util.List;

public interface UserBetService {
    boolean addUserBet(UserBetInfo userBetInfo) throws ServiceException;
    List<UserBet> getAllAccountBets(Account account) throws ServiceException;
    UserBet getUserBetById(int id) throws ServiceException;
    List<UserBet> getAllBetsByBetId(int betId) throws ServiceException;
}
