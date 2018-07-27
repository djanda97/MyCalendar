public class MyCalendarTester
{
    public static void main(String[] args)
    {
        DataModel model = new DataModel();
        CalendarView calendarView = new CalendarView(model);
        model.attach(calendarView);
    }
}