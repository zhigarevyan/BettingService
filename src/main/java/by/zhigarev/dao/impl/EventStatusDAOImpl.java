package by.zhigarev.dao.impl;


import by.zhigarev.bean.EventStatus;
import by.zhigarev.dao.EventStatusDAO;
import by.zhigarev.dao.connection.ConnectionPool;
import by.zhigarev.dao.connection.impl.ConnectionPoolImpl;
import by.zhigarev.dao.exception.DAOException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventStatusDAOImpl implements EventStatusDAO {
    private static final String SQL_SELECT_STATUS_BY_ID = "select * from EventStatuses where id = ?";
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
    private static final String MESSAGE_SQL_EXCEPTION = "SQL exception dao layer";
    private static final String MESSAGE_SELECT_STATUS_EXCEPTION = "cant select status";
    private static final EventStatusDAOImpl instance = new EventStatusDAOImpl();

    public static EventStatusDAOImpl getInstance() {
        return instance;
    }

    @Override
    public EventStatus getStatusById(int id) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        EventStatus eventStatus = null;
        try{
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_SELECT_STATUS_BY_ID);
            ps.setInt(EventStatusIndexes.ID,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                eventStatus = new EventStatus(rs.getInt(EventStatusIndexes.ID),rs.getString(EventStatusIndexes.STATUS));
            }
            return eventStatus;
        } catch (DAOException e) {
            throw new DAOException(MESSAGE_SELECT_STATUS_EXCEPTION,e);
        } catch (SQLException e) {
            throw new DAOException(MESSAGE_SQL_EXCEPTION,e);
        }finally {
            connectionPool.closeConnection(connection,ps);
        }
    }
    private static class EventStatusIndexes{
        private static final int ID = 1;
        private static final int STATUS = 2;
    }
}
