package by.zhigarev.service;

import by.zhigarev.bean.OutcomeType;
import by.zhigarev.service.exception.ServiceException;

import java.util.List;

public interface OutcomeTypeService {
    OutcomeType getTypeById(int id) throws ServiceException;
    List<OutcomeType> getAllTypes() throws ServiceException;
    boolean addType(String type) throws ServiceException;
}
