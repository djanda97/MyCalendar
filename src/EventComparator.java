import java.util.Comparator;

/**
 * this class is to inherit the comparator interface so that we can sort objects in the MyCalendarTester class
 */
public class EventComparator implements Comparator<Event>
{

    /**
     * compares objects according to date and start date
     */
    public int compare(Event o1, Event o2)
    {
        if (o1.getYear() > o2.getYear())
        {
            return 1;
        }
        if (o1.getYear() < o2.getYear())
        {
            return -1;
        }
        if (o1.getStartMonth() > o2.getStartMonth())
        {
            return 1;
        }
        if (o1.getStartMonth() < o2.getStartMonth())
        {
            return -1;
        }
        if (o1.getDay() > o2.getDay())
        {
            return 1;
        }
        if (o1.getDay() < o2.getDay())
        {
            return -1;
        }
        if (o1.getStartHour() > o2.getStartHour())
        {
            return 1;
        }
        if (o1.getStartHour() < o2.getStartHour())
        {
            return -1;
        }
        return 0;
    }
}
