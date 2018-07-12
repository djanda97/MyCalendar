
/**
 * HW1-Solution
 * <p>
 * Summer 2018
 * <p>
 * CS151-Programming Assignment 1 Solution
 *
 * @instructor: Dr. Kim
 * @author Parnika De
 * @version Java SE 8[1.8.0]
 */

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Scanner;


/**
 * To display Calendar in console
 */
public class MyCalendarTester
{
    private static Scanner option;
    static Scanner eventOption = new Scanner(System.in);
    static ArrayList<Event> events = new ArrayList<Event>();

    /**
     * The main method where we get calendar option to display in the console
     * We take user inputs
     * @param args
     */
    public static void main(String[] args)
    {
        CalendarView view = new CalendarView();
//        view.setLayout(new FlowLayout());
//        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        view.setVisible(true);
//		GregorianCalendar cal = new GregorianCalendar(); // capture today
//		option = new Scanner(System.in);
//		boolean load = false;
//		printCalendar(cal, "regular");
//
//		String op = " ";
//
//		while (! "q".equalsIgnoreCase(op))
//		{
//			display();
//			op = option.nextLine();
//			op = op.toUpperCase();
//			switch(op)
//			{
//				case "L":
//					events = readFromFile(true);
//					load = true;
//					break;
//
//				case "V":
//					view(cal);
//					break;
//
//				case "C":
//					Event e = createEvent();
//					if(isConflicting(e)) {
//						{
//							System.out.println("Conflicting event detected! Change the event time or date or both!!");
//							createEvent();
//						}
//					}
//					events.add(e);
//					break;
//
//				case "G":
//					gotoView(cal);
//					break;
//
//				case "E":
//					eventSort();
//					System.out.println();
//					int printYear = -1;
//					for(Event event : events) {
//						if(event.getStartTime().get(Calendar.YEAR) != printYear) {
//							printYear = event.getStartTime().get(Calendar.YEAR);
//							System.out.println(printYear);
//						}
//						event.prettyPrintEvents();
//					}
//					break;
//
//				case "D":
//					delete();
//					break;
//
//				case "Q":
//					if (!load) {
//						ArrayList<Event> oldEvents = readFromFile(false);
//						events.addAll(oldEvents);
//					}
//					eventSort();
//					if(events != null && events.size() > 0) {
//						writeToFile();
//					}
//					System.out.println("Bye!");
//					break;
//
//				default:
//					System.out.println("Invalid Input!");
//					break;
//			}
//		}

    }

