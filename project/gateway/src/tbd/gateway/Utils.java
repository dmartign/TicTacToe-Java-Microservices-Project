package tbd.gateway;

public class Utils {

    public static boolean nullOrBlank(String parameter) {
        return parameter == null || "".equals(parameter);
    }
}
