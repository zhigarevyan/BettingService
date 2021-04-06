package by.zhigarev.controller.command.impl.admin.impl.go;

import by.zhigarev.controller.command.impl.admin.AdminCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToAddParticipantPage extends AdminCommand {
    private static final String ADD_PARTICIPANT_FRAGMENT_PATH = "admin/addParticipant.jsp";
    private static final String ADMIN_PAGE_PATH = "WEB-INF/jsp/admin.jsp";
    private static final String ATTRIBUTE_ADMIN_CONTENT = "admin_content";
    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(ATTRIBUTE_ADMIN_CONTENT,ADD_PARTICIPANT_FRAGMENT_PATH);
        request.getRequestDispatcher(ADMIN_PAGE_PATH).forward(request,response);
    }
}
