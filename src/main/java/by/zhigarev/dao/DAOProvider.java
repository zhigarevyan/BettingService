package by.zhigarev.dao;

import by.zhigarev.dao.impl.*;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    public static DAOProvider getInstance() {
        return instance;
    }

    private static final UserDAO userDAO = UserDAOImpl.getInstance();
    private static final AccountDAO accountDAO = AccountDAOImpl.getInstance();
    private static final ParticipantDAO participantDAO = ParticipantDAOImpl.getInstance();
    private static final EventDAO eventDAO = EventDAOImpl.getInstance();
    private static final EventStatusDAO eventStatusDAO = EventStatusDAOImpl.getInstance();
    private static final BetDAO betDAO = BetDAOImpl.getInstance();
    private static final OutcomeTypeDAO outcomeTypeDAO = OutcomeTypeDAOImpl.getInstance();
    private static final BetStatusDAO betStatusDAO = BetStatusDAOImpl.getInstance();
    private static final UserBetDAO userBetDAO = UserBetDAOImpl.getInstance();
    private static final EventResultDAO eventResultDAO = EventResultDAOImpl.getInstance();

    public static UserDAO getUserDAO() {
        return userDAO;
    }

    public static AccountDAO getAccountDAO() {
        return accountDAO;
    }

    public static ParticipantDAO getParticipantDAO() {
        return participantDAO;
    }

    public static EventDAO getEventDAO() {
        return eventDAO;
    }

    public static EventStatusDAO getEventStatusDAO() {
        return eventStatusDAO;
    }

    public static BetDAO getBetDAO() {
        return betDAO;
    }

    public static OutcomeTypeDAO getOutcomeTypeDAO() {
        return outcomeTypeDAO;
    }

    public static BetStatusDAO getBetStatusDAO() {
        return betStatusDAO;
    }

    public static UserBetDAO getUserBetDAO() {
        return userBetDAO;
    }

    public static EventResultDAO getEventResultDAO() {
        return eventResultDAO;
    }
}

