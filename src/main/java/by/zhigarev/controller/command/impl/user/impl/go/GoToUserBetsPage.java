package by.zhigarev.controller.command.impl.user.impl.go;

import by.zhigarev.bean.Account;
import by.zhigarev.bean.UserBet;
import by.zhigarev.controller.command.impl.user.UserCommand;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class GoToUserBetsPage extends UserCommand {
    private static final String USER_BETS_FRAGMENT_PATH = "user/userBets.jsp";
    private static final String USER_PAGE_PATH = "WEB-INF/jsp/user.jsp";
    private static final String ATTRIBUTE_USER_CONTENT = "user_content";
    private static final String ATTRIBUTE_USER_BETS = "userBets";
    private static final String ATTRIBUTE_ACCOUNT = "account";
    private static final String ATTRIBUTE_MESSAGE_ERROR = "message_error";
    private static final String MESSAGE_ERROR = "error";

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute(ATTRIBUTE_ACCOUNT);
        try {
            List<UserBet> userBets = ServiceProvider.getUserBetService().getAllAccountBets(account);
            request.setAttribute(ATTRIBUTE_USER_CONTENT, USER_BETS_FRAGMENT_PATH);
            request.setAttribute(ATTRIBUTE_USER_BETS, userBets);
            request.getRequestDispatcher(USER_PAGE_PATH).forward(request,response);
        } catch (ServiceException e) {
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR,MESSAGE_ERROR);
            request.getRequestDispatcher(USER_PAGE_PATH).forward(request,response);
        }

    }
}
