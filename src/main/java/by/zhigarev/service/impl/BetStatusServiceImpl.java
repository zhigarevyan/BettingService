package by.zhigarev.service.impl;

import by.zhigarev.bean.BetStatus;
import by.zhigarev.dao.BetStatusDAO;
import by.zhigarev.dao.DAOProvider;
import by.zhigarev.dao.exception.DAOException;
import by.zhigarev.service.BetStatusService;
import by.zhigarev.service.exception.ServiceException;

import java.util.List;

public class BetStatusServiceImpl implements BetStatusService {
    private static final BetStatusDAO betStatusDAO = DAOProvider.getBetStatusDAO();
    private static final BetStatusService instance = new BetStatusServiceImpl();
    private static final String MESSAGE_GET_ALL_STATUSES_EXCEPTION = "cant get all statuses";

    public static BetStatusService getInstance() {
        return instance;
    }

    @Override
    public List<BetStatus> getAllStatuses() throws ServiceException {
        try {
            return betStatusDAO.getAllStatuses();
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_GET_ALL_STATUSES_EXCEPTION, e);
        }
    }
}
