package by.zhigarev.controller.command.impl.admin.impl;

import by.zhigarev.bean.info.ParticipantInfo;
import by.zhigarev.controller.command.CommandProvider;
import by.zhigarev.controller.command.impl.admin.AdminCommand;
import by.zhigarev.service.ParticipantService;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.exception.DuplicateParticipantException;
import by.zhigarev.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddParticipant extends AdminCommand {
    private static final Logger logger = Logger.getLogger(AddParticipant.class);

    private static final String NAME_PARAMETER = "name";
    private static final String SURNAME_PARAMETER = "surname";
    private static final String INFO_PARAMETER = "info";
    private static final String ATTRIBUTE_MESSAGE_SUCCESS = "message_success";
    private static final String MESSAGE_SUCCESS = "success";
    private static final String ATTRIBUTE_MESSAGE_ERROR = "message_error";
    private static final String GO_TO_ADD_PARTICIPANT_PAGE_COMMAND = "go_to_add_participant_page";
    private static final String GO_TO_PARTICIPANTS_PAGE_COMMAND = "go_to_participants_page";
    private static final String MESSAGE_DUPLICATE_PARTICIPANT_EXCEPTION = "Duplicate participant exception";
    private static final String MESSAGE_VALIDATION_EXCEPTION = "Participant validation exception";
    private static final CommandProvider commandProvider = CommandProvider.getInstance();

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(NAME_PARAMETER);
        String surname = request.getParameter(SURNAME_PARAMETER);
        String info = request.getParameter(INFO_PARAMETER);
        String image = "";


        ParticipantService participantService = ServiceProvider.getParticipantService();

        ParticipantInfo participant = new ParticipantInfo(name, surname, info, image);
        try {
            participantService.addParticipant(participant);
            request.setAttribute(ATTRIBUTE_MESSAGE_SUCCESS, MESSAGE_SUCCESS);
            commandProvider.getCommand(GO_TO_PARTICIPANTS_PAGE_COMMAND).execute(request, response);
        } catch (DuplicateParticipantException e) {
            logger.error(MESSAGE_DUPLICATE_PARTICIPANT_EXCEPTION);
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR, MESSAGE_DUPLICATE_PARTICIPANT_EXCEPTION);
            commandProvider.getCommand(GO_TO_ADD_PARTICIPANT_PAGE_COMMAND).execute(request, response);
        } catch (ServiceException e) {
            logger.error(MESSAGE_VALIDATION_EXCEPTION);
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR, MESSAGE_VALIDATION_EXCEPTION);
            commandProvider.getCommand(GO_TO_ADD_PARTICIPANT_PAGE_COMMAND).execute(request, response);
        }

    }
}
