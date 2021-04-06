package by.zhigarev.dao;

import by.zhigarev.bean.Account;
import by.zhigarev.bean.User;
import by.zhigarev.bean.info.UserBetInfo;
import by.zhigarev.dao.exception.DAOException;

import java.util.List;

public interface UserBetDAO {
    boolean addUserBet(UserBetInfo userBetInfo) throws DAOException;
    List<UserBetInfo> getAllAccountBets(Account account) throws DAOException;
    UserBetInfo getUserBetById(int id) throws DAOException;
    List<UserBetInfo> getAllBetsByBetId(int betId) throws DAOException;
}
