package by.zhigarev.dao;

import by.zhigarev.bean.EventStatus;
import by.zhigarev.dao.exception.DAOException;

public interface EventStatusDAO {
    EventStatus getStatusById(int id) throws DAOException;
}
