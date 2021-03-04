package by.zhigarev.controller.command.impl;


import by.zhigarev.controller.command.Command;
import by.zhigarev.controller.command.CommandProvider;
import by.zhigarev.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Logout implements Command {
	private static final String GO_TO_WELCOME_PAGE="Controller?command=GO_TO_WELCOME_PAGE&message=logout ok";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
		
		request.getSession().invalidate();
		response.sendRedirect(GO_TO_WELCOME_PAGE);
		
	}

}
