package by.zhigarev.controller.command;

import by.zhigarev.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static CommandProvider instance = new CommandProvider();
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.SIGN_UP, new SignUp());
        commands.put(CommandName.GO_TO_SIGN_UP_PAGE, new GoToSignUpPage());
        commands.put(CommandName.SIGN_IN, new SignIn());
        commands.put(CommandName.GO_TO_WELCOME_PAGE, new GoToWelcomePage());
        commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
        commands.put(CommandName.LOGOUT, new Logout());
        commands.put(CommandName.GO_TO_SIGN_IN_PAGE, new GoToSignInPage());
    }


    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    public Command getCommand(String commandName) {
        commandName = commandName.toUpperCase();
        CommandName enumName = CommandName.valueOf(commandName);

        return commands.get(enumName);
    }

}
