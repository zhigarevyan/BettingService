package by.zhigarev.controller;

import by.zhigarev.controller.command.Command;
import by.zhigarev.controller.command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@MultipartConfig
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final CommandProvider provider = new CommandProvider();
    private static final String ATTRIBUTE_COMMAND = "command";
    private static final String ATTRIBUTE_LOCALE = "locale";
    private static final String CONTROLLER_URL = "Controller?";
    private static final String ATTRIBUTE_PREV_REQUEST = "prev_request";


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name;
        Command command;
        HttpSession session = request.getSession();
        String queryString = request.getQueryString();
        String previousRequest = CONTROLLER_URL + queryString;

        name = request.getParameter(ATTRIBUTE_COMMAND);
        command = provider.getCommand(name);
        if (session.getAttribute(ATTRIBUTE_LOCALE) == null) {
            session.setAttribute(ATTRIBUTE_LOCALE, Locale.getDefault());
        }

        if (command == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            command.execute(request, response);
            if (queryString != null) {
                session.setAttribute(ATTRIBUTE_PREV_REQUEST, previousRequest);
            }
        }
    }
}
