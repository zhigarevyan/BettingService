package by.zhigarev.controller.command.impl.go;

import by.zhigarev.controller.command.Command;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class GoToWelcomePage implements Command {
	private static final String WELCOME_JSP_PATH = "/welcome.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(WELCOME_JSP_PATH);
		requestDispatcher.forward(request, response);
	}

}
