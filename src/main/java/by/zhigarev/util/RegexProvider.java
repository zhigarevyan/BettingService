package by.zhigarev.util;

import java.util.ResourceBundle;

public class RegexProvider {
    private static final String REGEX_BUNDLE_PATH = "regex";
    private static final ResourceBundle regexBundle = ResourceBundle.getBundle(REGEX_BUNDLE_PATH);

    public static String getRegex(String propertyName){
        return regexBundle.getString(propertyName);
    }
}
