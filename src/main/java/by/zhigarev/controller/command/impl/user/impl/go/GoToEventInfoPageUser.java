package by.zhigarev.controller.command.impl.user.impl.go;

import by.zhigarev.bean.Bet;
import by.zhigarev.bean.Event;
import by.zhigarev.bean.OutcomeType;
import by.zhigarev.controller.command.impl.user.UserCommand;
import by.zhigarev.service.BetService;
import by.zhigarev.service.EventService;
import by.zhigarev.service.OutcomeTypeService;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToEventInfoPageUser extends UserCommand {
    private static final Logger logger = Logger.getLogger(GoToEventInfoPageUser.class);
    private static final String MESSAGE_SERVICE_EXCEPTION = "service exception";
    private static final String EVENT_INFO_FRAGMENT_PATH = "eventInfo.jsp";
    private static final String USER_PAGE_PATH = "/WEB-INF/jsp/user.jsp";
    private static final String ATTRIBUTE_USER_CONTENT = "user_content";
    private static final String ATTRIBUTE_EVENT_ID = "eventId";
    private static final String ATTRIBUTE_EVENT = "event";
    private static final String ATTRIBUTE_EVENT_BETS = "eventBets";
    private static final String ATTRIBUTE_OUTCOME_TYPES = "outcomeTypes";
    private static final String ATTRIBUTE_MESSAGE_ERROR = "message_error";
    private static final String MESSAGE_ERROR = "error";

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EventService eventService = ServiceProvider.getEventService();
        OutcomeTypeService outcomeTypeService = ServiceProvider.getOutcomeTypeService();
        BetService betService = ServiceProvider.getBetService();
        try {
            List<OutcomeType> outcomeTypes = outcomeTypeService.getAllTypes();
            Event event = eventService.getEventById(Integer.parseInt(request.getParameter(ATTRIBUTE_EVENT_ID)));
            List<Bet> bets = betService.getAllBetsOfEvent(event);

            request.setAttribute(ATTRIBUTE_EVENT, event);
            request.setAttribute(ATTRIBUTE_EVENT_BETS, bets);
            request.setAttribute(ATTRIBUTE_OUTCOME_TYPES, outcomeTypes);
            request.setAttribute(ATTRIBUTE_USER_CONTENT, EVENT_INFO_FRAGMENT_PATH);
            request.getRequestDispatcher(USER_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            logger.error(MESSAGE_SERVICE_EXCEPTION);
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR, MESSAGE_ERROR);
            request.getRequestDispatcher(USER_PAGE_PATH).forward(request, response);
        }
    }
}
