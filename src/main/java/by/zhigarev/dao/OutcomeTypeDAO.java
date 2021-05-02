package by.zhigarev.dao;

import by.zhigarev.bean.OutcomeType;
import by.zhigarev.dao.exception.DAOException;

import java.util.List;

/**
 * Interface provides methods to interact with OutcomeType data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface OutcomeTypeDAO {
    /**
     * Connects to database and return outcomeType by id.
     *
     * @param id is id of outcomeType
     * @return OutcomeType {@link OutcomeType} .
     * @throws DAOException when problems with database connection occurs.
     */
    OutcomeType getTypeById(int id) throws DAOException;

    /**
     * Connects to database and return list of outcomeTypes.
     *
     * @return list of OutcomeType {@link OutcomeType} with all types.
     * @throws DAOException when problems with database connection occurs.
     */
    List<OutcomeType> getAllTypes() throws DAOException;

    /**
     * Connects to database and create outcomeType.
     *
     * @param type is name of outcomeType
     * @throws DAOException when problems with database connection occurs.
     */
    void addType(String type) throws DAOException;
}
