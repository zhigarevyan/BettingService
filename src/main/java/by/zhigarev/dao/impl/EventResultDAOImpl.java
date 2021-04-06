package by.zhigarev.dao.impl;

import by.zhigarev.bean.Event;
import by.zhigarev.bean.info.EventResultInfo;
import by.zhigarev.dao.EventResultDAO;
import by.zhigarev.dao.connection.ConnectionPool;
import by.zhigarev.dao.connection.impl.ConnectionPoolImpl;
import by.zhigarev.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventResultDAOImpl implements EventResultDAO {
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
    private static final EventResultDAOImpl instance = new EventResultDAOImpl();
    private static final String SQL_GET_ALL_RESULTS = "select * from eventresults";
    private static final String SQL_GET_RESULT_BY_EVENT = "select * from eventresults where event_id = ?";
    private static final String SQL_INSERT_RESULT = "insert into eventresults(event_id,winner,winner_score,loser_score) values(?,?,?,?)";

    private static final String MESSAGE_SQL_EXCEPTION = "sql exception on dao layer";

    public static EventResultDAOImpl getInstance() {
        return instance;
    }

    @Override
    public List<EventResultInfo> getAllResults() throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        List<EventResultInfo> eventResultInfoList = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_GET_ALL_RESULTS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EventResultInfo eventResultInfo = new EventResultInfo(
                        rs.getInt(EventResultIndexes.ID),
                        rs.getInt(EventResultIndexes.EVENT_ID),
                        rs.getInt(EventResultIndexes.WINNER),
                        rs.getInt(EventResultIndexes.WINNER_SCORE),
                        rs.getInt(EventResultIndexes.LOSER_SCORE)
                );
                eventResultInfoList.add(eventResultInfo);
            }
            return eventResultInfoList;
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    @Override
    public EventResultInfo getResultByEvent(Event event) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        EventResultInfo eventResultInfo = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_GET_RESULT_BY_EVENT);
            ps.setInt(EventResultInsertIndexes.EVENT_ID,event.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 eventResultInfo = new EventResultInfo(
                        rs.getInt(EventResultIndexes.ID),
                        rs.getInt(EventResultIndexes.EVENT_ID),
                        rs.getInt(EventResultIndexes.WINNER),
                        rs.getInt(EventResultIndexes.WINNER_SCORE),
                        rs.getInt(EventResultIndexes.LOSER_SCORE)
                );
            }
            return eventResultInfo;
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    @Override
    public boolean createResultForEvent(EventResultInfo eventResult) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_INSERT_RESULT);
            ps.setInt(EventResultInsertIndexes.EVENT_ID, eventResult.getEventId());
            ps.setInt(EventResultInsertIndexes.WINNER, eventResult.getWinner());
            ps.setInt(EventResultInsertIndexes.WINNER_SCORE, eventResult.getFirstParticipantScore());
            ps.setInt(EventResultInsertIndexes.LOSER_SCORE, eventResult.getSecondParticipantScore());
            return ps.execute();
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    private static class EventResultInsertIndexes {
        private static final int EVENT_ID = 1;
        private static final int WINNER = 2;
        private static final int WINNER_SCORE = 3;
        private static final int LOSER_SCORE = 4;
    }

    private static class EventResultIndexes {
        private static final int ID = 1;
        private static final int EVENT_ID = 2;
        private static final int WINNER = 3;
        private static final int WINNER_SCORE = 4;
        private static final int LOSER_SCORE = 5;
    }
}
