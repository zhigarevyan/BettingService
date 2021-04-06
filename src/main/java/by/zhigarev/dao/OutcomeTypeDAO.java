package by.zhigarev.dao;

import by.zhigarev.bean.EventStatus;
import by.zhigarev.bean.OutcomeType;
import by.zhigarev.dao.exception.DAOException;

import java.util.List;

public interface OutcomeTypeDAO {
    OutcomeType getTypeById(int id) throws DAOException;
    List<OutcomeType> getAllTypes() throws DAOException;
    boolean addType(String type) throws DAOException;
}
