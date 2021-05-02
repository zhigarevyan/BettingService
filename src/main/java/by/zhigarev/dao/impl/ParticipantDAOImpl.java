package by.zhigarev.dao.impl;

import by.zhigarev.bean.Participant;
import by.zhigarev.bean.info.ParticipantInfo;
import by.zhigarev.dao.ParticipantDAO;
import by.zhigarev.dao.connection.ConnectionPool;
import by.zhigarev.dao.connection.impl.ConnectionPoolImpl;
import by.zhigarev.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAOImpl implements ParticipantDAO {
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
    private static final String SQL_INSERT_PARTICIPANT = "insert into Participants(name,surname,info,image) values (?,?,?,?)";
    private static final String SQL_INSERT_IMAGE_BY_ID = "update participants set image = ? where id = ?";
    private static final String SQL_UPDATE_PARTICIPANT_BY_ID = "update participants set name = ?,surname = ?, info = ? , image = ? where id = ?";
    private static final String SQL_SELECT_ALL_PARTICIPANTS = "select id,name,surname,info,image from Participants order by id desc";
    private static final String SQL_SELECT_PARTICIPANT_BY_NAME = "select id,name,surname,info,image from Participants where name = ? and surname = ?";
    private static final String SQL_SELECT_PARTICIPANT_BY_ID = "select id,name,surname,info,image from Participants where id = ?";

    private static final String MESSAGE_SQL_EXCEPTION = "SQLException on DAO layer";
    private static final String MESSAGE_CANT_CREATE_PARTICIPANT_EXCEPTION = "Cant create participant";
    private static final String MESSAGE_CANT_SELECT_ALL_PARTICIPANTS_EXCEPTION = "Cant select all participants";
    private static final String MESSAGE_CANT_SELECT_PARTICIPANT_EXCEPTION = "Cant select participant";
    private static final ParticipantDAOImpl instance = new ParticipantDAOImpl();

    public static ParticipantDAO getInstance() {
        return instance;
    }

    @Override
    public void addParticipant(ParticipantInfo participant) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_INSERT_PARTICIPANT);

            ps.setString(ParticipantInsertIndexes.NAME, participant.getName());
            ps.setString(ParticipantInsertIndexes.SURNAME, participant.getSurName());
            ps.setString(ParticipantInsertIndexes.INFO, participant.getInfo());
            ps.setString(ParticipantInsertIndexes.IMAGE, participant.getImage());
            ps.execute();

        } catch (DAOException e) {
            throw new DAOException(MESSAGE_CANT_CREATE_PARTICIPANT_EXCEPTION, e);
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }


    @Override
    public void updateParticipant(ParticipantInfo participantInfo, int id) throws DAOException {
        final int ID_INDEX = 5;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_UPDATE_PARTICIPANT_BY_ID);
            ps.setString(ParticipantInsertIndexes.NAME, participantInfo.getName());
            ps.setString(ParticipantInsertIndexes.SURNAME, participantInfo.getSurName());
            ps.setString(ParticipantInsertIndexes.INFO, participantInfo.getInfo());
            ps.setString(ParticipantInsertIndexes.IMAGE, participantInfo.getImage());
            ps.setInt(ID_INDEX, id);
            ps.execute();
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }


    @Override
    public List<Participant> getAllParticipants() throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        List<Participant> participants = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_SELECT_ALL_PARTICIPANTS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                participants.add(new Participant(
                        rs.getInt(ParticipantIndexes.ID),
                        rs.getString(ParticipantIndexes.NAME),
                        rs.getString(ParticipantIndexes.SURNAME),
                        rs.getString(ParticipantIndexes.INFO),
                        rs.getString(ParticipantIndexes.IMAGE)));
            }
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } catch (DAOException e) {
            throw new DAOException(MESSAGE_CANT_SELECT_ALL_PARTICIPANTS_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
        return participants;
    }

    @Override
    public Participant getParticipantByName(String name, String surname) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        Participant participant = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_SELECT_PARTICIPANT_BY_NAME);
            ps.setString(ParticipantInsertIndexes.NAME, name);
            ps.setString(ParticipantInsertIndexes.SURNAME, surname);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                participant = new Participant(
                        rs.getInt(ParticipantIndexes.ID),
                        rs.getString(ParticipantIndexes.NAME),
                        rs.getString(ParticipantIndexes.SURNAME),
                        rs.getString(ParticipantIndexes.INFO),
                        rs.getString(ParticipantIndexes.IMAGE));
            }
            return participant;
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_CANT_SELECT_PARTICIPANT_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    @Override
    public Participant getParticipantById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        Participant participant = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_SELECT_PARTICIPANT_BY_ID);
            ps.setInt(ParticipantIndexes.ID, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                participant = new Participant(
                        rs.getInt(ParticipantIndexes.ID),
                        rs.getString(ParticipantIndexes.NAME),
                        rs.getString(ParticipantIndexes.SURNAME),
                        rs.getString(ParticipantIndexes.INFO),
                        rs.getString(ParticipantIndexes.IMAGE));
            }
            return participant;
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_CANT_SELECT_PARTICIPANT_EXCEPTION);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    private static class ParticipantIndexes {
        private static final int ID = 1;
        private static final int NAME = 2;
        private static final int SURNAME = 3;
        private static final int INFO = 4;
        private static final int IMAGE = 5;
    }

    private static class ParticipantInsertIndexes {
        private static final int NAME = 1;
        private static final int SURNAME = 2;
        private static final int INFO = 3;
        private static final int IMAGE = 4;
    }
}
