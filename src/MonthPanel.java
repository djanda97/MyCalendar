import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MonthPanel extends JPanel
{
    private DataModel model;
    private JButton[] buttons;
    private JTextArea weekField;
    private JTextArea monthField;
    private JTextArea yearField;
    private GregorianCalendar cal;

    public MonthPanel()//DataModel m)
    {
        cal = new GregorianCalendar();
        setLayout(new GridLayout(6, 7));
        setSize(100, 100);
        for (int i = 1; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH) + 1; i++)
        {
            String dayValue = String.valueOf(i);
            JButton dayButton = new JButton(dayValue);
            add(dayButton);
        }

//        weekField = new JTextArea(10, 10);
//        monthField = new JTextArea(10, 10);
//        yearField = new JTextArea(10, 10);
        //model = m;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //display();
    }

    public void display()
    {
        int month = Calendar.MONTH;
        int day = Calendar.DAY_OF_MONTH;
        int year = Calendar.YEAR;

//        monthField.setText(String.valueOf(month));
//        yearField.setText(String.valueOf(year));

        for (int i = 1; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH) + 1; i++)
        {
            String dayValue = String.valueOf(i);
            JButton dayButton = new JButton(dayValue);
            add(dayButton);
        }
    }
}
