import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RightTopBarPanel extends JPanel {
    private DataModel model;
    private JButton[] buttons;
    private JTextArea weekField;
    private JTextArea monthField;
    private JTextArea yearField;
    private GregorianCalendar cal;

    public RightTopBarPanel()//DataModel m)
    {
        setLayout(new GridLayout(1, 5));
        setSize(100, 10);

        JButton buttonDay = new JButton("Day");
        JButton buttonWeek = new JButton("Week");
        JButton buttonMonth = new JButton("Month");
        JButton buttonAgenda = new JButton("Agenda");
        JButton buttonFromFile = new JButton("From File");

        add(buttonDay);
        add(buttonWeek);
        add(buttonMonth);
        add(buttonAgenda);
        add(buttonFromFile);

        buttonDay.addActionListener(event->{
            // call get day method
            // model.goto method.
        });
    }

//    public void paintComponent(Graphics g)
//    {
//        super.paintComponent(g);
//    }

//    public void display()
//    {
//        int month = Calendar.MONTH;
//        int day = Calendar.DAY_OF_MONTH;
//        int year = Calendar.YEAR;
//
//        for (int i = 1; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH) + 1; i++)
//        {
//            String dayValue = String.valueOf(i);
//            JButton dayButton = new JButton(dayValue);
//            add(dayButton);
//        }
//    }
}
