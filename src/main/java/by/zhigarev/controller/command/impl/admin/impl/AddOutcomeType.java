package by.zhigarev.controller.command.impl.admin.impl;

import by.zhigarev.controller.command.CommandProvider;
import by.zhigarev.controller.command.impl.admin.AdminCommand;
import by.zhigarev.service.OutcomeTypeService;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.exception.DuplicateOutcomeTypeException;
import by.zhigarev.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddOutcomeType extends AdminCommand {
    private static final String OUTCOME_TYPE_PARAMETER = "outcomeType";
    private static final String ATTRIBUTE_MESSAGE_ERROR ="message_error";
    private static final String ATTRIBUTE_MESSAGE_SUCCESS ="message_success";
    private static final String MESSAGE_ERROR = "Error";
    private static final String MESSAGE_DUPLICATE_OUTCOME_TYPE = "Duplicate outcome type";
    private static final String MESSAGE_SUCCESS = "Success";
    private static final String GO_TO_EVENT_INFO_PAGE_ADMIN_COMMAND = "go_to_event_info_page_admin";
    private static final CommandProvider commandProvider = CommandProvider.getInstance();

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String outcomeType = request.getParameter(OUTCOME_TYPE_PARAMETER);
        OutcomeTypeService outcomeTypeService = ServiceProvider.getOutcomeTypeService();
        try{
            outcomeTypeService.addType(outcomeType);
            request.setAttribute(ATTRIBUTE_MESSAGE_SUCCESS,MESSAGE_SUCCESS);
            commandProvider.getCommand(GO_TO_EVENT_INFO_PAGE_ADMIN_COMMAND).execute(request,response);
        }catch (DuplicateOutcomeTypeException e){
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR,MESSAGE_DUPLICATE_OUTCOME_TYPE);
            commandProvider.getCommand(GO_TO_EVENT_INFO_PAGE_ADMIN_COMMAND).execute(request,response);
        }
        catch (ServiceException e) {
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR,MESSAGE_ERROR);
            commandProvider.getCommand(GO_TO_EVENT_INFO_PAGE_ADMIN_COMMAND).execute(request,response);
        }
    }
}
