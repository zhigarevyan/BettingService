package by.zhigarev.controller.command.impl.admin.impl;

import by.zhigarev.bean.info.ParticipantInfo;
import by.zhigarev.controller.command.CommandProvider;
import by.zhigarev.controller.command.impl.admin.AdminCommand;
import by.zhigarev.service.ParticipantService;
import by.zhigarev.service.ServiceProvider;
import by.zhigarev.service.exception.DuplicateParticipantException;
import by.zhigarev.service.exception.ServiceException;
import by.zhigarev.util.SaveImage;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

public class SaveParticipantChanges extends AdminCommand {
    private static final Logger logger = Logger.getLogger(SaveParticipantChanges.class);
    private static final String MESSAGE_SERVICE_EXCEPTION = "service exception";
    private static final String ATTRIBUTE_MESSAGE_SUCCESS = "message_success";
    private static final String ATTRIBUTE_MESSAGE_ERROR = "message_error";
    private static final String MESSAGE_ERROR = "Error";
    private static final String MESSAGE_SUCCESS = "Success";
    private static final String MESSAGE_DUPLICATE_PARTICIPANT_EXCEPTION = "duplicate participant";
    private static final String MESSAGE_ADD_IMAGE_EXCEPTION = "add image error";
    private static final String ATTRIBUTE_PARTICIPANT_ID = "participantId";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_SURNAME = "surname";
    private static final String ATTRIBUTE_INFO = "info";
    private static final String ATTRIBUTE_FILE = "file";
    private static final String GO_TO_EDIT_PARTICIPANT_PAGE_COMMAND = "go_to_edit_participant_page";
    private static final String GO_TO_PARTICIPANTS_PAGE_COMMAND = "go_to_participants_page";
    private static final CommandProvider commandProvider = CommandProvider.getInstance();
    ParticipantService participantService = ServiceProvider.getParticipantService();
    private static final SaveImage saveImage = new SaveImage();

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int participantId = Integer.parseInt(request.getParameter(ATTRIBUTE_PARTICIPANT_ID));
        String name = request.getParameter(ATTRIBUTE_NAME);
        String surname = request.getParameter(ATTRIBUTE_SURNAME);
        String info = request.getParameter(ATTRIBUTE_INFO);

        try {
            Part inputFile = request.getPart(ATTRIBUTE_FILE);
            String imagePath = saveImage.addImage(inputFile, name, surname);
            participantService.updateParticipantById(new ParticipantInfo(name, surname, info, imagePath), participantId);
            request.setAttribute(ATTRIBUTE_MESSAGE_SUCCESS, MESSAGE_SUCCESS);
            commandProvider.getCommand(GO_TO_PARTICIPANTS_PAGE_COMMAND).execute(request, response);
        } catch (IOException e) {
            logger.error(MESSAGE_ADD_IMAGE_EXCEPTION);
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR, MESSAGE_ADD_IMAGE_EXCEPTION);
            commandProvider.getCommand(GO_TO_EDIT_PARTICIPANT_PAGE_COMMAND).execute(request, response);
        } catch (DuplicateParticipantException e) {
            logger.error(MESSAGE_DUPLICATE_PARTICIPANT_EXCEPTION);
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR, MESSAGE_DUPLICATE_PARTICIPANT_EXCEPTION);
            commandProvider.getCommand(GO_TO_EDIT_PARTICIPANT_PAGE_COMMAND).execute(request, response);
        } catch (ServiceException e) {
            logger.error(MESSAGE_SERVICE_EXCEPTION);
            request.setAttribute(ATTRIBUTE_MESSAGE_ERROR, MESSAGE_ERROR);
            commandProvider.getCommand(GO_TO_EDIT_PARTICIPANT_PAGE_COMMAND).execute(request, response);
        }

    }
}
