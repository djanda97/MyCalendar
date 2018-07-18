import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Calendar;
import java.util.List;

public class CalendarView extends JFrame implements ChangeListener
{
    private static final int DEFAULT_WIDTH = 900;
    private static final int DEFAULT_HEIGHT = 400;
    private final static int DAYS_IN_WEEK = 7;
    private final static int TEXT_COLUMN = 55;
    private DataModel model;
    private List<Event> eventList;
    private JTextArea textArea;
    private JButton[] dayButton;
    private String[] months = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    private String[] days = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};

    public CalendarView(DataModel m)
    {
//        eventList = new ArrayList<>();
        this.model = m;
        this.setTitle("Calendar");
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setLayout(new BorderLayout());
        this.createLeftPanel();
        this.createRightPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void changeDay(String option)
    {
        int currentDay = model.getCurrentDay();

        dayButton[currentDay].setBackground(null);
        dayButton[currentDay].setOpaque(false);
        dayButton[currentDay].setBorderPainted(true);

        if (option.equalsIgnoreCase("prev"))
        {
        	model.prevDay();
        }
        else if (option.equalsIgnoreCase("next"))
        {
        	model.nextDay();
        }

        int day = model.getCurrentDay();
        dayButton[day].setBackground(Color.GRAY);
        dayButton[day].setOpaque(true);
        dayButton[day].setBorderPainted(true);
    }

    private void changeMonth(String option)
    {
        if (option.equalsIgnoreCase("prev"))
        {
        	model.prevMonth();
        }
        else if (option.equalsIgnoreCase("next"))
        {
        	model.nextMonth();
        }

        int month = model.getCurrentMonth();
        textArea.setText(String.valueOf(months[month]) + " ");
        textArea.append(String.valueOf(model.getCurrentYear()) + "\n\n");
    }

    private void createLeftPanel()
    {
        JPanel leftButtonPanel = new JPanel();
        leftButtonPanel.setLayout(new FlowLayout());

        JButton buttonToday = new JButton("Today");
        buttonToday.addActionListener(event ->
        {
            model.getCal().set(Calendar.DATE, model.getToday());
            for (int i = 1; i < model.getMonthDays() + 1; i++)
            {
                if (i == model.getToday())
                {
                    dayButton[model.getToday()].setBackground(Color.PINK);
                    dayButton[model.getToday()].setOpaque(true);
                    dayButton[model.getToday()].setBorderPainted(true);
                }
                else
                {
                    dayButton[i].setBackground(null);
                    dayButton[i].setOpaque(false);
                    dayButton[i].setBorderPainted(true);
                }
            }
            // call get day method
            // model.goto method.
        });

        JButton buttonPrevDay = new JButton("<");
        buttonPrevDay.addActionListener(event -> changeDay("prev"));

        JButton buttonNextDay = new JButton(">");
        buttonNextDay.addActionListener(event -> changeDay("next"));

        JButton buttonCreate = new JButton("CREATE");
        buttonCreate.setBackground(Color.ORANGE);
        buttonCreate.setOpaque(true);
        buttonCreate.setBorderPainted(false);
        buttonCreate.addActionListener(event ->
        {
            JTextField textFieldEventTitle = new JTextField("Untitled Event", TEXT_COLUMN);
            JTextField textFieldDate = new JTextField(7);
            String currentMonth;
            String currentDay;

            if ((model.getCurrentMonth() + 1) < 10)
            {
                currentMonth = "0" + String.valueOf(model.getCurrentMonth() + 1);
            }
            else
            {
                currentMonth = String.valueOf(model.getCurrentMonth() + 1);
            }

            if (model.getCurrentDay() < 10)
            {
                currentDay = "0" + String.valueOf(model.getCurrentDay());
            }
            else
            {
                currentDay = String.valueOf(model.getCurrentDay());
            }

            String currentDate = currentMonth + "/" + currentDay + "/" + model.getCurrentYear();
            textFieldDate.setText(currentDate);
            JTextField textFieldStartingTime = new JTextField(5);
            JTextField textFieldEndingTime = new JTextField(5);

            JPanel myPanel = new JPanel();
            myPanel.setLayout(new BorderLayout());

            JPanel myPanel1 = new JPanel();
            myPanel1.add(textFieldEventTitle, BorderLayout.NORTH);
            myPanel1.add(Box.createVerticalStrut(15));

            JPanel myPanel2 = new JPanel();
            myPanel2.add(new JLabel("Date: (MM/DD/YYYY)"));
            myPanel2.add(textFieldDate);
            myPanel2.add(new JLabel("Starting time (HH:00)"));
            myPanel2.add(textFieldStartingTime);
            myPanel2.add(new JLabel("to"));
            myPanel2.add(new JLabel("Ending time (HH:00)"));
            myPanel2.add(textFieldEndingTime);

            myPanel.setLayout(new BorderLayout());
            myPanel.add(myPanel1, BorderLayout.NORTH);
            myPanel.add(myPanel2, BorderLayout.SOUTH);

            int input = JOptionPane.showConfirmDialog(null, myPanel,
                    "Please enter event title, date, starting time and ending time \n", JOptionPane.OK_CANCEL_OPTION);

            if (input != JOptionPane.CLOSED_OPTION)
            {
                String eventTitle = textFieldEventTitle.getText();
                String date = textFieldDate.getText();            // specially, if the user did not enter anything, it means current button's date
                String startingTime = textFieldStartingTime.getText();
                String endingTime = textFieldEndingTime.getText();


                // for date, 1. if the user didn't enter anything, get the current button's date.
                if (date.equals(""))
                {
                    // this need to go to the for loop when generate the days.
                    // *************************************************************************
                    // this needs to get the current day's date from the current button's date.
                    System.out.println("no date");

                }

                int theDay = 0;
                int theMonth = 0;
                int theYear = 0;

                // for date, 2. if the user enter the date, then parse the date into day, month, and year.
                if (!date.equals(""))
                {
                    theDay = Integer.parseInt(date.substring(0, 2));
                    theMonth = Integer.parseInt(date.substring(3, 5));
                    theYear = Integer.parseInt(date.substring(6));

                    System.out.println(theDay);
                    System.out.println(theMonth);
                    System.out.println(theYear);
                }

                if (startingTime.equals("") || endingTime.equals(""))
                {
                    System.out.println("no events created");
                }
                else
                {
                    int startingHour = Integer.parseInt(startingTime.substring(0, 2));
                    int endingHour = Integer.parseInt(endingTime.substring(0, 2));
                    System.out.println(startingHour);
                    System.out.println(endingHour);
                    // ******************************************* Controller **********************************
                    model.createEvent(eventTitle, theYear, theMonth, theMonth, theDay, startingHour, endingHour);
                }
            }
        });

        leftButtonPanel.add(buttonToday);
        leftButtonPanel.add(buttonPrevDay);
        leftButtonPanel.add(buttonNextDay);
        leftButtonPanel.add(buttonCreate);

        // month and year: July 2018
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        String month = months[model.getCurrentMonth()] + " ";
        textArea.setText(month);
        textArea.append(String.valueOf(model.getCurrentYear()) + "\n\n");
        textArea.setOpaque(false);
        textPanel.add(textArea);

        JPanel monthButtonPanel = new JPanel();
        JButton buttonPrevMonth = new JButton("<");
        buttonPrevMonth.addActionListener(event -> changeMonth("prev"));

        JButton buttonNextMonth = new JButton(">");
        buttonNextMonth.addActionListener(event -> changeMonth("next"));

        monthButtonPanel.add(buttonPrevMonth);
        monthButtonPanel.add(buttonNextMonth);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

        JPanel daysOfWeekPanel = new JPanel();
        daysOfWeekPanel.setLayout(new GridLayout(1, DAYS_IN_WEEK - 1));
        JLabel[] dayOfWeekLabel = new JLabel[DAYS_IN_WEEK];

        JPanel daysPanel = new JPanel();
        daysPanel.setLayout(new GridLayout(5, DAYS_IN_WEEK));
        dayButton = new JButton[model.getMonthDays() + 1];

        for (int i = 1; i < model.getMonthDays() + 1; i++)
        {
            if (i == 1)
            {
                for (int j = 0; j < days.length; j++)
                {
                    dayOfWeekLabel[j] = new JLabel(days[j], JLabel.CENTER);
                    daysOfWeekPanel.add(dayOfWeekLabel[j]);
                }
            }

            dayButton[i] = new JButton(String.valueOf(i));
            dayButton[i].setPreferredSize(new Dimension(50, 50));
            daysPanel.add(dayButton[i]);

            if (i == model.getCurrentDay())
            {
                dayButton[i].setBackground(Color.PINK);
                dayButton[i].setOpaque(true);
                dayButton[i].setBorderPainted(true);
            }

            dayButton[i].addActionListener(event ->
            {
                model.printEventList();

                // update the event day, month, year for eventPanel to use.

                // ****************************************************************
                // wanted to get the data so that can save it to dataModel and change on EventPanel.
                // but it is final, cannot do that
//                int clickedDay = i;

                //  shows the events on that day on DayView panel, go to method
            });
        }

        leftPanel.add(leftButtonPanel);
        leftPanel.add(textPanel);
        leftPanel.add(monthButtonPanel);
        leftPanel.add(daysOfWeekPanel);
        leftPanel.add(daysPanel);

        this.add(leftPanel, BorderLayout.WEST);
    }

    private void createRightPanel()
    {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        JPanel rightButtonPanel = new JPanel();
        rightButtonPanel.setLayout(new FlowLayout());

        JButton buttonDay = new JButton("Day");
        JButton buttonWeek = new JButton("Week");
        JButton buttonMonth = new JButton("Month");
        JButton buttonAgenda = new JButton("Agenda");
        JButton buttonFromFile = new JButton("From File");

        rightButtonPanel.add(buttonDay);
        rightButtonPanel.add(buttonWeek);
        rightButtonPanel.add(buttonMonth);
        rightButtonPanel.add(buttonAgenda);
        rightButtonPanel.add(buttonFromFile);

        rightPanel.add(rightButtonPanel, BorderLayout.NORTH);

        buttonDay.addActionListener(event ->
        {
            // call get day method
            // model.goto method.
        	eventList = model.getEventInSelectedView("day");
        	System.out.println(eventList.toString());
        });

        buttonWeek.addActionListener(event ->
        {
        	eventList = model.getEventInSelectedView("week");
        	System.out.println(eventList.toString());
        });

        buttonMonth.addActionListener(event ->
        {
        	eventList = model.getEventInSelectedView("month");
        	System.out.println(eventList.toString());
        });

        buttonAgenda.addActionListener(event ->
        {
            JTextField textFieldStartingDate = new JTextField( 10);
            JTextField textFieldEndingDate = new JTextField( 10);

            JPanel myPanel = new JPanel();

            // ask user input
            myPanel.add(new JLabel("Starting date: (DD/MM/YYYY)"));
            myPanel.add(textFieldStartingDate);
            myPanel.add(Box.createVerticalStrut(15));
            myPanel.add(new JLabel("Ending date: (DD/MM/YYYY)"));
            myPanel.add(textFieldEndingDate);

            JOptionPane.showConfirmDialog(null, myPanel,
                "Please enter event title, date, starting time and ending time \n", JOptionPane.OK_CANCEL_OPTION);

            String startingDate = textFieldStartingDate.getText();
            String endingDate = textFieldEndingDate.getText();

            // if 1. if the user didn't enter anything, do not do anything
            if (startingDate.equals("") || endingDate.equals(""))
            {
                System.out.println("no time period entered");
            }

            int startingDay = 0;
            int startingMonth = 0;
            int startingYear = 0;

            int endingDay = 0;
            int endingMonth = 0;
            int endingYear = 0;

            // if 2. if the user enter the date, then parse the date into day, month, and year.
            if (!((startingDate.equals("") || endingDate.equals(""))))
            {
                startingDay = Integer.parseInt(startingDate.substring(0, 2));
                startingMonth = Integer.parseInt(startingDate.substring(3, 5));
                startingYear = Integer.parseInt(startingDate.substring(6));

                endingDay = Integer.parseInt(endingDate.substring(0, 2));
                endingMonth = Integer.parseInt(endingDate.substring(3, 5));
                endingYear = Integer.parseInt(endingDate.substring(6));

                System.out.println(startingDay);
                System.out.println(startingMonth);
                System.out.println(startingYear);

                System.out.println(endingDay);
                System.out.println(endingMonth);
                System.out.println(endingYear);
            }

            // situation 0:
            // if same month, loop through each day.
            if (startingMonth == endingMonth){
                for (int i = startingDay; i < endingDay + 1; i++){
                    String day = String.valueOf(i);
                    eventList = model.getEventInSelectedView(day);

                    //*********************************************************************
//                    model.getEventInSelectedView(day);
                    // this return a list,
                    // but I wanna call getEventDay() method to get the details of the event
                    //*********************************************************************


                }
                System.out.println(eventList.toString());

                // ?????????????????????????????????????? current code the event title and time are missing
            }

            // situation 1:
            // if different months, 1/12 - 2/12 get the starting day to the end of the starting month, loop through each day,
            // get the ending day to the end of the ending month, loop through each day.

            // situation 2:
            // if different months, 1/12 - 5/12 but with other whole months inside, in addition to situation 1,
            // loop through the months in between

            // situation 3:
            // if different years, look through the month -- same with situation 2.



        });

        buttonFromFile.addActionListener(event ->
        {
            //model.readFromFile("/Users/arnabsarkar/Desktop/input.txt");
            model.readFromFile("input.txt");
        	model.printEventList();
        });

        rightPanel.add(new DayView(model), BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.CENTER);
    }

    public void stateChanged(ChangeEvent e)
    {

    }
}
