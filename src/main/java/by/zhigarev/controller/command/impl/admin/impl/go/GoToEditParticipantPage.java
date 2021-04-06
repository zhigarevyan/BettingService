package by.zhigarev.controller.command.impl.admin.impl.go;

import by.zhigarev.bean.Participant;
import by.zhigarev.controller.command.CommandProvider;
import by.zhigarev.controller.command.impl.admin.AdminCommand;
import by.zhigarev.service.ParticipantService;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToEditParticipantPage extends AdminCommand {
    private static final String EDIT_PARTICIPANT_FRAGMENT_PATH = "admin/editParticipant.jsp";
    private static final String ADMIN_PAGE_PATH = "WEB-INF/jsp/admin.jsp";
    private static final String ATTRIBUTE_ADMIN_CONTENT = "admin_content";
    private static final String PARAMETER_PARTICIPANT_ID = "participantId";
    private static final String ATTRIBUTE_PARTICIPANT = "participant";
    private static final String GO_TO_PARTICIPANTS_PAGE_COMMAND = "go_to_participants_page";
    private static final ParticipantService participantService = ServiceProvider.getParticipantService();
    private static final CommandProvider commandProvider = CommandProvider.getInstance();

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int participantId = Integer.parseInt(request.getParameter(PARAMETER_PARTICIPANT_ID));
        try {
            Participant participant = participantService.getParticipantById(participantId);
            request.setAttribute(ATTRIBUTE_PARTICIPANT,participant);
            request.setAttribute(ATTRIBUTE_ADMIN_CONTENT, EDIT_PARTICIPANT_FRAGMENT_PATH);
            request.getRequestDispatcher(ADMIN_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            commandProvider.getCommand(GO_TO_PARTICIPANTS_PAGE_COMMAND).execute(request, response);
        }
    }
}
