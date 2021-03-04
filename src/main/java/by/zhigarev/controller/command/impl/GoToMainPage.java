package by.zhigarev.controller.command.impl;



import by.zhigarev.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToMainPage implements Command {
	private static final String WELCOME_PAGE_PATH ="/WEB-INF/welcome.jsp";
	private static final String GO_TO_SIGN_IN_PAGE_COMMAND = "go_to_sign_in_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		if(session == null) {
			response.sendRedirect("Controller?command=gotoindexpage&message=eeerrror2");
			return;			
		}
		
		Boolean isAuth = (Boolean) session.getAttribute("auth");

		if (isAuth == null || !isAuth) {
			response.sendRedirect("Controller?command=gotoindexpage&message=eeerrror");
			return;
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(WELCOME_PAGE_PATH);
		requestDispatcher.forward(request, response);

	}

}
