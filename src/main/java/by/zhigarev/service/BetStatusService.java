package by.zhigarev.service;

import by.zhigarev.bean.BetStatus;
import by.zhigarev.service.exception.ServiceException;

import java.util.List;

public interface BetStatusService {
    List<BetStatus> getAllStatuses() throws ServiceException;
}
