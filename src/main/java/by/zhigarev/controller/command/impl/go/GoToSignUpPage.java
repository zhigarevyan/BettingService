package by.zhigarev.controller.command.impl.go;

import by.zhigarev.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToSignUpPage implements Command {
    private static final String SIGN_UP_JSP_PATH = "/signUp.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(SIGN_UP_JSP_PATH);
        requestDispatcher.forward(request, response);
    }
}
