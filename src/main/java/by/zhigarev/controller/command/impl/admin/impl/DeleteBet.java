package by.zhigarev.controller.command.impl.admin.impl;

import by.zhigarev.bean.Account;
import by.zhigarev.bean.UserBet;
import by.zhigarev.controller.command.CommandProvider;
import by.zhigarev.controller.command.impl.admin.AdminCommand;
import by.zhigarev.service.AccountService;
import by.zhigarev.service.BetService;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.UserBetService;
import by.zhigarev.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBet extends AdminCommand {
    private static final String PARAMETER_BET_ID = "betId";
    private static final BetService betService = ServiceProvider.getBetService();
    private static final CommandProvider commandProvider = CommandProvider.getInstance();
    private static final String ATTRIBUTE_MESSAGE_SUCCESS = "message_success";
    private static final String ATTRIBUTE_MESSAGE_ERROR = "message_error";
    private static final String MESSAGE_SUCCESS = "success";
    private static final String MESSAGE_ERROR = "error";
    private static final String GO_TO_EVENT_INFO_PAGE_ADMIN_COMMAND = "go_to_event_info_page_admin";
    private static final UserBetService userBetService = ServiceProvider.getUserBetService();
    private static final AccountService accountService = ServiceProvider.getAccountService();


    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int betId = Integer.parseInt(request.getParameter(PARAMETER_BET_ID));
        int STATUS_RETURN_ID = 4;
        try{
            betService.updateBetStatusById(betId,STATUS_RETURN_ID);
            for(UserBet userBet : userBetService.getAllBetsByBetId(betId)){
                accountService.payMoneyByAccountId(userBet.getAccount().getId(), userBet.getBetAmount());
            }
            request.setAttribute(ATTRIBUTE_MESSAGE_SUCCESS,MESSAGE_SUCCESS);
            commandProvider.getCommand(GO_TO_EVENT_INFO_PAGE_ADMIN_COMMAND).execute(request,response);
        } catch (ServiceException e) {
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR,MESSAGE_ERROR);
            commandProvider.getCommand(GO_TO_EVENT_INFO_PAGE_ADMIN_COMMAND).execute(request,response);
        }
    }
}
