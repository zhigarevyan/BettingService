package by.zhigarev.controller.command.impl.admin;

import by.zhigarev.bean.User;
import by.zhigarev.controller.command.Command;
import by.zhigarev.controller.command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AdminCommand implements Command {
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_MESSAGE = "message";
    private static final String MESSAGE_LOG_IN_TO_CONTINUE_LOCALE = "sign in to continue";
    private static final String NO_RIGHTS_PAGE_URL = "no_rights.jsp";
    private static final String COMMAND_SIGN_IN = "go_to_sign_in_page";

    @Override
    public final void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkAuthAndProcess(request, response);
    }

    private void checkAuthAndProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(ATTRIBUTE_USER);
        if (user != null) {
            if (user.getRole() == 2) {
                process(request, response);
            } else {
                request.getRequestDispatcher(NO_RIGHTS_PAGE_URL).forward(request, response);
            }
        } else {
            request.setAttribute(ATTRIBUTE_MESSAGE, MESSAGE_LOG_IN_TO_CONTINUE_LOCALE);
            CommandProvider.getInstance().getCommand(COMMAND_SIGN_IN).execute(request, response);
        }
    }

    protected abstract void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
