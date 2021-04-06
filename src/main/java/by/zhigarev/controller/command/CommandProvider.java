package by.zhigarev.controller.command;

import by.zhigarev.controller.command.impl.*;
import by.zhigarev.controller.command.impl.admin.impl.*;
import by.zhigarev.controller.command.impl.admin.impl.go.*;
import by.zhigarev.controller.command.impl.go.*;
import by.zhigarev.controller.command.impl.user.impl.AddUserBet;
import by.zhigarev.controller.command.impl.user.impl.ChangePassword;
import by.zhigarev.controller.command.impl.user.impl.SaveProfileChanges;
import by.zhigarev.controller.command.impl.user.impl.go.*;
import by.zhigarev.controller.command.impl.admin.impl.go.GoToEventInfoPageAdmin;
import by.zhigarev.controller.command.impl.admin.impl.go.GoToFutureEventsPageAdmin;

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
        commands.put(CommandName.GO_TO_USER_PAGE, new GoToUserPage());
        commands.put(CommandName.GO_TO_ADMIN_PAGE, new GoToAdminPage());
        commands.put(CommandName.GO_TO_ADD_PARTICIPANT_PAGE, new GoToAddParticipantPage());
        commands.put(CommandName.ADD_PARTICIPANT, new AddParticipant());
        commands.put(CommandName.GO_TO_ADD_EVENT_PAGE, new GoToAddEventPage());
        commands.put(CommandName.ADD_EVENT, new AddEvent());
        commands.put(CommandName.ADD_BET, new AddBet());
        commands.put(CommandName.GO_TO_FUTURE_EVENTS_PAGE_ADMIN, new GoToFutureEventsPageAdmin());
        commands.put(CommandName.GO_TO_FUTURE_EVENTS_PAGE_USER, new GoToFutureEventsPageUser());
        commands.put(CommandName.GO_TO_EVENT_INFO_PAGE_USER, new GoToEventInfoPageUser());
        commands.put(CommandName.GO_TO_EVENT_INFO_PAGE_ADMIN, new GoToEventInfoPageAdmin());
        commands.put(CommandName.GO_TO_MAKE_USER_BET_PAGE, new GoToMakeUserBetPage());
        commands.put(CommandName.ADD_OUTCOME_TYPE, new AddOutcomeType());
        commands.put(CommandName.ADD_USER_BET, new AddUserBet());
        commands.put(CommandName.GO_TO_USER_BETS_PAGE, new GoToUserBetsPage());
        commands.put(CommandName.GO_TO_ADD_RESULT_PAGE, new GoToAddResultPage());
        commands.put(CommandName.ADD_RESULT, new AddResult());
        commands.put(CommandName.GO_TO_EVENT_RESULTS_PAGE, new GoToEventResultsPage());
        commands.put(CommandName.GO_TO_USER_INFO_PAGE, new GoToUserInfoPage());
        commands.put(CommandName.GO_TO_EDIT_PROFILE_PAGE, new GoToEditProfilePage());
        commands.put(CommandName.GO_TO_PARTICIPANTS_PAGE, new GoToParticipantsPage());
        commands.put(CommandName.GO_TO_EDIT_PARTICIPANT_PAGE, new GoToEditParticipantPage());
        commands.put(CommandName.SAVE_PROFILE_CHANGES, new SaveProfileChanges());
        commands.put(CommandName.CHANGE_PASSWORD, new ChangePassword());
        commands.put(CommandName.DELETE_EVENT, new DeleteEvent());
        commands.put(CommandName.DELETE_BET, new DeleteBet());
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
