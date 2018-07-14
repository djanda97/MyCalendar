import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.*;
import java.util.*;

public class DataModel {
    private List<Event> eventList;
    private BufferedReader br;
    private Map<Integer, String> dayMap;
    private ArrayList<ChangeListener> listeners;
    private GregorianCalendar cal;

    public DataModel() {
        listeners = new ArrayList<>();
        cal = new GregorianCalendar();
        eventList = new ArrayList<>();
        dayMap = new HashMap<>();
        dayMap.put(1, "S");
        dayMap.put(2, "M");
        dayMap.put(3, "T");
        dayMap.put(4, "W");
        dayMap.put(5, "H");
        dayMap.put(6, "F");
        dayMap.put(7, "A");
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public boolean createEvent(String name, int year, int startMonth,
                               int day, int startHour, int endHour) {
        Event e = new Event(name, year, startMonth, day, startHour, endHour);
        if(checkConflict(e)) {
            eventList.add(e);
            return true;
        }
        return false;
    }

    public boolean createEvent(String name, int year, int startMonth, int endMonth,
                               int day, int startHour, int endHour) {
        Event e = new Event(name, year, startMonth, endMonth, day, startHour, endHour);
        if(checkConflict(e)) {
            eventList.add(e);
            return true;
        }
        return false;
    }

    private boolean checkConflict(Event e) {
        for(Event event : eventList) {
            if(event.getYear() == e.getYear()
                    && event.getStartMonth() == e.getStartMonth()
                    && event.getDay() == e.getDay()
                    && event.getStartHour() >= e.getStartHour()
                    && (event.getEndHour() < e.getStartHour() || event.getEndHour() != -1)) {
                return false;
            }
        }
        return true;
    }

    public void sortEvent() {
        EventComparator eventComparator = new EventComparator();
        Collections.sort(eventList, eventComparator);
    }

    public boolean readFromFile(String filePath) {
        filePath = "/Users/arnabsarkar/Desktop/input.txt";
        File file = new File(filePath);

        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                String[] stSplit = st.split(";");
                if(stSplit.length != 7) {
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
                while((g.get(Calendar.MONTH) + 1) <= endMonth && g.get(Calendar.YEAR) == year) {
                    if(days.contains(dayMap.get(g.get(Calendar.DAY_OF_WEEK)))) {
                        int day = g.get(Calendar.DAY_OF_MONTH);
                        createEvent(name, year, (g.get(Calendar.MONTH) + 1), endMonth, day, startHour, endHour);
                    }
                    g.add(Calendar.DAY_OF_MONTH, 1);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void printEventList() {
        for (Event e : eventList) {
            System.out.println(e.toString());
        }
    }

    public GregorianCalendar getCal() { return cal; }

    public int getMonthDays(){
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public int getCurrentMonth(){
        return cal.get(Calendar.MONTH);
    }

    public int getCurrentYear(){
        return cal.get(Calendar.YEAR);
    }

    public int getCurrentDay(){
        return cal.get(Calendar.DATE);
    }

    // Mutator
    public void update(Event event){
        eventList.add(event);
        ChangeEvent e = new ChangeEvent(this);
        for (ChangeListener l: listeners)
        {
            l.stateChanged(e);
        }
    }

    // Attach
    public void attach(ChangeListener listener){
        listeners.add(listener);
    }

}
