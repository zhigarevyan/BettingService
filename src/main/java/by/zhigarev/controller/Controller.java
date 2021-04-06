package by.zhigarev.controller;

import by.zhigarev.controller.command.Command;
import by.zhigarev.controller.command.CommandProvider;
import by.zhigarev.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final CommandProvider provider = new CommandProvider();


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String name;
        Command command;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        name = request.getParameter("command");
        command = provider.getCommand(name);
        command.execute(request, response);

    }

}