    private static boolean isConflicting(Event event)
    {
        for (Event e : events)
        {
            if (event.getStartTime().get(Calendar.MONTH) == e.getStartTime().get(Calendar.MONTH)
                    && event.getStartTime().get(Calendar.DAY_OF_MONTH) == e.getStartTime().get(Calendar.DAY_OF_MONTH)
                    && event.getStartTime().get(Calendar.YEAR) == e.getStartTime().get(Calendar.YEAR))
            {
                if ((event.getStartTime().get(Calendar.HOUR_OF_DAY) * 60 + event.getStartTime().get(Calendar.MINUTE))
                        >= (e.getStartTime().get(Calendar.HOUR_OF_DAY) * 60 + e.getStartTime().get(Calendar.MINUTE))
                        && (e.getEndTime() != null
                        && (event.getStartTime().get(Calendar.HOUR_OF_DAY) * 60 + event.getStartTime().get(Calendar.MINUTE))
                        < (e.getEndTime().get(Calendar.HOUR_OF_DAY) * 60 + e.getEndTime().get(Calendar.MINUTE))))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * This method is used to De-serialize objects from event.txt that we have already created in the write to file method
     * @param printFlag
     * @return: returnEvents
     */
    private static ArrayList<Event> readFromFile(boolean printFlag)
    {
        // creating input stream variables
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList<Event> returnEvents = new ArrayList<Event>();

        try
        {
            // reading binary data
            fis = new FileInputStream("events.txt");

            // converting binary-data to java-object
            ois = new ObjectInputStream(fis);

            // reading object's value and casting ArrayList<Event>
            returnEvents = (ArrayList<Event>) ois.readObject();
        } catch (FileNotFoundException fnfex)
        {
            if (printFlag)
            {
                System.out.println("This is the first run. Please create some events to populate the events.txt");
            }
        } catch (IOException ioex)
        {
            ioex.printStackTrace();
        } catch (ClassNotFoundException ccex)
        {
            ccex.printStackTrace();
        }
        return returnEvents;
    }

    /**
     * this method is used to serialize objects and save them to event.txt
     */
    private static void writeToFile()
    {
        // creating output stream variables
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try
        {
            // for writing or saving binary data
            fos = new FileOutputStream("events.txt");

            // converting java-object to binary-format 
            oos = new ObjectOutputStream(fos);

            // writing or saving ArrayList values to stream
            oos.writeObject(events);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException fnfex)
        {
            fnfex.printStackTrace();
        } catch (IOException ioex)
        {
            ioex.printStackTrace();
        }
    }

    /**
     * this method prints the calendar in the console which is of the type Gregorian calendar
     * precodition: it checks whether its a day view or a month view
     * postcondition: day view - it shows the day and its events if at all they are scheduled
     * 				  month view: it show the whole month and highlights the day it has events with {..}
     * @param c
     * @param viewType
     */
    public static void printCalendar(Calendar c, String viewType)
    {
        MONTHS[] arrayOfMonths = MONTHS.values();
        DAYS[] arrayOfDays = DAYS.values();

        if (viewType == "day")
        {
            System.out.println(arrayOfDays[c.get(Calendar.DAY_OF_WEEK) - 1] + ", " +
                    arrayOfMonths[c.get(Calendar.MONTH)] + " " +
                    c.get(Calendar.DAY_OF_MONTH) + ", " +
                    c.get(Calendar.YEAR)
            );
            eventSort();
            for (Event e : events)
            {
                if (c.get(Calendar.MONTH) == e.getStartTime().get(Calendar.MONTH)
                        && c.get(Calendar.DAY_OF_MONTH) == e.getStartTime().get(Calendar.DAY_OF_MONTH)
                        && c.get(Calendar.YEAR) == e.getStartTime().get(Calendar.YEAR))
                {
                    e.printEvents();
                    System.out.println();
                }
            }
        }
        else
        {
            System.out.print("\t " + arrayOfMonths[c.get(Calendar.MONTH)]);
            System.out.println(" " + c.get(Calendar.YEAR));
            System.out.print("  ");
            for (int i = 0; i < 7; i++)
                System.out.print(arrayOfDays[i] + "  ");
            System.out.println();

            GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
            int k = temp.get(Calendar.DAY_OF_WEEK);
            for (int l = 1; l <= (k - 1) * 5; l++)
            {
                System.out.print(" ");
            }
            int numberOfDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            int currentDate = c.get(Calendar.DATE);
            for (int j = 1; j <= numberOfDays; j++, k++)
            {
                if (j < 10)
                {
                    System.out.print("  ");
                }
                else
                {
                    System.out.print(" ");
                }
                if (j == currentDate && viewType == "regular")
                {
                    System.out.print("[" + j + "]");
                }
                else if (viewType == "month")
                {
                    boolean eventPrint = false;
                    for (Event e : events)
                    {
		    			/*System.out.println(c.get(Calendar.MONTH) + " " + e.getStartTime().get(Calendar.MONTH)+ " " +
		    					c.get(Calendar.DAY_OF_MONTH) + " " + e.getStartTime().get(Calendar.DAY_OF_MONTH) + " " +
		    					c.get(Calendar.YEAR) + " " + e.getStartTime().get(Calendar.YEAR)
		    					);*/
                        if (c.get(Calendar.MONTH) == e.getStartTime().get(Calendar.MONTH)
                                && j == e.getStartTime().get(Calendar.DAY_OF_MONTH)
                                && c.get(Calendar.YEAR) == e.getStartTime().get(Calendar.YEAR))
                        {
                            eventPrint = true;
                            break;
                        }
                    }
                    if (eventPrint)
                    {
                        System.out.print("{" + j + "}");
                    }
                    else
                    {
                        System.out.print("  " + j);
                    }
                }
                else
                {
                    System.out.print("  " + j);
                }
                if (k % 7 == 0)
                {
                    k = 0;
                    System.out.println();
                }
            }
        }
        System.out.println();
    }

    /**
     * this function shows the displays the option we can do when we see the Calendar
     */
    public static void display()
    {
        System.out.println();
        System.out.println("Select one of the following options: ");
        System.out.print("[L]oad");
        System.out.print("   " + "[V]iew by");
        System.out.print("   " + "[C]reate");
        System.out.print("   " + "[G]o to");
        System.out.print("   " + "[E]vent list");
        System.out.print("   " + "[D]elete");
        System.out.print("   " + "[Q]uit");
    }

    /**
     * this method is used to create events which asks for user inputs and returns an event
     * @return e
     */
    public static Event createEvent()
    {
        String op = " ";
        Event e = new Event();
        System.out.println();
        System.out.println("Create an Event :");
        System.out.print("Enter Title :");
        op = eventOption.nextLine();
        e.setTitle(op);
        System.out.print("Enter Date (MM/DD/YYYY) :");
        op = eventOption.nextLine();
        String[] date_part = op.split("/");
        int mm = Integer.parseInt(date_part[0]);
        int dd = Integer.parseInt(date_part[1]);
        int yyyy = Integer.parseInt(date_part[2]);
        System.out.print("Enter Start Time (24 hour format) :");
        op = eventOption.nextLine();
        String[] time_part = op.split(":");
        int hh = Integer.parseInt(time_part[0]);
        int min = Integer.parseInt(time_part[1]);
        GregorianCalendar c_start = new GregorianCalendar(yyyy, --mm, dd, hh, min);
        e.setStartTime(c_start);
        System.out.print("Enter End Time (24 hour format) :");
        op = eventOption.nextLine();
        if ("".equals(op) || op == null)
        {
            e.setEndTime(null);
        }
        else
        {
            time_part = op.split(":");
            hh = Integer.parseInt(time_part[0]);
            min = Integer.parseInt(time_part[1]);
            GregorianCalendar c_end = new GregorianCalendar(yyyy, --mm, dd, hh, min);
            e.setEndTime(c_end);
        }
        return e;
    }

    /**
     * this method sorts events according to date and time
     */
    private static void eventSort()
    {
        Comparator<Event> cmp = new EventComparator();
        Collections.sort(events, cmp);

    }

    /**
     * in this method we delete an event the user chooses
     *
     * precodition: the user has the choice to delete one event from the date he chooses or all the eveents scheduled for that date
     * postcodition: according to the option the user chooses the events are deleted.
     *
     */
    public static void delete()
    {
        String op = " ";
        System.out.print("Enter Date (MM/DD/YYYY) :");
        op = eventOption.nextLine();
        String[] date_part = op.split("/");
        int mm = Integer.parseInt(date_part[0]);
        int dd = Integer.parseInt(date_part[1]);
        int yyyy = Integer.parseInt(date_part[2]);
        for (Event e : events)
        {
            if (mm == e.getStartTime().get(Calendar.MONTH)
                    && dd == e.getStartTime().get(Calendar.DAY_OF_MONTH)
                    && yyyy == e.getStartTime().get(Calendar.YEAR))
            {
                e.prettyPrintEvents();
            }
        }
        System.out.println("[A]ll \t [S]elected");
        op = eventOption.nextLine();
        if ("a".equalsIgnoreCase(op))
        {
            for (int i = 0; i < events.size(); i++)
            {
                Event e = events.get(i);
                if (mm - 1 == e.getStartTime().get(Calendar.MONTH)
                        && dd == e.getStartTime().get(Calendar.DAY_OF_MONTH)
                        && yyyy == e.getStartTime().get(Calendar.YEAR))
                {
                    events.remove(e);
                    System.out.print("Event deleted: ");
                    e.prettyPrintEvents();
                    i--;
                }
            }
        }
        else if ("s".equalsIgnoreCase(op))
        {
            System.out.print("Enter Start Time (24 hour format) :");
            op = eventOption.nextLine();
            String[] time_part = op.split(":");
            int hh = Integer.parseInt(time_part[0]);
            int min = Integer.parseInt(time_part[1]);
            for (int i = 0; i < events.size(); i++)
            {
                Event e = events.get(i);
                if (mm - 1 == e.getStartTime().get(Calendar.MONTH)
                        && dd == e.getStartTime().get(Calendar.DAY_OF_MONTH)
                        && yyyy == e.getStartTime().get(Calendar.YEAR)
                        && hh == e.getStartTime().get(Calendar.HOUR_OF_DAY)
                        && min == e.getStartTime().get(Calendar.MINUTE))
                {
                    events.remove(e);
                    System.out.print("Event deleted: ");
                    e.prettyPrintEvents();
                    i--;
                }
            }
        }
        else
        {
            System.out.println("Invalid Choice! Please try again later.");
        }
    }

    /**
     * In this methods the user is asked how he wants to view his calendar
     * options: month-view and day view
     * there are options to view previous, next or the main menu
     *
     * @param c
     */
    public static void view(Calendar c)
    {
        System.out.println("[D]ay view or [M]onth view ?");
        String op = eventOption.nextLine();
        if ("d".equalsIgnoreCase(op))
        {
            op = "";
            while (!"m".equalsIgnoreCase(op))
            {
                printCalendar(c, "day");
                System.out.println("[P]revious or [N]ext or [M]ain menu ? ");
                op = eventOption.nextLine();
                if ("p".equalsIgnoreCase(op))
                {
                    c.add(Calendar.DAY_OF_MONTH, -1);
                }
                else if ("n".equalsIgnoreCase(op))
                {
                    c.add(Calendar.DAY_OF_MONTH, 1);
                }
                else if (!"m".equalsIgnoreCase(op))
                {
                    System.out.println("Invalid Choice! Please try again later.");
                }
            }
        }
        else if ("m".equalsIgnoreCase(op))
        {
            op = "";
            while (!"m".equalsIgnoreCase(op))
            {
                printCalendar(c, "month");
                System.out.println("[P]revious or [N]ext or [M]ain menu ? ");
                op = eventOption.nextLine();
                if ("p".equalsIgnoreCase(op))
                {
                    c.add(Calendar.MONTH, -1);
                }
                else if ("n".equalsIgnoreCase(op))
                {
                    c.add(Calendar.MONTH, 1);
                }
                else if (!"m".equalsIgnoreCase(op))
                {
                    System.out.println("Invalid Choice! Please try again later.");
                }
            }
        }
        else
        {
            System.out.println("Invalid Choice! Please try again later.");
        }
    }

    /**
     * in this the user is asked to enter a date in the form of MM/DD/YYYY and then the calendar displays the
     * Day view of the requested date including any event scheduled on that day in the order of starting time.
     * @param c
     */
    public static void gotoView(Calendar c)
    {
        System.out.print("Enter Date (MM/DD/YYYY) :");
        String op = eventOption.nextLine();
        String[] date_part = op.split("/");
        int mm = Integer.parseInt(date_part[0]);
        int dd = Integer.parseInt(date_part[1]);
        int yyyy = Integer.parseInt(date_part[2]);
        GregorianCalendar temp = new GregorianCalendar(yyyy, mm - 1, dd);
        printCalendar(temp, "day");

    }
}
