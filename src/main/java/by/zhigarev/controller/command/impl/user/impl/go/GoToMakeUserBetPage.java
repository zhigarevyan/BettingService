package by.zhigarev.controller.command.impl.user.impl.go;

import by.zhigarev.bean.Bet;
import by.zhigarev.bean.Event;
import by.zhigarev.controller.command.impl.user.UserCommand;
import by.zhigarev.service.BetService;
import by.zhigarev.service.EventService;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToMakeUserBetPage extends UserCommand {
    private static final Logger logger = Logger.getLogger(GoToMakeUserBetPage.class);
    private static final String MESSAGE_SERVICE_EXCEPTION = "service exception";
    private static final String MAKE_USER_BET_FRAGMENT_PATH = "user/makeUserBet.jsp";
    private static final String USER_PAGE_PATH = "WEB-INF/jsp/user.jsp";
    private static final String ATTRIBUTE_USER_CONTENT = "user_content";
    private static final String ATTRIBUTE_EVENT = "event";
    private static final String ATTRIBUTE_BET = "bet";
    private static final String PARAMETER_BET_ID = "betId";
    private static final String ATTRIBUTE_MESSAGE_ERROR = "message_error";
    private static final String MESSAGE_ERROR = "error";
    private static final BetService betService = ServiceProvider.getBetService();
    private static final EventService eventService = ServiceProvider.getEventService();

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int betId = Integer.parseInt(request.getParameter(PARAMETER_BET_ID));
        try {
            Bet bet = betService.getBetById(betId);
            Event event = eventService.getEventById(bet.getEvent().getId());
            request.setAttribute(ATTRIBUTE_USER_CONTENT, MAKE_USER_BET_FRAGMENT_PATH);
            request.setAttribute(ATTRIBUTE_EVENT, event);
            request.setAttribute(ATTRIBUTE_BET, bet);
            request.getRequestDispatcher(USER_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            logger.error(MESSAGE_SERVICE_EXCEPTION);
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR, MESSAGE_ERROR);
            request.getRequestDispatcher(USER_PAGE_PATH).forward(request, response);
        }

    }
}
