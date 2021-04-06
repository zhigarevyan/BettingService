package by.zhigarev.controller.command.impl.go;


import by.zhigarev.controller.command.Command;
import by.zhigarev.controller.command.CommandProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToMainPage implements Command {
    private static final String WELCOME_PAGE_PATH = "/welcome.jsp";
    private static final String ATTRIBUTE_AUTH = "auth";
    private static final String GO_TO_MAIN_PAGE_COMMAND = "go_to_main_page";
    private static final String ATTRIBUTE_MESSAGE_ERROR = "message_error";
    private static final String MESSAGE_ERROR = "Error";
    private static final CommandProvider commandProvider = CommandProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session == null) {
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR,MESSAGE_ERROR);
            commandProvider.getCommand(GO_TO_MAIN_PAGE_COMMAND);
            return;
        }

        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);

        if (isAuth == null || !isAuth) {
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR,MESSAGE_ERROR);
            commandProvider.getCommand(GO_TO_MAIN_PAGE_COMMAND);
            return;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(WELCOME_PAGE_PATH);
        requestDispatcher.forward(request, response);

    }

}
