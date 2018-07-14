import javax.swing.*;

public class CalendarInfoPanel extends JPanel
{
    private DataModel model = new DataModel();
    protected JTextArea textArea;
    private String month;
    private String year;
    private final static int NUM_SPACES = 7;
    protected String[] months = { "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December" };
    protected String[] days = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };

    public  CalendarInfoPanel() { }

    public CalendarInfoPanel(DataModel model)
    {
        //createPanel("prev");
        //DataModel model = m;

        String[] months = { "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December" };
        String[] days = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };

        textArea = new JTextArea();
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
