import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventModel 
{
	private List<Event> eventList;

	public EventModel(ArrayList<Event> eventList) {
		this.eventList = eventList;
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
}