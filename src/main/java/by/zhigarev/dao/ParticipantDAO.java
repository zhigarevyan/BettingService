package by.zhigarev.dao;

import by.zhigarev.bean.Participant;
import by.zhigarev.bean.info.ParticipantInfo;
import by.zhigarev.dao.exception.DAOException;

import java.util.List;

/**
 * Interface provides methods to interact with Participant data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface ParticipantDAO {
    /**
     * Connects to database and create participant.
     *
     * @param participant is an object with info about participant.
     * @throws DAOException when problems with database connection occurs.
     */
    void addParticipant(ParticipantInfo participant) throws DAOException;

    /**
     * Connects to database and return outcomeType by id.
     *
     * @param participant is an object with info about participant.
     * @param id          is an id of participant.
     * @throws DAOException when problems with database connection occurs.
     */
    void updateParticipant(ParticipantInfo participantInfo, int id) throws DAOException;

    /**
     * Connects to database and return list of participants.
     *
     * @return list of participant {@link Participant} with all participants
     * @throws DAOException when problems with database connection occurs.
     */
    List<Participant> getAllParticipants() throws DAOException;

    /**
     * Connects to database and return participant by id.
     *
     * @param id is id of participant.
     * @throws DAOException when problems with database connection occurs.
     */
    Participant getParticipantById(int id) throws DAOException;

    /**
     * Connects to database and return participant by name and surname.
     *
     * @param name    is name of participant.
     * @param surname is surname of participant.
     * @throws DAOException when problems with database connection occurs.
     */
    Participant getParticipantByName(String name, String surname) throws DAOException;
}
