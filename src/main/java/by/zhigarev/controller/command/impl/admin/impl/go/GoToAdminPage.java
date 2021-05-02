package by.zhigarev.controller.command.impl.admin.impl.go;

import by.zhigarev.controller.command.impl.admin.AdminCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToAdminPage extends AdminCommand {
    private static final String ADMIN_PAGE_PATH = "/WEB-INF/jsp/admin.jsp";

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(ADMIN_PAGE_PATH).forward(request, response);
    }
}
