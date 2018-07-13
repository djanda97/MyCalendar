public class Event{
	private String name;
	private int year;
	private int startMonth;
	private int endMonth;
	private int day;
	private int startHour;
	private int endHour;
	
	public Event(String name, int year, int startMonth,
			int day, int startHour, int endHour) {
		super();
		this.name = name;
		this.year = year;
		this.startMonth = startMonth;
		this.endMonth = -1;
		this.day = day;
		this.startHour = startHour;
		this.endHour = endHour;
	}
	public Event(String name, int year, int startMonth, int endMonth,
			int day, int startHour, int endHour) {
		super();
		this.name = name;
		this.year = year;
		this.startMonth = startMonth;
		this.endMonth = endMonth;
		this.day = day;
		this.startHour = startHour;
		this.endHour = endHour;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}
	public int getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getStartHour() {
		return startHour;
	}
	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}
	public int getEndHour() {
		return endHour;
	}
	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}
}

