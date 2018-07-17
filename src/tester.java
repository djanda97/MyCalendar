import java.util.Calendar;
import java.util.GregorianCalendar;

public class tester
{
    public static void main(String[] args)
    {
        String date = "09:00";
        int day = Integer.parseInt(date.substring(0, 2));
        System.out.println(day);
        String month = date.substring(3, 5);
        System.out.println(month);
//        String year = date.substring(6);
//        System.out.println(year);
        GregorianCalendar calendar = new GregorianCalendar();
        System.out.println(Calendar.DAY_OF_WEEK);


    }
}
