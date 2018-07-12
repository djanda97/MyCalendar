import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
enum MONTHS
{
    Jan, Feb, March, Apr, May, June, July, Aug, Sep, Oct, Nov, Dec;
}
enum DAYS
{
    Sun, Mon, Tue, Wed, Thu, Fri, Sat ;
}
/**
 * This class is mainly for getters and setters and printing the calendar
 * This class also implements the Serializable interface
 *
 */

public class Event implements Serializable{

    private static final long serialVersionUID = 1L;

    String title;
    GregorianCalendar startTime;
    GregorianCalendar endTime;

    /**
     * getter for title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * setter for title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * getter for startTitle
     * @return startTitle
     */
    public GregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     * setter for startTime
     * @param startTime
     */
    public void setStartTime(GregorianCalendar startTime) {
        this.startTime = startTime;
    }

    /**
     * getter for endTitle
     * @return endTitle
     */
    public GregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     * setter for endTitle
     * @return endTitle
     */
    public void setEndTime(GregorianCalendar endTime) {
        this.endTime = endTime;
    }

    /**
     * this method prints the event list in the calendar
     */
    public void printEvents()
    {
        System.out.print(title + " "
                + printTime(startTime.get(Calendar.HOUR_OF_DAY))+":"
                + printTime(startTime.get(Calendar.MINUTE)));

        if(endTime != null) {
            System.out.print(" - "
                    + printTime(endTime.get(Calendar.HOUR_OF_DAY))+":"
                    + printTime(endTime.get(Calendar.MINUTE)));
        }
    }

    /**
     * this method prints the options in create option of the calendar
     */
    public void prettyPrintEvents()
    {
        MONTHS[] arrayOfMonths = MONTHS.values();
        DAYS[] arrayOfDays = DAYS.values();

        System.out.print("  " + (arrayOfDays[startTime.get(Calendar.DAY_OF_WEEK)-1]) +" "
                + (arrayOfMonths[startTime.get(Calendar.MONTH)])+" "
                + startTime.get(Calendar.DAY_OF_MONTH)+" "
                + printTime(startTime.get(Calendar.HOUR_OF_DAY))+":"
                + printTime(startTime.get(Calendar.MINUTE)));

        if(endTime != null) {
            System.out.print(" - "
                    + printTime(endTime.get(Calendar.HOUR_OF_DAY))+":"
                    + printTime(endTime.get(Calendar.MINUTE)));
        }
        System.out.print(" " + title + "\n");
    }

    private String printTime(int i) {
        String returnString = "" + i;
        if(i < 10) {
            returnString = "0" + returnString;
        }
        return returnString;
    }
}
