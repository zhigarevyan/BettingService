package by.zhigarev.controller.command.impl.admin.impl;

import by.zhigarev.bean.Bet;
import by.zhigarev.bean.UserBet;
import by.zhigarev.bean.info.EventResultInfo;
import by.zhigarev.controller.command.CommandProvider;
import by.zhigarev.controller.command.impl.admin.AdminCommand;
import by.zhigarev.service.*;
import by.zhigarev.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddResult extends AdminCommand {
    private static final String MESSAGE_SUCCESS = "success";
    private static final String MESSAGE_ERROR = "error";
    private static final String ATTRIBUTE_MESSAGE_SUCCESS = "message_success";
    private static final String ATTRIBUTE_MESSAGE_ERROR = "message_error";
    private static final String EVENT_ID_PARAMETER = "eventId";
    private static final String WINNER_ID_PARAMETER = "winnerId";
    private static final String FIRST_PARTICIPANT_SCORE_PARAMETER = "firstParticipantScore";
    private static final String SECOND_PARTICIPANT_SCORE_PARAMETER = "secondParticipantScore";
    private static final String GO_TO_EVENT_INFO_PAGE_ADMIN_COMMAND = "go_to_event_info_page_admin";
    private static final String GO_TO_ADD_RESULT_PAGE_COMMAND = "go_to_add_result_page";
    private static final EventResultService eventResultService = ServiceProvider.getEventResultService();
    private static final EventService eventService = ServiceProvider.getEventService();
    private static final BetService betService = ServiceProvider.getBetService();
    private static final UserBetService userBetService = ServiceProvider.getUserBetService();
    private static final AccountService accountService = ServiceProvider.getAccountService();
    private static final CommandProvider commandProvider = CommandProvider.getInstance();

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int STATUS_PAID = 2;
        int STATUS_RETURN = 4;

        int eventId = Integer.parseInt(request.getParameter(EVENT_ID_PARAMETER));
        int winnerId = Integer.parseInt(request.getParameter(WINNER_ID_PARAMETER));
        int winnerScore = Integer.parseInt(request.getParameter(FIRST_PARTICIPANT_SCORE_PARAMETER));
        int loserScore = Integer.parseInt(request.getParameter(SECOND_PARTICIPANT_SCORE_PARAMETER));

        EventResultInfo eventResultInfo = new EventResultInfo(0, eventId, winnerId, winnerScore, loserScore);
        try {
            eventResultService.createResultForEvent(eventResultInfo);
            eventService.changeEventStatusToPastById(eventId);
            List<Bet> bets = betService.getAllBetsOfEvent(eventService.getEventById(eventId));
            for (Bet bet : bets) {
                int newStatus = Integer.parseInt(request.getParameter(String.valueOf(bet.getId())));
                betService.updateBetStatusById(bet.getId(),newStatus);
                List<UserBet> userBets = userBetService.getAllBetsByBetId(bet.getId());
                for (UserBet userBet : userBets) {
                    if (newStatus == STATUS_PAID) {
                        accountService.payMoneyByAccountId(userBet.getAccount().getId(),bet.getOffer()*userBet.getBetAmount());
                    }
                    if(newStatus == STATUS_RETURN){
                        accountService.payMoneyByAccountId(userBet.getAccount().getId(),userBet.getBetAmount());
                    }
                }
            }
            request.setAttribute(ATTRIBUTE_MESSAGE_SUCCESS,MESSAGE_SUCCESS);
            commandProvider.getCommand(GO_TO_EVENT_INFO_PAGE_ADMIN_COMMAND).execute(request, response);

        } catch (ServiceException e) {
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR,MESSAGE_ERROR);
            commandProvider.getCommand(GO_TO_ADD_RESULT_PAGE_COMMAND).execute(request, response);
        }
    }
}
