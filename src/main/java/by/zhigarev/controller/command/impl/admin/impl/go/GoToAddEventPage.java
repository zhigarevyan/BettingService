package by.zhigarev.controller.command.impl.admin.impl.go;

import by.zhigarev.bean.Participant;
import by.zhigarev.controller.command.impl.admin.AdminCommand;
import by.zhigarev.service.ParticipantService;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToAddEventPage extends AdminCommand {
    private static final Logger logger = Logger.getLogger(GoToAddEventPage.class);
    private static final String MESSAGE_SERVICE_EXCEPTION = "service exception";
    private static final String ADD_EVENT_FRAGMENT_PATH = "admin/addEvent.jsp";
    private static final String ADMIN_PAGE_PATH = "WEB-INF/jsp/admin.jsp";
    private static final String ATTRIBUTE_ADMIN_CONTENT = "admin_content";
    private static final String ATTRIBUTE_PARTICIPANTS = "participants";
    private static final String MESSAGE_ERROR = "Error";
    private static final String ATTRIBUTE_MESSAGE_ERROR = "message_error";
    private static final ParticipantService participantService = ServiceProvider.getParticipantService();

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Participant> participants = participantService.getAllParticipants();
            request.setAttribute(ATTRIBUTE_ADMIN_CONTENT, ADD_EVENT_FRAGMENT_PATH);
            request.setAttribute(ATTRIBUTE_PARTICIPANTS, participants);
            request.getRequestDispatcher(ADMIN_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            logger.error(MESSAGE_SERVICE_EXCEPTION);
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR, MESSAGE_ERROR);
            request.getRequestDispatcher(ADMIN_PAGE_PATH).forward(request, response);
        }
    }
}
