package by.zhigarev.service;

import by.zhigarev.bean.Participant;
import by.zhigarev.bean.info.ParticipantInfo;
import by.zhigarev.service.exception.ServiceException;

import java.util.List;

public interface ParticipantService {
    void addParticipant(ParticipantInfo participant) throws ServiceException;

    void updateParticipantById(ParticipantInfo participant, int id) throws ServiceException;

    List<Participant> getAllParticipants() throws ServiceException;

    Participant getParticipantById(int id) throws ServiceException;
}
