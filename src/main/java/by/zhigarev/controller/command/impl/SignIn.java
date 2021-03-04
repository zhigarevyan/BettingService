package by.zhigarev.controller.command.impl;


import by.zhigarev.bean.SignInInfo;
import by.zhigarev.bean.User;
import by.zhigarev.controller.command.Command;
import by.zhigarev.controller.command.CommandProvider;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.UserService;
import by.zhigarev.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn implements Command {
	private static final String USER_ATTRIBUTE ="user";
	private static final String PASSWORD_ATTRIBUTE ="password";
	private static final String LOGIN_ATTRIBUTE ="login";
	private static final String COMMAND_GO_TO_MAIN ="go_to_main_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login;
		String password;

		login = request.getParameter(LOGIN_ATTRIBUTE);
		password = request.getParameter(PASSWORD_ATTRIBUTE);

		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();

		User user = null;
		RequestDispatcher requestDispatcher = null;
		try {
			user = userService.signIn(new SignInInfo(login, password));
			
			if (user == null) {
				response.sendRedirect("Controller?command=go_to_sign_in_page&message=wrong");
			}else {
				request.getSession().setAttribute(USER_ATTRIBUTE,user);
				request.getSession(true).setAttribute("auth", true);
				CommandProvider.getInstance().getCommand(COMMAND_GO_TO_MAIN).execute(request,response);
			}
		} catch (ServiceException e) {
			response.sendRedirect("Controller?command=gotoindexpage&message="+e.getMessage());
		}

	}

}
