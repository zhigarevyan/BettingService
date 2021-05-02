package by.zhigarev.controller.command.impl.admin.impl;

import by.zhigarev.bean.Participant;
import by.zhigarev.bean.info.EventInfo;
import by.zhigarev.controller.command.CommandProvider;
import by.zhigarev.controller.command.impl.admin.AdminCommand;
import by.zhigarev.service.EventService;
import by.zhigarev.service.ParticipantService;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class AddEvent extends AdminCommand {
    private static final Logger logger = Logger.getLogger(AddEvent.class);
    private static final String MESSAGE_SERVICE_EXCEPTION = "service exception";

    private static final String FIRST_PARTICIPANT_PARAMETER = "first_participant";
    private static final String SECOND_PARTICIPANT_PARAMETER = "second_participant";
    private static final String INFO_PARAMETER = "info";
    private static final String LOCATION_PARAMETER = "location";
    private static final String START_DATE_PARAMETER = "start_date";
    private static final String ATTRIBUTE_MESSAGE_SUCCESS = "message_success";
    private static final String ATTRIBUTE_MESSAGE_ERROR = "message_error";
    private static final String MESSAGE_CHOSE_DIFFERENT_PARTICIPANTS = "Choose different participants";
    private static final String MESSAGE_SUCCESS = "Success";
    private static final String MESSAGE_ERROR = "Error";
    private static final String GO_TO_ADD_EVENT_PAGE_COMMAND = "go_to_add_event_page";
    private static final CommandProvider commandProvider = CommandProvider.getInstance();

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int firstParticipantId = Integer.parseInt(request.getParameter(FIRST_PARTICIPANT_PARAMETER));
        int secondParticipantId = Integer.parseInt(request.getParameter(SECOND_PARTICIPANT_PARAMETER));
        String info = request.getParameter(INFO_PARAMETER);
        String location = request.getParameter(LOCATION_PARAMETER);
        String startDateTime = request.getParameter(START_DATE_PARAMETER);
        Participant firstParticipant;
        Participant secondParticipant;


        ParticipantService participantService = ServiceProvider.getParticipantService();
        EventService eventService = ServiceProvider.getEventService();
        if (firstParticipantId == secondParticipantId) {
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR, MESSAGE_CHOSE_DIFFERENT_PARTICIPANTS);
        } else {
            try {
                firstParticipant = participantService.getParticipantById(firstParticipantId);
                secondParticipant = participantService.getParticipantById(secondParticipantId);
                EventInfo event = new EventInfo(firstParticipant.getId(), secondParticipant.getId(), location, info, Date.valueOf(startDateTime));
                eventService.createEvent(event);

            } catch (ServiceException e) {
                logger.error(MESSAGE_SERVICE_EXCEPTION);
                request.setAttribute(ATTRIBUTE_MESSAGE_ERROR, MESSAGE_ERROR);
                commandProvider.getCommand(GO_TO_ADD_EVENT_PAGE_COMMAND).execute(request, response);
            }
            request.setAttribute(ATTRIBUTE_MESSAGE_SUCCESS, MESSAGE_SUCCESS);
        }
        commandProvider.getCommand(GO_TO_ADD_EVENT_PAGE_COMMAND).execute(request, response);
    }
}
