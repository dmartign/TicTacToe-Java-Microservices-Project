package hw3q1;

public class TypeConverter {

    @SuppressWarnings("unchecked")
    public static <T> T convert(Object o) {
        return (T) o;
    }

}
