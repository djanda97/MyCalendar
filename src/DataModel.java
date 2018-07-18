import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.*;
import java.util.*;

public class DataModel
{
    private List<Event> eventList;// *****to store events******
    private BufferedReader br;
    private Map<Integer, String> dayMap;//******to map recurring events*******
    private ArrayList<ChangeListener> listeners;
    private GregorianCalendar cal;
    

    private int eventDay;
    private int eventMonth;
    private int eventYear;

    public int getToday()
    {
        return Calendar.getInstance().get(Calendar.DATE);
    }

    public void setEventDay(int eventDay){
        this.eventDay = eventDay;
    }

    public void setEventMonth(int eventMonth){
        this.eventMonth = eventMonth;
    }

    public void setEventYear(int eventYear){
        this.eventYear = eventYear;
    }

    public int getEventDay(){
        return this.eventDay;
    }

    public int getEventMonth(){
        return this.eventMonth;
    }

    public int getEventYear(){
        return this.eventYear;
    }

    public DataModel()
    {
        listeners = new ArrayList<>();
        cal = new GregorianCalendar();
        eventList = new ArrayList<>();
        // *************creating Map to act as a dictionary so that when we read the 
        // day from the file, we can map it back to cal.get(Calendar.DAY_OF_WEEK)
        // this is only for recurring events because we are reading from file for recurring events only. 
        // In recurring events each symbol is used to map the day to a event.***************
        dayMap = new HashMap<>();
        dayMap.put(1, "S");
        dayMap.put(2, "M");
        dayMap.put(3, "T");
        dayMap.put(4, "W");
        dayMap.put(5, "H");
        dayMap.put(6, "F");
        dayMap.put(7, "A");
    }

    public List<Event> getEventList()
    {
        return eventList;
    }

    public void setEventList(List<Event> eventList)
    {
        this.eventList = eventList;
    }
    
    // *********Overloaded function to create single event without endHour*********
    public boolean createEvent(String name, int year, int startMonth,
			int day, int startHour) {
		Event e = new Event(name, year, startMonth, day, startHour);
		if(checkConflict(e)) {
			eventList.add(e);
			update();
			return true;
		}
		return false;
	}

    //*********** Overloaded function to create single event with endHour**********
    public boolean createEvent(String name, int year, int startMonth,
                               int day, int startHour, int endHour)
    {
        Event e = new Event(name, year, startMonth, day, startHour, endHour);
        if (checkConflict(e))
        {
            eventList.add(e);
            update();
            return true;
        }
        return false;
    }

    //********** Overloaded function to create recurring event***************
    public boolean createEvent(String name, int year, int startMonth, int endMonth,
                               int day, int startHour, int endHour)
    {
        Event e = new Event(name, year, startMonth, endMonth, day, startHour, endHour);
        if (checkConflict(e))
        {
            eventList.add(e);
            update();
            return true;
        }
        return false;
    }

    private boolean checkConflict(Event e)
    {
        for (Event event : eventList)
        {
            if (event.getYear() == e.getYear()
                    && event.getStartMonth() == e.getStartMonth()
                    && event.getDay() == e.getDay()
                    && event.getStartHour() >= e.getStartHour()
                    && (event.getEndHour() < e.getStartHour() || event.getEndHour() != -1))
            {
                return false;
            }
        }
        return true;
    }

