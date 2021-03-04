package by.zhigarev.controller.command.impl;



import by.zhigarev.bean.SignUpInfo;
import by.zhigarev.controller.command.Command;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.UserService;
import by.zhigarev.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

public class SignUp implements Command {
	private static final String MESSAGE_SIGNUP_EXCEPTION = "SignUp exception";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {

		UserService userService = ServiceProvider.getInstance().getUserService();

		String name = request.getParameter("name");
		String surName =request.getParameter("surname");
		String login =request.getParameter("login");
		String password =request.getParameter("password");
		String email =request.getParameter("email");
		String date =request.getParameter("date");

		SignUpInfo signUpInfo = new SignUpInfo(name,surName,login,password,email,Date.valueOf(date));
		try{
			userService.signUp(signUpInfo);
		} catch (ServiceException e) {
			throw new ServiceException(MESSAGE_SIGNUP_EXCEPTION,e);
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("auth", true);
		response.sendRedirect("Controller?command=gotomainpage&message=Registration ok");
		//RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
		//requestDispatcher.forward(request, response);
		
	}

}
