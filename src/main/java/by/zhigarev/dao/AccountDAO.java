package by.zhigarev.dao;

import by.zhigarev.bean.Account;
import by.zhigarev.bean.User;
import by.zhigarev.dao.exception.DAOException;

public interface AccountDAO {
    Account getAccountByUser(User user) throws DAOException;
    Account getAccountById(int id) throws DAOException;
    Account createAccountByUser(User user) throws DAOException;
    boolean writeOffMoneyByAccountId(int accountId, double amount) throws DAOException;
    boolean payMoneyByAccountId(int accountId, double amount) throws DAOException;
}
