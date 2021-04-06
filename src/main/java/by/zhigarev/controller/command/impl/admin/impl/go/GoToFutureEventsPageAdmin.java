package by.zhigarev.controller.command.impl.admin.impl.go;


import by.zhigarev.bean.Event;
import by.zhigarev.controller.command.impl.admin.AdminCommand;
import by.zhigarev.controller.command.impl.user.UserCommand;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.exception.ServiceException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class GoToFutureEventsPageAdmin extends AdminCommand {
    private static final String FUTURE_EVENTS_FRAGMENT_PATH = "futureEvents.jsp";
    private static final String ADMIN_PAGE_PATH = "WEB-INF/jsp/admin.jsp";
    private static final String ATTRIBUTE_ADMIN_CONTENT = "admin_content";
    private static final String ATTRIBUTE_EVENTS = "events";
    private static final String MESSAGE_ERROR = "Error";
    private static final String ATTRIBUTE_MESSAGE_ERROR = "message_error";

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Event> events = ServiceProvider.getEventService().getAllFutureEvents();
            request.setAttribute(ATTRIBUTE_ADMIN_CONTENT, FUTURE_EVENTS_FRAGMENT_PATH);
            request.setAttribute(ATTRIBUTE_EVENTS, events);
            request.getRequestDispatcher(ADMIN_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR,MESSAGE_ERROR);
            request.getRequestDispatcher(ADMIN_PAGE_PATH).forward(request, response);
        }

    }
}
