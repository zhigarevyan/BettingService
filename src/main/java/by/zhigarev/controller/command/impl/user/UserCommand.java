package by.zhigarev.controller.command.impl.user;

import by.zhigarev.controller.command.Command;
import by.zhigarev.controller.command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class UserCommand implements Command {
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_MESSAGE = "message";
    private static final String MESSAGE_SIGN_IN = "Sign in to continue";
    private static final String COMMAND_SIGN_IN = "sign_in";


    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkAuthAndProcess(request, response);
    }

    private void checkAuthAndProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute(ATTRIBUTE_USER) == null) {
            request.setAttribute(ATTRIBUTE_MESSAGE, MESSAGE_SIGN_IN);
            CommandProvider.getInstance().getCommand(COMMAND_SIGN_IN).execute(request, response);
        } else {
            process(request, response);
        }
    }

    protected abstract void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
