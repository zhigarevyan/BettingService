package by.zhigarev.service;

import by.zhigarev.service.impl.*;

public class ServiceProvider {
    private static final UserService userService = UserServiceImpl.getInstance();
    private static final AccountService accountService = AccountServiceImpl.getInstance();
    private static final ParticipantService participantService = ParticipantServiceImpl.getInstance();
    private static final EventService eventService = EventServiceImpl.getInstance();
    private static final OutcomeTypeService outcomeTypeService = OutcomeTypeServiceImpl.getInstance();
    private static final BetService betService = BetServiceImpl.getInstance();
    private static final UserBetService userBetService = UserBetServiceImpl.getInstance();
    private static final BetStatusService betStatusService = BetStatusServiceImpl.getInstance();
    private static final EventResultService eventResultService = EventResultServiceImpl.getInstance();


    public static UserService getUserService() {
        return userService;
    }

    public static AccountService getAccountService() {
        return accountService;
    }

    public static ParticipantService getParticipantService() {
        return participantService;
    }

    public static EventService getEventService() {
        return eventService;
    }

    public static OutcomeTypeService getOutcomeTypeService() {
        return outcomeTypeService;
    }

    public static BetService getBetService() {
        return betService;
    }

    public static UserBetService getUserBetService() {
        return userBetService;
    }

    public static BetStatusService getBetStatusService() {
        return betStatusService;
    }

    public static EventResultService getEventResultService() {
        return eventResultService;
    }
}
