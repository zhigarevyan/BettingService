package by.zhigarev.controller.command.impl;

import by.zhigarev.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class ChangeLocale implements Command {
    private static final String PARAMETER_LOCALE = "locale";
    private static final String ATTRIBUTE_PREVIOUS_REQUEST = "prev_request";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession SESSION = request.getSession();
        final Locale LOCALE = Locale.forLanguageTag(request.getParameter(PARAMETER_LOCALE));
        final String PREVIOUS_REQUEST = (String) SESSION.getAttribute(ATTRIBUTE_PREVIOUS_REQUEST);

        SESSION.setAttribute(PARAMETER_LOCALE, LOCALE);
        response.sendRedirect(PREVIOUS_REQUEST);

    }
}
