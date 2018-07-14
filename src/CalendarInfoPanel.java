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

    public  CalendarInfoPanel() { }

    public CalendarInfoPanel(DataModel m)
    {
        //createPanel("prev");
        model = m;
        //setLayout(new GridLayout());
        String[] months = { "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December" };
        String[] days = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };

        textArea = new JTextArea();

        JButton buttonToday = new JButton("Today");
        JButton buttonPrev = new JButton("<");
        JButton buttonNext = new JButton(">");

        add(buttonToday);
        add(buttonPrev);
        add(buttonNext);

        buttonToday.addActionListener(event -> {
            // call get day method
            // model.goto method.
        });

        buttonPrev.addActionListener(event -> {
            model.getCal().add(Calendar.MONTH, -1);
            int prevMonth = model.getCurrentMonth();
            textArea.setText(String.valueOf(months[prevMonth]));
        });

        buttonNext.addActionListener(event -> {
            model.getCal().add(Calendar.MONTH, 1);
            int nextMonth = model.getCurrentMonth();
            textArea.setText(String.valueOf(months[nextMonth]));
        });

        String month = months[model.getCurrentMonth()] + " ";
        textArea.setText(month);

        textArea.append(String.valueOf(model.getCurrentYear()) + "\n\n");

        String spaces = " ";
        for (int i = 0; i < NUM_SPACES; i++) { spaces = spaces.concat(" "); }

        for (String s: days) { textArea.append(s + spaces); }

        textArea.setOpaque(false);
        add(textArea);
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
