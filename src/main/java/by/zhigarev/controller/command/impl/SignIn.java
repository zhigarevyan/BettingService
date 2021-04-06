package by.zhigarev.controller.command.impl;


import by.zhigarev.bean.Account;
import by.zhigarev.bean.User;
import by.zhigarev.bean.info.SignInInfo;
import by.zhigarev.controller.command.Command;
import by.zhigarev.controller.command.CommandProvider;
import by.zhigarev.service.AccountService;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.UserService;
import by.zhigarev.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignIn implements Command {
    private static final String USER_ATTRIBUTE = "user";
    private static final String ACCOUNT_ATTRIBUTE = "account";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String PARAMETER_LOGIN = "login";
    private static final String COMMAND_GO_TO_USER_PAGE = "go_to_user_page";
    private static final String COMMAND_GO_TO_ADMIN_PAGE = "go_to_admin_page";
    private static final String GO_TO_SIGN_IN_PAGE = "go_to_sign_in_page";
    private static final String MESSAGE_ERROR = "Error";
    private static final String ATTRIBUTE_MESSAGE_ERROR = "message_error";
    private static final CommandProvider commandProvider = CommandProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login;
        String password;

        login = request.getParameter(PARAMETER_LOGIN);
        password = request.getParameter(PARAMETER_PASSWORD);

        UserService userService = ServiceProvider.getUserService();
        AccountService accountService = ServiceProvider.getAccountService();

        User user;
        Account account;
        try {
            user = userService.signIn(new SignInInfo(login, password));
            account = accountService.getAccountByUser(user);

            if (user == null) {
                commandProvider.getCommand(GO_TO_SIGN_IN_PAGE).execute(request, response);
            } else {
                request.getSession().setAttribute(USER_ATTRIBUTE, user);
                request.getSession().setAttribute(ACCOUNT_ATTRIBUTE, account);

                if (user.getRole() == 1) {
                    commandProvider.getCommand(COMMAND_GO_TO_USER_PAGE).execute(request, response);
                } else if (user.getRole() == 2) {
                    commandProvider.getCommand(COMMAND_GO_TO_ADMIN_PAGE).execute(request, response);
                }
            }
        } catch (ServiceException e) {
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR,MESSAGE_ERROR);
            commandProvider.getCommand(GO_TO_SIGN_IN_PAGE).execute(request, response);
        }

    }

}
