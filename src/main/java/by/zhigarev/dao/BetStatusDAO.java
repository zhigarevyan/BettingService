package by.zhigarev.dao;

import by.zhigarev.bean.BetStatus;
import by.zhigarev.dao.exception.DAOException;

import java.util.List;

public interface BetStatusDAO {
    BetStatus getStatusById(int id) throws DAOException;
    List<BetStatus> getAllStatuses() throws DAOException;
}
