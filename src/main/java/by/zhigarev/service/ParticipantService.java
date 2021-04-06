package by.zhigarev.service;

import by.zhigarev.bean.Participant;
import by.zhigarev.bean.info.ParticipantInfo;
import by.zhigarev.dao.exception.DAOException;
import by.zhigarev.service.exception.ServiceException;

import java.util.List;

public interface ParticipantService {
    boolean addParticipant(ParticipantInfo participant) throws ServiceException;
    boolean deleteParticipant(Participant participant);
    List<Participant> getAllParticipants() throws ServiceException;
    Participant getParticipant(String name, String surname)throws ServiceException;
    Participant getParticipantById(int id) throws ServiceException;
}
