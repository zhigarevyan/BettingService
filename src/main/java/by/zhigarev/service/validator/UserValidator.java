package by.zhigarev.service.validator;

import by.zhigarev.bean.SignInInfo;
import by.zhigarev.bean.SignUpInfo;

public class UserValidator {

    public static boolean isValidUserSignIn(SignInInfo signInInfo) {
        return isValid(signInInfo.getLogin()) && isValid(signInInfo.getPassword());
    }

    public static boolean isValidUserSignUp(SignUpInfo signUpInfo) {
        return isValid(signUpInfo.getName()) &&
                isValid(signUpInfo.getSurName()) &&
                isValid(signUpInfo.getLogin()) &&
                isValid(signUpInfo.getPassword()) &&
                isValid(signUpInfo.getEmail()) &&
                isValid(signUpInfo.getBirthdayDate().toString());
    }

    private static boolean isValid(String field){
        return field != null && !field.equals("");

    }
}
