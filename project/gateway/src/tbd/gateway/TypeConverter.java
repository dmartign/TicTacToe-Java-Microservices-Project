package tbd.gateway;

public class TypeConverter {

    @SuppressWarnings("unchecked")
    public static <T> T convert(Object o) {
        return (T) o;
    }

}
