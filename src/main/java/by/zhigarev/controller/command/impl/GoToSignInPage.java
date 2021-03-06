package by.zhigarev.controller.command.impl;

import by.zhigarev.controller.command.Command;
import by.zhigarev.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToSignInPage implements Command {
    private static final String SIGN_IN_PAGE_PATH="/WEB-INF/signIn.jsp";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(SIGN_IN_PAGE_PATH);
        requestDispatcher.forward(request,response);
    }
}
