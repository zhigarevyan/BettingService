package by.zhigarev.service.validator;


import by.zhigarev.bean.info.*;
import by.zhigarev.util.DateFormatter;
import by.zhigarev.util.RegexProvider;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String REGEX_FIO_PROPERTY = "regex.fio";
    private static final String REGEX_LOGIN_PROPERTY = "regex.login";
    private static final String REGEX_PASSWORD_PROPERTY = "regex.password";
    private static final String REGEX_EMAIL_PROPERTY = "regex.email";
    private static final String REGEX_REQUIRED_FIELD_PROPERTY = "regex.requiredField";
    private static final String REGEX_OFFER_FIELD_PROPERTY = "regex.offer";

    public static boolean isValidUserSignIn(SignInInfo signInInfo) {
        return isValidLogin(signInInfo.getLogin()) && isValidPassword(signInInfo.getPassword());
    }

    public static boolean isValidUserSignUp(SignUpInfo signUpInfo) {
        return isValidFio(signUpInfo.getName()) &&
                isValidFio(signUpInfo.getSurName()) &&
                isValidPassword(signUpInfo.getPassword()) &&
                isValidLogin(signUpInfo.getLogin()) &&
                isValidEmail(signUpInfo.getEmail()) &&
                isValidDate(signUpInfo.getBirthdayDate());
    }

    public static boolean isValidParticipant(ParticipantInfo participant){
        return isValidFio(participant.getName()) &&
                isValidFio(participant.getSurName())&&
                isValidRequiredField(participant.getInfo());
    }

    public static boolean isValidEvent(EventInfo eventInfo){
        return isValidRequiredField(eventInfo.getInfo()) &&
                isValidRequiredField(eventInfo.getLocation());
    }

    private static boolean isValidFio(String field){
        return  isNotNull(field) && isMatchesPattern(field,RegexProvider.getRegex(REGEX_FIO_PROPERTY));
    }
    private static boolean isValidLogin(String field){
        return  isNotNull(field) && isMatchesPattern(field,RegexProvider.getRegex(REGEX_LOGIN_PROPERTY));
    }
    private static boolean isValidPassword(String field){
        return  isNotNull(field) && isMatchesPattern(field,RegexProvider.getRegex(REGEX_PASSWORD_PROPERTY));
    }
    private static boolean isValidEmail(String field){
        return  isNotNull(field) && isMatchesPattern(field,RegexProvider.getRegex(REGEX_EMAIL_PROPERTY));
    }
    private static boolean isValidDate(java.util.Date date){
        Date currentDate = Date.valueOf(DateFormatter.format(new java.util.Date()));
        return isNotNull(date.toString()) &&  (date.before(currentDate) || date.equals(currentDate));
    }
    public static boolean isValidRequiredField(String field){
        return  isNotNull(field) && isMatchesPattern(field,RegexProvider.getRegex(REGEX_REQUIRED_FIELD_PROPERTY));
    }


    private static boolean isNotNull(String field){
        return field != null && !field.equals("");
    }



    private static boolean isMatchesPattern(String field, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(field);
        return matcher.find();
    }

}
