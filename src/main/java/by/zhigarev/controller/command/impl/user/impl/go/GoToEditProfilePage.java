package by.zhigarev.controller.command.impl.user.impl.go;

import by.zhigarev.controller.command.impl.user.UserCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToEditProfilePage extends UserCommand {
    private static final String USER_INFO_PAGE_PATH = "/WEB-INF/jsp/editUserInfo.jsp";

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(USER_INFO_PAGE_PATH).forward(request, response);
    }
}
