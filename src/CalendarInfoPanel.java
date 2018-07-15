import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class CalendarInfoPanel extends JPanel
{
    private DataModel model;
    protected JTextArea textArea;
    private String month;
    private String year;
    private final static int NUM_SPACES = 7;
    protected String[] months = { "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December" };
    protected String[] days = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };

    public CalendarInfoPanel(DataModel m)
    {
        model = m;
        setLayout(new BorderLayout());

        JButton buttonToday = new JButton("Today");
        JButton buttonPrevDay = new JButton("<");
        JButton buttonNextDay = new JButton(">");
        JButton buttonCreate = new JButton("CREATE");

        buttonCreate.setBackground(Color.orange);
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
        for (int i = 0; i < NUM_SPACES; i++) { spaces = spaces.concat(" "); }
        for (String s: days) { textArea.append(s + spaces); }
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
        panel3.add(panel4, BorderLayout.CENTER);

        JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayout(6, 7));

        this.add(panel3, BorderLayout.NORTH);
        this.add(panel5, BorderLayout.CENTER);

        for (int i = 1; i < model.getMonthDays() + 1; i++)
        {
            JButton dayButton = new JButton(String.valueOf(i));
            dayButton.setPreferredSize(new Dimension(50, 50));
            panel5.add(dayButton);

            if ( i == model.getCurrentDay()){
                dayButton.setBackground(Color.PINK);
                dayButton.setOpaque(true);
                dayButton.setBorderPainted(false);

            }
            dayButton.addActionListener(event ->{
                // first function, shows the events on that day on DayView panel, go to method


            });

        }

        buttonToday.addActionListener(event -> {
            // call get day method
            // model.goto method.
        });

        buttonPrevDay.addActionListener(event -> {
            model.getCal().add(Calendar.DATE, -1);
            int prevDay = model.getCurrentDay();
            // move back on Calendar button views
            // add method to show the button
            System.out.println(prevDay);

        });

        buttonNextDay.addActionListener(event -> {
            model.getCal().add(Calendar.DATE, 1);
            int nextDay = model.getCurrentDay();
            // move back on Calendar button views
            // add method to show the button
            System.out.println(nextDay);
        });

        buttonCreate.addActionListener(event -> {
            // first pop up box
//            String name = JOptionPane.showInputDialog(new JFrame(),
//                    "What is your name?", null);

            final int TEXT_COLUMN = 36;
            JTextField textFieldeventTitle = new JTextField("Untitled Event", TEXT_COLUMN);
            JTextField textFielddate = new JTextField(5);
            JTextField textFieldstartingTime = new JTextField(5);
            JTextField textFieldendingTime = new JTextField(5);

            JPanel myPanel = new JPanel();
            myPanel.setLayout(new BorderLayout());

            JPanel myPanel1 = new JPanel();
            myPanel1.add(textFieldeventTitle, BorderLayout.NORTH);
            myPanel1.add(Box.createVerticalStrut(15));
//            myPanel.add(Box.createHorizontalStrut(15)); // a spacer

            JPanel myPanel2 = new JPanel();
            myPanel2.add(new JLabel("Date:"));
            myPanel2.add(textFielddate);
            myPanel2.add(new JLabel("Starting time"));
            myPanel2.add(textFieldstartingTime);
            myPanel2.add(new JLabel("to"));
            myPanel2.add(new JLabel("Ending time"));
            myPanel2.add(textFieldendingTime);

            myPanel.setLayout(new BorderLayout());
            myPanel.add(myPanel1, BorderLayout.NORTH);
            myPanel.add(myPanel2, BorderLayout.SOUTH);

            int result = JOptionPane.showConfirmDialog(null, myPanel,
                    "Please enter event title, date, starting time and ending time \n", JOptionPane.OK_CANCEL_OPTION);

            String eventTitle = textFieldeventTitle.getText();
            String date = textFielddate.getText();            // specially, if the user did not enter anything, it means current button's date
            String startingTime = textFieldstartingTime.getText();
            String endingTime = textFieldendingTime.getText();

            // for date, 1. if the user didn't enter anything, get the current button's date.
            if (date.equals("")){
                // this need to go to the for loop when generate the days.
                // *************************************************************************
                // this needs to get the current day's date from the current button's date.
                System.out.println("no date");

            }

            int theDay = 0;
            int theMonth = 0;
            int theYear = 0;

            // for date, 2. if the user enter the date, then parse the date into day, month, and year.
            if (!date.equals("")) {

                theDay = Integer.parseInt(date.substring(0, 2));
                theMonth = Integer.parseInt(date.substring(3, 5));
                theYear = Integer.parseInt(date.substring(6));

                System.out.println(theDay);
                System.out.println(theMonth);
                System.out.println(year);
            }

            // need to change the hour into int
            int startingHour = Integer.parseInt(startingTime.substring(0,2));
            int endingHour = Integer.parseInt(endingTime.substring(0,2));

            System.out.println(startingHour);



//             now controller, update the DataModel's data.
            model.createEvent(eventTitle, theYear, theMonth, theMonth, theDay, startingHour, endingHour);

        });

        buttonPrevMonth.addActionListener(event -> {
            model.getCal().add(Calendar.MONTH, -1);
//            model.getCal().add(model.getCurrentMonth(), -1);

            int prevMonth = model.getCurrentMonth();
            textArea.setText(String.valueOf(months[prevMonth]));
            textArea.append(String.valueOf(model.getCurrentYear()) + "\n\n");

        });

        buttonNextMonth.addActionListener(event -> {
            model.getCal().add(Calendar.MONTH, 1);
            int nextMonth = model.getCurrentMonth();
            textArea.setText(String.valueOf(months[nextMonth]));
            textArea.append(String.valueOf(model.getCurrentYear()) + "\n\n");

        });

    }

    public void createPanel(String option)
    {
        textArea = new JTextArea();

        if (option.equalsIgnoreCase("current"))
        {
            month = months[model.getCurrentMonth()];
        }
        else if (option.equalsIgnoreCase("prev"))
        {
            month = months[model.getCurrentMonth() - 1];
        }
        else if (option.equalsIgnoreCase("next"))
        {
            month = months[model.getCurrentMonth() + 1];
        }

        textArea.setText(month);
    }

    public void previousMonth()
    {

    }

    public void nextMonth()
    {

    }
}
