import java.util.Date;

public class Date_Test {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getTime());
        String s = date.toString();
        System.out.println(s);
    }
}
