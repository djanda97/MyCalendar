import java.util.Comparator;

/**
 * this class is to inherit the comparator interface so that we can sort objects in the MyCalendarTester class
 * 
 */
public class EventComparator implements Comparator<Event>{
	@Override
	/**
	 * compares objects according to date and start date
	 */
	public int compare(Event o1, Event o2) 
	{
		return (int)(o1.getStartTime().compareTo(o2.getStartTime()));	
	}
}
