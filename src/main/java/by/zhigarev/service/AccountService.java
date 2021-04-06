package by.zhigarev.service;

import by.zhigarev.bean.Account;
import by.zhigarev.bean.User;
import by.zhigarev.service.exception.ServiceException;

public interface AccountService {
    Account getAccountByUser(User user) throws ServiceException;
    Account createAccountByUser(User user) throws ServiceException;
    boolean writeOffMoneyByAccountId(int accountId, double amount) throws ServiceException;
    boolean payMoneyByAccountId(int accountId, double amount) throws ServiceException;
}
