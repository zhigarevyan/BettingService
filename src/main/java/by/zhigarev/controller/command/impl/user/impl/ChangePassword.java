package by.zhigarev.controller.command.impl.user.impl;

import by.zhigarev.bean.User;
import by.zhigarev.controller.command.CommandProvider;
import by.zhigarev.controller.command.impl.user.UserCommand;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.UserService;
import by.zhigarev.service.exception.ServiceException;
import by.zhigarev.service.exception.WrongPasswordException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePassword extends UserCommand {
    private static final Logger logger = Logger.getLogger(ChangePassword.class);
    private static final String MESSAGE_SERVICE_EXCEPTION = "service exception";
    private static final String GO_TO_USER_INFO_PAGE_COMMAND = "go_to_user_info_page";
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_MESSAGE_SUCCESS = "message_success";
    private static final String ATTRIBUTE_MESSAGE_ERROR = "message_success";
    private static final String MESSAGE_SUCCESS = "Success";
    private static final String MESSAGE_ERROR = "Error";
    private static final String PARAMETER_PASSWORD = "password";
    private static final UserService userService = ServiceProvider.getUserService();
    private static final CommandProvider commandProvider = CommandProvider.getInstance();
    private static final String MESSAGE_WRONG_PASSWORD = "not valid password";


    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession(true).getAttribute(ATTRIBUTE_USER);
        try {
            userService.changePasswordById(user.getId(), request.getParameter(PARAMETER_PASSWORD));
            request.setAttribute(ATTRIBUTE_MESSAGE_SUCCESS, MESSAGE_SUCCESS);
            commandProvider.getCommand(GO_TO_USER_INFO_PAGE_COMMAND).execute(request, response);
        } catch (WrongPasswordException e) {
            logger.error(MESSAGE_WRONG_PASSWORD);
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR, MESSAGE_WRONG_PASSWORD);
            commandProvider.getCommand(GO_TO_USER_INFO_PAGE_COMMAND).execute(request, response);
        } catch (ServiceException e) {
            logger.error(MESSAGE_SERVICE_EXCEPTION);
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR, MESSAGE_ERROR);
            commandProvider.getCommand(GO_TO_USER_INFO_PAGE_COMMAND).execute(request, response);
        }


    }
}
