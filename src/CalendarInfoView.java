import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Calendar;

public class CalendarInfoView extends JPanel implements ChangeListener
{
    private final static int NUM_SPACES = 7;
    private final static int TEXT_COLUMN = 55;
    private static final int today = Calendar.getInstance().get(Calendar.DATE);
    private DataModel model;
    private JTextArea textArea;
    private JButton[] dayButton;
    private String[] months = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    private String[] days = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};

    public CalendarInfoView(DataModel m)
    {
        model = m;
        setLayout(new BorderLayout());

        JButton buttonToday = new JButton("Today");
        JButton buttonPrevDay = new JButton("<");
        JButton buttonNextDay = new JButton(">");
        JButton buttonCreate = new JButton("CREATE");

        buttonCreate.setBackground(Color.ORANGE);
        buttonCreate.setOpaque(true);
        buttonCreate.setBorderPainted(false);

        JPanel panel1 = new JPanel();
        panel1.add(buttonToday);
        panel1.add(buttonPrevDay);
        panel1.add(buttonNextDay);
        panel1.add(buttonCreate);

        textArea = new JTextArea();
        String month = months[model.getCurrentMonth()] + " ";
        textArea.setText(month);
        textArea.append(String.valueOf(model.getCurrentYear()) + "\n\n");
        String spaces = " ";

        for (int i = 0; i < NUM_SPACES; i++)
        {
            spaces = spaces.concat(" ");
        }
        for (String s : days)
        {
            textArea.append(s + spaces);
        }
        textArea.setOpaque(false);

        JButton buttonPrevMonth = new JButton("<");
        JButton buttonNextMonth = new JButton(">");

        JPanel panel4 = new JPanel();
        panel4.add(textArea);
        panel4.add(buttonPrevMonth);
        panel4.add(buttonNextMonth);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.add(panel1, BorderLayout.NORTH);
        panel3.add(panel4, BorderLayout.SOUTH);

        JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayout(6, 7));

        this.add(panel3, BorderLayout.NORTH);
        this.add(panel5, BorderLayout.CENTER);

        dayButton = new JButton[model.getMonthDays() + 1];

        for (int i = 1; i < model.getMonthDays() + 1; i++)
        {
            dayButton[i] = new JButton(String.valueOf(i));
            dayButton[i].setPreferredSize(new Dimension(50, 50));
            panel5.add(dayButton[i]);

            if (i == model.getCurrentDay())
            {
                dayButton[i].setBackground(Color.PINK);
                dayButton[i].setOpaque(true);
                dayButton[i].setBorderPainted(false);
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

        buttonToday.addActionListener(event ->
        {
            model.getCal().set(Calendar.DATE, today);
            for (int i = 1; i < model.getMonthDays() + 1; i++)
            {
                if (i == today)
                {
                    dayButton[today].setBackground(Color.PINK);
                    dayButton[today].setOpaque(true);
                    dayButton[today].setBorderPainted(true);
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

        buttonPrevDay.addActionListener(event -> changeDay("prev"));

        buttonNextDay.addActionListener(event -> changeDay("next"));

        buttonCreate.addActionListener(event ->
        {
            JTextField textFieldEventTitle = new JTextField("Untitled Event", TEXT_COLUMN);
            JTextField textFieldDate = new JTextField(7);
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

            int result = JOptionPane.showConfirmDialog(null, myPanel,
                    "Please enter event title, date, starting time and ending time \n", JOptionPane.OK_CANCEL_OPTION);

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

            if (startingTime.equals("") || endingTime.equals("")){
                System.out.println("no events created");
            }
            else {
                int startingHour = Integer.parseInt(startingTime.substring(0, 2));
                int endingHour = Integer.parseInt(endingTime.substring(0, 2));
                System.out.println(startingHour);
                System.out.println(endingHour);
                // ******************************************* Controller **********************************
                model.createEvent(eventTitle, theYear, theMonth, theMonth, theDay, startingHour, endingHour);
            }

        });

        buttonPrevMonth.addActionListener(event -> changeMonth("prev"));

        buttonNextMonth.addActionListener(event -> changeMonth("next"));
    }

    private void printDaysOfWeek()
    {
        String spaces = " ";
        for (int i = 0; i < NUM_SPACES; i++)
        {
            spaces = spaces.concat(" ");
        }
        for (String s : days)
        {
            textArea.append(s + spaces);
        }
    }

    private void changeDay(String option)
    {
        int currentDay = model.getCurrentDay();

        dayButton[currentDay].setBackground(null);
        dayButton[currentDay].setOpaque(false);
        dayButton[currentDay].setBorderPainted(true);

        if (option.equalsIgnoreCase("prev"))
        {
            model.getCal().add(Calendar.DATE, -1);
//            int prevDay = model.getCurrentDay();
//            dayButton[prevDay].setBackground(Color.GRAY);
//            dayButton[prevDay].setOpaque(true);
//            dayButton[prevDay].setBorderPainted(true);
        }
        else if (option.equalsIgnoreCase("next"))
        {
            model.getCal().add(Calendar.DATE, 1);
//            int nextDay = model.getCurrentDay();
//            dayButton[nextDay].setBackground(Color.GRAY);
//            dayButton[nextDay].setOpaque(true);
//            dayButton[nextDay].setBorderPainted(true);
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
            model.getCal().add(Calendar.MONTH, -1);
//            int prevMonth = model.getCurrentMonth();
//            textArea.setText(String.valueOf(months[prevMonth]) + " ");
//            textArea.append(String.valueOf(model.getCurrentYear()) + "\n\n");
//            printDaysOfWeek();
        }
        else if (option.equalsIgnoreCase("next"))
        {
            model.getCal().add(Calendar.MONTH, 1);
//            int nextMonth = model.getCurrentMonth();
//            textArea.setText(String.valueOf(months[nextMonth]) + " ");
//            textArea.append(String.valueOf(model.getCurrentYear()) + "\n\n");
//            printDaysOfWeek();
        }

        int month = model.getCurrentMonth();
        textArea.setText(String.valueOf(months[month]) + " ");
        textArea.append(String.valueOf(model.getCurrentYear()) + "\n\n");
        printDaysOfWeek();
    }

    public void stateChanged(ChangeEvent e)
    {

    }
}
