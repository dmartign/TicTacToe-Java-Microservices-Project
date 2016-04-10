package cse551.tbd.authentication;

public class Utils {

    public static boolean nullOrBlank(String parameter) {
        return parameter == null || "".equals(parameter);
    }
}
