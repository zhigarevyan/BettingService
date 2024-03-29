package by.zhigarev.service.impl;

import by.zhigarev.bean.Participant;
import by.zhigarev.bean.info.ParticipantInfo;
import by.zhigarev.dao.DAOProvider;
import by.zhigarev.dao.ParticipantDAO;
import by.zhigarev.dao.exception.DAOException;
import by.zhigarev.service.ParticipantService;
import by.zhigarev.service.exception.DuplicateParticipantException;
import by.zhigarev.service.exception.ServiceException;
import by.zhigarev.service.exception.ValidationException;
import by.zhigarev.service.validator.Validator;

import java.util.List;

public class ParticipantServiceImpl implements ParticipantService {
    private static final ParticipantServiceImpl instance = new ParticipantServiceImpl();
    private static final ParticipantDAO participantDAO = DAOProvider.getParticipantDAO();
    private static final String MESSAGE_ADD_PARTICIPANT_EXCEPTION = "Cant add participant";
    private static final String MESSAGE_VALIDATION_EXCEPTION = "Cant add participant validation exception";
    private static final String MESSAGE_GET_ALL_PARTICIPANTS_EXCEPTION = "Cant get all participants";
    private static final String MESSAGE_GET_PARTICIPANT_EXCEPTION = "Cant get participant";
    private static final String MESSAGE_DUPLICATE_PARTICIPANT_EXCEPTION = "Duplicate participant exception";
    private static final String MESSAGE_UPDATE_EXCEPTION = "Cant update";

    public static ParticipantServiceImpl getInstance() {
        return instance;
    }

    @Override
    public void addParticipant(ParticipantInfo participant) throws ServiceException {
        if (!Validator.isValidParticipant(participant)) {
            throw new ValidationException(MESSAGE_VALIDATION_EXCEPTION);
        }
        try {
            if (getParticipant(participant.getName(), participant.getSurName()) != null) {
                throw new DuplicateParticipantException(MESSAGE_DUPLICATE_PARTICIPANT_EXCEPTION);
            }
            participantDAO.addParticipant(participant);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_ADD_PARTICIPANT_EXCEPTION, e);
        }
    }

    @Override
    public void updateParticipantById(ParticipantInfo participant, int id) throws ServiceException {
        if (!Validator.isValidId(id)) {
            throw new ValidationException(MESSAGE_VALIDATION_EXCEPTION);
        }
        try {
            Participant participantDB = getParticipant(participant.getName(), participant.getSurName());
            if (participantDB != null && participantDB.getId() != id) {
                throw new DuplicateParticipantException(MESSAGE_DUPLICATE_PARTICIPANT_EXCEPTION);
            }
            participantDAO.updateParticipant(participant, id);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_UPDATE_EXCEPTION, e);
        }
    }

    @Override
    public List<Participant> getAllParticipants() throws ServiceException {
        try {
            return participantDAO.getAllParticipants();
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_GET_ALL_PARTICIPANTS_EXCEPTION, e);
        }
    }


    private Participant getParticipant(String name, String surname) throws ServiceException {
        try {
            return participantDAO.getParticipantByName(name, surname);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_GET_PARTICIPANT_EXCEPTION, e);
        }
    }

    @Override
    public Participant getParticipantById(int id) throws ServiceException {
        if (!Validator.isValidId(id)) {
            throw new ValidationException(MESSAGE_VALIDATION_EXCEPTION);
        }
        try {
            return participantDAO.getParticipantById(id);
        } catch (DAOException e) {
            throw new ServiceException(MESSAGE_GET_PARTICIPANT_EXCEPTION, e);
        }
    }
}
