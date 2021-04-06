package by.zhigarev.controller.command.impl.user.impl;

import by.zhigarev.bean.User;
import by.zhigarev.controller.command.CommandProvider;
import by.zhigarev.controller.command.impl.user.UserCommand;
import by.zhigarev.dao.exception.DuplicateLoginException;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.UserService;
import by.zhigarev.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class SaveProfileChanges extends UserCommand {
    private static final String ATTRIBUTE_MESSAGE_SUCCESS = "message_success";
    private static final String ATTRIBUTE_MESSAGE_ERROR = "message_error";
    private static final String MESSAGE_ERROR = "Error";
    private static final String MESSAGE_SUCCESS = "Success";
    private static final String MESSAGE_DUPLICATE_LOGIN_EXCEPTION = "duplicate login";
    private static final String ATTRIBUTE_USER = "user";
    private static final String NAME_PARAMETER = "name";
    private static final String SURNAME_PARAMETER = "surname";
    private static final String LOGIN_PARAMETER = "login";
    private static final String EMAIL_PARAMETER = "email";
    private static final String DATE_PARAMETER = "birthday";
    private static final String GO_TO_USER_INFO_PAGE_COMMAND = "go_to_user_info_page";
    private static final String GO_TO_EDIT_PROFILE_PAGE_COMMAND = "go_to_edit_profile_page";
    private static final CommandProvider commandProvider =  CommandProvider.getInstance();
    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = ServiceProvider.getUserService();
        User user = (User) request.getSession(true).getAttribute(ATTRIBUTE_USER);
        User newUser = new User(user.getId(),
                request.getParameter(LOGIN_PARAMETER),
                user.getPassword(),
                request.getParameter(NAME_PARAMETER),
                request.getParameter(SURNAME_PARAMETER),
                request.getParameter(EMAIL_PARAMETER),
                Date.valueOf(request.getParameter(DATE_PARAMETER)),
                user.getRole());
        try {
            userService.changeUser(newUser);
            request.getSession(true).setAttribute(ATTRIBUTE_USER, newUser);
            request.setAttribute(ATTRIBUTE_MESSAGE_SUCCESS,MESSAGE_SUCCESS);
            commandProvider.getCommand(GO_TO_USER_INFO_PAGE_COMMAND).execute(request, response);
        } catch (DuplicateLoginException e) {
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR, MESSAGE_DUPLICATE_LOGIN_EXCEPTION);
            commandProvider.getCommand(GO_TO_EDIT_PROFILE_PAGE_COMMAND).execute(request, response);
        } catch (ServiceException e) {
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR, MESSAGE_ERROR);
            commandProvider.getCommand(GO_TO_EDIT_PROFILE_PAGE_COMMAND).execute(request, response);
        }

    }
}
