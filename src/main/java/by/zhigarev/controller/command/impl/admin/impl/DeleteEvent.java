package by.zhigarev.controller.command.impl.admin.impl;

import by.zhigarev.bean.Bet;
import by.zhigarev.bean.Event;
import by.zhigarev.bean.UserBet;
import by.zhigarev.controller.command.CommandProvider;
import by.zhigarev.controller.command.impl.admin.AdminCommand;
import by.zhigarev.service.*;
import by.zhigarev.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteEvent extends AdminCommand {
    private static final Logger logger = Logger.getLogger(DeleteEvent.class);
    private static final String MESSAGE_SERVICE_EXCEPTION = "service exception";

    private static final String PARAMETER_EVENT_ID = "eventId";
    private static final String ATTRIBUTE_MESSAGE_SUCCESS = "message_success";
    private static final String ATTRIBUTE_MESSAGE_ERROR = "message_error";
    private static final String MESSAGE_SUCCESS = "success";
    private static final String MESSAGE_ERROR = "error";

    private static final EventService eventService = ServiceProvider.getEventService();
    private static final BetService betService = ServiceProvider.getBetService();
    private static final UserBetService userBetService = ServiceProvider.getUserBetService();
    private static final AccountService accountService = ServiceProvider.getAccountService();
    private static final CommandProvider commandProvider = CommandProvider.getInstance();

    private static final String GO_TO_FUTURE_EVENTS_PAGE_COMMAND = "go_to_future_events_page_admin";

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int STATUS_RETURN_ID = 4;
        int eventId = Integer.parseInt(request.getParameter(PARAMETER_EVENT_ID));
        try {
            Event event = eventService.getEventById(eventId);
            List<Bet> betList = betService.getAllBetsOfEvent(event);
            for (Bet bet : betList) {
                betService.updateBetStatusById(bet.getId(), STATUS_RETURN_ID);
                List<UserBet> userBets = userBetService.getAllBetsByBetId(bet.getId());
                for (UserBet userBet : userBets) {
                    accountService.payMoneyByAccountId(userBet.getAccount().getId(), userBet.getBetAmount());
                }
            }
            eventService.changeEventStatusToPastById(eventId);
            request.setAttribute(ATTRIBUTE_MESSAGE_SUCCESS, MESSAGE_SUCCESS);
            commandProvider.getCommand(GO_TO_FUTURE_EVENTS_PAGE_COMMAND).execute(request, response);
        } catch (ServiceException e) {
            logger.error(MESSAGE_SERVICE_EXCEPTION);
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR, MESSAGE_ERROR);
            commandProvider.getCommand(GO_TO_FUTURE_EVENTS_PAGE_COMMAND).execute(request, response);
        }
    }
}
