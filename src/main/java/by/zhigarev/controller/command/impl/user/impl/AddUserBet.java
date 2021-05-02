package by.zhigarev.controller.command.impl.user.impl;

import by.zhigarev.bean.Account;
import by.zhigarev.bean.User;
import by.zhigarev.bean.info.UserBetInfo;
import by.zhigarev.controller.command.CommandProvider;
import by.zhigarev.controller.command.impl.user.UserCommand;
import by.zhigarev.service.AccountService;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.UserBetService;
import by.zhigarev.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class AddUserBet extends UserCommand {
    private static final Logger logger = Logger.getLogger(AddUserBet.class);
    private static final String MESSAGE_SERVICE_EXCEPTION = "service exception";
    private static final String PARAMETER_BET_ID = "betId";
    private static final String PARAMETER_AMOUNT = "betAmount";
    private static final String ATTRIBUTE_ACCOUNT = "account";
    private static final String ATTRIBUTE_MESSAGE_ERROR = "message_error";
    private static final String ATTRIBUTE_MESSAGE_SUCCESS = "message_success";
    private static final String ACCOUNT_ATTRIBUTE = "account";
    private static final String ATTRIBUTE_USER = "user";
    private static final String MESSAGE_NOT_ENOUGH_MONEY = "not enough money";
    private static final String MESSAGE_SUCCESS = "success";
    private static final String MESSAGE_ERROR = "Error";
    private static final String COMMAND_GO_TO_MAKE_USER_BET_PAGE = "go_to_make_user_bet_page";
    private static final String COMMAND_GO_TO_USER_PAGE = "go_to_user_page";
    private static final UserBetService userBetService = ServiceProvider.getUserBetService();
    private static final AccountService accountService = ServiceProvider.getAccountService();
    private static final CommandProvider commandProvider = CommandProvider.getInstance();

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int betId = Integer.parseInt(request.getParameter(PARAMETER_BET_ID));
        double betAmount = Double.parseDouble(request.getParameter(PARAMETER_AMOUNT));
        Account account = (Account) request.getSession().getAttribute(ATTRIBUTE_ACCOUNT);
        double accountBalance = account.getBalance();
        if (accountBalance > betAmount) {
            try {
                userBetService.addUserBet(new UserBetInfo(0, account.getId(), betId, new Date(), betAmount));
                accountService.writeOffMoneyByAccountId(account.getId(), betAmount);
                account = accountService.getAccountByUser((User) request.getSession().getAttribute(ATTRIBUTE_USER));
                request.getSession().setAttribute(ACCOUNT_ATTRIBUTE, account);
                request.setAttribute(ATTRIBUTE_MESSAGE_SUCCESS, MESSAGE_SUCCESS);
                commandProvider.getCommand(COMMAND_GO_TO_USER_PAGE).execute(request, response);
            } catch (ServiceException e) {
                logger.error(MESSAGE_SERVICE_EXCEPTION);
                request.setAttribute(ATTRIBUTE_MESSAGE_ERROR, MESSAGE_ERROR);
                commandProvider.getCommand(COMMAND_GO_TO_MAKE_USER_BET_PAGE).execute(request, response);
            }
        } else {
            logger.error(MESSAGE_NOT_ENOUGH_MONEY);
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR, MESSAGE_NOT_ENOUGH_MONEY);
            commandProvider.getCommand(COMMAND_GO_TO_MAKE_USER_BET_PAGE).execute(request, response);
        }

    }
}
