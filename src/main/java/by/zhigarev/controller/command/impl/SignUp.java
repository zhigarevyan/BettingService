package by.zhigarev.controller.command.impl;


import by.zhigarev.bean.Account;
import by.zhigarev.bean.info.SignUpInfo;
import by.zhigarev.bean.User;
import by.zhigarev.controller.command.Command;
import by.zhigarev.controller.command.CommandProvider;
import by.zhigarev.dao.exception.DuplicateLoginException;
import by.zhigarev.service.AccountService;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.UserService;
import by.zhigarev.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class SignUp implements Command {
    private static final String COMMAND_GO_TO_USER_PAGE = "GO_TO_USER_PAGE";
    private static final String COMMAND_GO_SIGN_UP_PAGE = "GO_TO_SIGN_UP_PAGE";
    private static final String DUPLICATE_LOGIN_MESSAGE = "duplicate login message";
    private static final String ATTRIBUTE_MESSAGE_ERROR = "message_error";
    private static final String MESSAGE_ERROR = "Error";
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_ACCOUNT = "account";
    private static final String PARAMETER_NAME = "name";
    private static final String PARAMETER_SURNAME = "surname";
    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String PARAMETER_EMAIL = "email";
    private static final String PARAMETER_DATE = "birthday";
    private static final CommandProvider commandProvider = CommandProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        UserService userService = ServiceProvider.getUserService();
        AccountService accountService = ServiceProvider.getAccountService();
        User user;
        Account account;
        String name = request.getParameter(PARAMETER_NAME);
        String surName = request.getParameter(PARAMETER_SURNAME);
        String login = request.getParameter(PARAMETER_LOGIN);
        String password = request.getParameter(PARAMETER_PASSWORD);
        String email = request.getParameter(PARAMETER_EMAIL);
        String date = request.getParameter(PARAMETER_DATE);

        SignUpInfo signUpInfo = new SignUpInfo(name, surName, login, password, email, Date.valueOf(date));
        try {
            user = userService.signUp(signUpInfo);
            account = accountService.createAccountByUser(user);

            request.getSession(true).setAttribute(ATTRIBUTE_USER, user);
            request.getSession(true).setAttribute(ATTRIBUTE_ACCOUNT, account);
            commandProvider.getCommand(COMMAND_GO_TO_USER_PAGE).execute(request, response);

        } catch (ServiceException e) {
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR, MESSAGE_ERROR);
            commandProvider.getCommand(COMMAND_GO_SIGN_UP_PAGE).execute(request, response);
        } catch (DuplicateLoginException e) {
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR, DUPLICATE_LOGIN_MESSAGE);
            commandProvider.getCommand(COMMAND_GO_SIGN_UP_PAGE).execute(request, response);
        }
    }

}
