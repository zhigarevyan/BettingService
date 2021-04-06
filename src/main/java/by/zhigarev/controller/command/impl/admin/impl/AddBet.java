package by.zhigarev.controller.command.impl.admin.impl;

import by.zhigarev.bean.info.BetInfo;
import by.zhigarev.controller.command.CommandProvider;
import by.zhigarev.controller.command.impl.admin.AdminCommand;
import by.zhigarev.service.BetService;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.exception.DuplicateBetException;
import by.zhigarev.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddBet extends AdminCommand {
    private static final String OUTCOME_TYPE_PARAMETER = "outcomeType";
    private static final String EVENT_ID_PARAMETER = "eventId";
    private static final String OFFER_PARAMETER = "offer";
    private static final String ATTRIBUTE_MESSAGE_SUCCESS = "message_success";
    private static final String ATTRIBUTE_MESSAGE_ERROR = "message_error";
    private static final String MESSAGE_SUCCESS = "success";
    private static final String MESSAGE_ERROR = "error";
    private static final String MESSAGE_DUPLICATE_BET = "duplicate bet";
    private static final String GO_TO_EVENT_INFO_PAGE_ADMIN_COMMAND = "go_to_event_info_page_admin";
    private static final CommandProvider commandProvider = CommandProvider.getInstance();
    private static final BetService betService = ServiceProvider.getBetService();

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int eventId = Integer.parseInt(request.getParameter(EVENT_ID_PARAMETER));
        int outcomeTypeId = Integer.parseInt(request.getParameter(OUTCOME_TYPE_PARAMETER));
        double offer = Double.parseDouble(request.getParameter(OFFER_PARAMETER));
        try{
            betService.createBetForEvent(
                    new BetInfo(0,eventId,outcomeTypeId,0,offer)
            );
            request.setAttribute(ATTRIBUTE_MESSAGE_SUCCESS,MESSAGE_SUCCESS);
            commandProvider.getCommand(GO_TO_EVENT_INFO_PAGE_ADMIN_COMMAND).execute(request,response);

        }catch (DuplicateBetException e){
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR,MESSAGE_DUPLICATE_BET);
            commandProvider.getCommand(GO_TO_EVENT_INFO_PAGE_ADMIN_COMMAND).execute(request,response);
        }
        catch (ServiceException e) {
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR,MESSAGE_ERROR);
            commandProvider.getCommand(GO_TO_EVENT_INFO_PAGE_ADMIN_COMMAND).execute(request,response);
        }
    }
}
