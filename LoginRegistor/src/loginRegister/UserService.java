package loginRegister;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static loginRegister.FileServiceRegister.MD5;

public class UserService {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean passwordValidation(String passStr) {
        String pattern = "(?=.*[0-9]){3,}(?=.*[a-z])(?=.*[A-Z]){2,}(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        return passStr.matches(pattern);
    }

    public static boolean validateUsername(String username) {

        return (username.length() > 10 && isUserNameFree(username));
    }

    public static boolean validateFullName(String fullName) {
        return (fullName.matches("[A-Z][a-z]+ [A-Z][a-z]+"));
    }

    public static boolean isUserNameFree(String userName) {
        try {
            List<String> reads = FileServiceRegister.read("files\\register.txt");
            for (String x : reads) {
                String[] split = x.split(",");

                if ( split[1].equals(userName)) {
                    System.out.println("username already exists");
                    return false;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean login(String userName, String password) {
        try {
            List<String> reads = FileServiceRegister.read("files\\register.txt");
            for (String x : reads) {
                String[] split = x.split(",");
                if (split[1].equals(userName) && split[3].equals(MD5(password))) {
                    return true;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return false;


    }
}
