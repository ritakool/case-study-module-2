package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataEntryKeyboard {
    private static Pattern pattern;
    private Matcher matcher;
    public static final String PHONE_REGEX = "^\\(\\+84\\)-\\(\\d{9}\\)$";
    public static final String NAME_REGEX = "^[a-zA-Z\\p{L}' ]{2,}$";
    public static final String ID_REGEX = "^#\\d{3}$";
    public boolean checkPhoneNumber(String phoneNumber) {
        pattern = Pattern.compile(PHONE_REGEX);
        matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
    public boolean checkName(String name) {
        pattern = Pattern.compile(NAME_REGEX);
        matcher = pattern.matcher(name);
        return matcher.matches();
    }
    public boolean checkID(String id) {
        pattern = Pattern.compile(ID_REGEX);
        matcher = pattern.matcher(id);
        return matcher.matches();
    }
}
