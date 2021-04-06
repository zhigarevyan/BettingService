package by.zhigarev.service.impl;

import by.zhigarev.bean.Account;
import by.zhigarev.bean.User;
import by.zhigarev.dao.AccountDAO;
import by.zhigarev.dao.DAOProvider;
import by.zhigarev.dao.exception.DAOException;
import by.zhigarev.service.AccountService;
import by.zhigarev.service.exception.ServiceException;

public class AccountServiceImpl implements AccountService {
    private static final AccountService instance = new AccountServiceImpl();
    private static final AccountDAO accountDAO = DAOProvider.getAccountDAO();

    private static final String MESSAGE_CANT_GET_ACCOUNT = "Cant get account for this user";
    private static final String MESSAGE_CANT_CREATE_ACCOUNT = "Cant create account for this user";
    private static final String MESSAGE_CANT_WRITE_OFF_MONEY = "Cant write off money";
    private static final String MESSAGE_CANT_PAY_MONEY = "Cant pay money";

    public static AccountService getInstance() {
        return instance;
    }

    @Override
    public Account getAccountByUser(User user) throws ServiceException {
        try {
            return accountDAO.getAccountByUser(user);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_CANT_GET_ACCOUNT,e);
        }
    }

    @Override
    public Account createAccountByUser(User user) throws ServiceException {
        try {
            return accountDAO.createAccountByUser(user);

        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_CANT_CREATE_ACCOUNT,e);
        }
    }

    @Override
    public boolean writeOffMoneyByAccountId(int accountId, double amount) throws ServiceException {
        try{
            return accountDAO.writeOffMoneyByAccountId(accountId,amount);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_CANT_WRITE_OFF_MONEY);
        }
    }

    @Override
    public boolean payMoneyByAccountId(int accountId, double amount) throws ServiceException {
        try{
            return accountDAO.payMoneyByAccountId(accountId,amount);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_CANT_PAY_MONEY);
        }
    }
}