    public void sortEvent()
    {
	Comparator<Event> eventComparator = (e1, e2) ->
    {
        if (e1.getYear() > e2.getYear())
        {
            return 1;
         }
         if (e1.getYear() < e2.getYear())
         {
             return -1;
         }
         if (e1.getStartMonth() > e2.getStartMonth())
         {
             return 1;
         }
         if (e1.getStartMonth() < e2.getStartMonth())
         {
             return -1;
         }
         if (e1.getDay() > e2.getDay())
         {
             return 1;
         }
         if (e1.getDay() < e2.getDay())
         {
             return -1;
         }
         if (e1.getStartHour() > e2.getStartHour())
         {
             return 1;
         }
         if (e1.getStartHour() < e2.getStartHour())
         {
             return -1;
         }
         return 0;
     };
     eventList.sort(eventComparator);
  }
    // *********read from file function*********
    public boolean readFromFile(String filePath)
    {
        //filePath = "input.txt";
        File file = new File(filePath);
        try
        {
            if (file.createNewFile())
            {
                System.out.println("input.txt does not exist since this is the first run of the program. Creating now.");
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null)
            {
                String[] stSplit = st.split(";");
                if (stSplit.length != 7)
                {
                    return false;
                }
                String name = stSplit[0];
                int year = Integer.parseInt(stSplit[1]);
                int startMonth = Integer.parseInt(stSplit[2]);
                int endMonth = Integer.parseInt(stSplit[3]);
                String days = stSplit[4];
                int startHour = Integer.parseInt(stSplit[5]);
                int endHour = Integer.parseInt(stSplit[6]);
                GregorianCalendar g = new GregorianCalendar(year, (startMonth - 1), 1);
                while ((g.get(Calendar.MONTH) + 1) <= endMonth && g.get(Calendar.YEAR) == year)
                {
                    if (days.contains(dayMap.get(g.get(Calendar.DAY_OF_WEEK))))
                    {
                        int day = g.get(Calendar.DAY_OF_MONTH);
                        createEvent(name, year, (g.get(Calendar.MONTH) + 1), endMonth, day, startHour, endHour);
                    }
                    g.add(Calendar.DAY_OF_MONTH, 1);
                }
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return false;
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public void printEventList()
    {
        for (Event e : eventList)
        {
            System.out.println(e.toString());
        }
    }

    public GregorianCalendar getCal()
    {
        return cal;
    }

    public int getMonthDays()
    {
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public int getCurrentMonth()
    {
        return cal.get(Calendar.MONTH);
    }

    public int getCurrentYear()
    {
        return cal.get(Calendar.YEAR);
    }

    public int getCurrentDay()
    {
        return cal.get(Calendar.DATE);
    }
    
    public void nextDay() {
    	cal.add(Calendar.DATE, 1);
    	update();
    }
    
    public void nextMonth() {
    	cal.add(Calendar.MONTH, 1);
    	update();
    }
    
    public void prevDay() {
    	cal.add(Calendar.DATE, -1);
    	update();
    }
    
    public void prevMonth() {
    	cal.add(Calendar.MONTH, -1);
    	update();
    }

    // Mutator
    public void update()
    {
        for (ChangeListener l : listeners)
        {
            l.stateChanged(new ChangeEvent(this));
        }
    }

    // Attach
    public void attach(ChangeListener listener)
    {
        listeners.add(listener);
    }

    // ************Override function to get all the events in the given viewType
    // only for day, week and month***************
    public List<Event> getEventInSelectedView(String viewType) {
    	sortEvent();
    	List<Event> eventListInSelectedView = new ArrayList<Event>();
    	// checks if view type is day
    	if("day".equalsIgnoreCase(viewType)) {
	    	for(Event e: eventList) {
	    		if(e.getYear() == getCurrentYear()
	    				&& e.getStartMonth() == (getCurrentMonth() + 1)
	    				&& e.getDay() == getCurrentDay()) {
	    			eventListInSelectedView.add(e);// adds event in the viewtype
	    		}
	    	}
	    	//checks if viewtype is month
    	} else if("month".equalsIgnoreCase(viewType)) {
    		for(Event e: eventList) {
	    		if(e.getYear() == getCurrentYear()
	    				&& e.getStartMonth() == (getCurrentMonth() + 1)) {
	    			eventListInSelectedView.add(e);
	    		}
	    	}
    		//checks if the viewtype is week view
    	} else if("week".equalsIgnoreCase(viewType)) {
    		int weekLowerThreshold = getCal().get(Calendar.DAY_OF_WEEK) - 1;//****** the first day of the week******
    		int weekUpperThreshold = 7 - getCal().get(Calendar.DAY_OF_WEEK);//****** the last day of the week*******
    		//System.out.println(weekLowerThreshold + " " + getCal().get(Calendar.DAY_OF_WEEK) + " " + weekUpperThreshold);
    		for(Event e: eventList) {
    			GregorianCalendar eventCal = new GregorianCalendar(e.getYear(), (e.getStartMonth() - 1), e.getDay());
    			// the time difference
    			double diff = (1.0 * getCal().getTimeInMillis() - eventCal.getTimeInMillis()) / (1000 * 60 * 60 * 24);
    			//System.out.println(getCal().getTimeInMillis() + " " +  eventCal.getTimeInMillis());
    			//System.out.println(diff + " " + (diff < 0 && Math.abs(diff) < weekLowerThreshold) + " " + (diff >=0 && diff <= weekUpperThreshold));
    			if(diff < 0 && Math.abs(diff) < weekUpperThreshold) {
    				eventListInSelectedView.add(e);
    			} else if(diff > 0 && diff <= weekLowerThreshold) {
    				eventListInSelectedView.add(e);
    			} else if (diff == 0) {
    				eventListInSelectedView.add(e);
    			}
	    	}
    	}
    	return eventListInSelectedView;
    }
    
    // Override function to get all the events in the given date range
    // For Agenda view type
    public List<Event> getEventInSelectedView(int startYear, int startMonth, int startDay,
    		int endYear, int endMonth, int endDay) {
    	sortEvent();
    	List<Event> eventListInSelectedView = new ArrayList<Event>();
    	for(Event e: eventList) {
    		if(e.getYear() >= startYear && e.getYear() <= endYear
    				&& e.getStartMonth() >= startMonth && e.getStartMonth() <= endMonth
    				&& e.getDay() >= startDay && e.getDay() <= endDay) {
    			eventListInSelectedView.add(e);
    		}
    	}
    	return eventListInSelectedView;
    }
}
