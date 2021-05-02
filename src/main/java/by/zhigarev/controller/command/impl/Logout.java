package by.zhigarev.controller.command.impl;

import by.zhigarev.controller.command.Command;
import by.zhigarev.controller.command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Logout implements Command {
    private static final String GO_TO_WELCOME_PAGE_COMMAND = "GO_TO_WELCOME_PAGE";
    private static final String ATTRIBUTE_MESSAGE_SUCCESS = "message_success";
    private static final String MESSAGE_SUCCESS = "Success";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getSession().invalidate();
        request.setAttribute(ATTRIBUTE_MESSAGE_SUCCESS, MESSAGE_SUCCESS);
        CommandProvider.getInstance().getCommand(GO_TO_WELCOME_PAGE_COMMAND).execute(request, response);
    }

}
