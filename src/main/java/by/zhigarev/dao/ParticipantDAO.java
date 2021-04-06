package by.zhigarev.dao;

import by.zhigarev.bean.Participant;
import by.zhigarev.bean.info.ParticipantInfo;
import by.zhigarev.dao.exception.DAOException;

import java.util.List;

public interface ParticipantDAO {
    boolean addParticipant(ParticipantInfo participant) throws DAOException;
    boolean deleteParticipant(Participant participant);
    List<Participant> getAllParticipants() throws DAOException;
    Participant getParticipantByName(String name, String surname) throws DAOException;
    Participant getParticipantById(int id) throws DAOException;
}
