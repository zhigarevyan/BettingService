package by.zhigarev.dao.impl;

import by.zhigarev.bean.Event;
import by.zhigarev.bean.info.EventInfo;
import by.zhigarev.dao.EventDAO;
import by.zhigarev.dao.connection.ConnectionPool;
import by.zhigarev.dao.connection.impl.ConnectionPoolImpl;
import by.zhigarev.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAOImpl implements EventDAO {
    private static final String SQL_CREATE_EVENT = "insert into Events(first_participant,second_participant,location, info, start_date_time, status) values (?,?,?,?,?,?)";
    private static final String SQL_GET_ALL_FUTURE_EVENTS = "select * from Events where status = 1 order by start_date_time desc";
    private static final String SQL_GET_EVENT_BY_ID = "select * from Events where id = ?";
    private static final String SQL_CHANGE_STATUS_TO_PAST = "update Events set status = 2 where id = ?";
    private static final String SQL_DELETE_EVENT_BY_ID = "delete from events where id = ?";

    private static final String MESSAGE_CREATE_EVENT_EXCEPTION = "Can't create event";
    private static final String MESSAGE_GET_FUTURE_EVENTS_EXCEPTION = "Can't get future events";
    private static final String MESSAGE_GET_EVENT_BY_ID_EXCEPTION = "Can't get event by id";

    private static final EventDAOImpl instance = new EventDAOImpl();
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    public static EventDAOImpl getInstance() {
        return instance;
    }


    @Override
    public List<EventInfo> getAllFutureEvents() throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        List<EventInfo> events = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_GET_ALL_FUTURE_EVENTS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EventInfo event = new EventInfo(
                        rs.getInt(EventIndexes.ID),
                        rs.getInt(EventIndexes.FIRST_PARTICIPANT),
                        rs.getInt(EventIndexes.SECOND_PARTICIPANT),
                        rs.getString(EventIndexes.LOCATION),
                        rs.getString(EventIndexes.INFO),
                        rs.getDate(EventIndexes.START_DATE_TIME),
                        rs.getInt(EventIndexes.STATUS));
                events.add(event);
            }
            return events;
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_GET_FUTURE_EVENTS_EXCEPTION);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    @Override
    public boolean createEvent(EventInfo event) throws DAOException {
        final int STATUS = 1;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_CREATE_EVENT);
            ps.setInt(EventInsertIndexes.FIRST_PARTICIPANT, event.getFirstParticipant());
            ps.setInt(EventInsertIndexes.SECOND_PARTICIPANT, event.getSecondParticipant());
            ps.setString(EventInsertIndexes.LOCATION, event.getLocation());
            ps.setString(EventInsertIndexes.INFO, event.getInfo());
            ps.setDate(EventInsertIndexes.START_DATE_TIME, (Date) event.getStartDateTime());
            ps.setInt(EventInsertIndexes.STATUS, STATUS);
            return ps.execute();

        } catch (SQLException e) {
            throw new DAOException(MESSAGE_CREATE_EVENT_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }

    }

    @Override
    public EventInfo getEventById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        EventInfo event = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_GET_EVENT_BY_ID);
            ps.setInt(EventIndexes.ID, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                event = new EventInfo(id,
                        rs.getInt(EventIndexes.FIRST_PARTICIPANT),
                        rs.getInt(EventIndexes.SECOND_PARTICIPANT),
                        rs.getString(EventIndexes.LOCATION),
                        rs.getString(EventIndexes.INFO),
                        rs.getDate(EventIndexes.START_DATE_TIME),
                        rs.getInt(EventIndexes.STATUS));
            }
            return event;
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_GET_EVENT_BY_ID_EXCEPTION, e);
        }finally {
            connectionPool.closeConnection(connection,ps);
        }
    }

    @Override
    public boolean deleteEventById(int eventId) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_DELETE_EVENT_BY_ID);
            ps.setInt(EventIndexes.ID,eventId);
            return ps.execute();
        }catch (SQLException e) {
            throw new DAOException(MESSAGE_GET_EVENT_BY_ID_EXCEPTION, e);
        }finally {
            connectionPool.closeConnection(connection,ps);
        }
    }

    @Override
    public boolean updateEvent(Event oldEvent, Event newEvent) throws DAOException {
        return false;
    }

    @Override
    public List<EventInfo> getAllPastEvents() throws DAOException {
        return null;
    }

    @Override
    public boolean changeEventStatusToPastById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = connectionPool.getConnection();
            ps =connection.prepareStatement(SQL_CHANGE_STATUS_TO_PAST);
            ps.setInt(EventIndexes.ID,id);
            return ps.execute();
        }catch (SQLException e) {
            throw new DAOException(MESSAGE_GET_EVENT_BY_ID_EXCEPTION, e);
        }finally {
            connectionPool.closeConnection(connection,ps);
        }
    }

    private static class EventInsertIndexes {
        public static final int FIRST_PARTICIPANT = 1;
        public static final int SECOND_PARTICIPANT = 2;
        public static final int LOCATION = 3;
        public static final int INFO = 4;
        public static final int START_DATE_TIME = 5;
        public static final int STATUS = 6;
    }

    private static class EventIndexes {
        public static final int ID = 1;
        public static final int FIRST_PARTICIPANT = 2;
        public static final int SECOND_PARTICIPANT = 3;
        public static final int LOCATION = 4;
        public static final int INFO = 5;
        public static final int START_DATE_TIME = 6;
        public static final int STATUS = 7;
    }
}
