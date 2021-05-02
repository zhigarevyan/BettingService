package by.zhigarev.service.impl;

import by.zhigarev.bean.OutcomeType;
import by.zhigarev.dao.DAOProvider;
import by.zhigarev.dao.OutcomeTypeDAO;
import by.zhigarev.dao.exception.DAOException;
import by.zhigarev.service.OutcomeTypeService;
import by.zhigarev.service.exception.DuplicateOutcomeTypeException;
import by.zhigarev.service.exception.ServiceException;
import by.zhigarev.service.exception.ValidationException;
import by.zhigarev.service.validator.Validator;

import java.util.List;

public class OutcomeTypeServiceImpl implements OutcomeTypeService {
    private static final OutcomeTypeServiceImpl instance = new OutcomeTypeServiceImpl();
    private static final OutcomeTypeDAO outcomeTypeDAO = DAOProvider.getOutcomeTypeDAO();
    private static final String MESSAGE_GET_TYPE_BY_ID_EXCEPTION = "Cant get type by id";
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Cant get all types ";
    private static final String MESSAGE_ADD_TYPE_EXCEPTION = "Cant add type ";
    private static final String MESSAGE_VALIDATION_EXCEPTION = "Validation exception ";
    private static final String MESSAGE_DUPLICATE_OUTCOME_TYPE_EXCEPTION = "Duplicate outcome type ";

    public static OutcomeTypeServiceImpl getInstance() {
        return instance;
    }

    @Override
    public OutcomeType getTypeById(int id) throws ServiceException {
        if (!Validator.isValidId(id)) {
            throw new ValidationException(MESSAGE_VALIDATION_EXCEPTION);
        }
        try {
            return outcomeTypeDAO.getTypeById(id);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_GET_TYPE_BY_ID_EXCEPTION, e);
        }
    }

    @Override
    public List<OutcomeType> getAllTypes() throws ServiceException {
        try {
            return outcomeTypeDAO.getAllTypes();
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_GET_ALL_EXCEPTION, e);
        }
    }

    @Override
    public void addType(String type) throws ServiceException {
        if (!Validator.isValidRequiredField(type)) {
            throw new ValidationException(MESSAGE_VALIDATION_EXCEPTION);
        }
        try {
            for (OutcomeType outcomeType : outcomeTypeDAO.getAllTypes()) {
                if (outcomeType.getType().equalsIgnoreCase(type)) {
                    throw new DuplicateOutcomeTypeException(MESSAGE_DUPLICATE_OUTCOME_TYPE_EXCEPTION);
                }
            }
            outcomeTypeDAO.addType(type);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_ADD_TYPE_EXCEPTION, e);
        }
    }
}
