import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonthPanel extends CalendarInfoPanel implements ActionListener
{
    private DataModel model;
//    private JButton[] buttons;
//    private JTextArea textArea;
//    private String[] months = { "January", "February", "March", "April", "May", "June",
//            "July", "August", "September", "October", "November", "December" };
//    private String[] days = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };

    public MonthPanel(DataModel m)
    {
        model = m;
//        textArea = new JTextArea();
//        String month = months[model.getCurrentMonth()] + " ";
//        textArea.setText(month);
//
//        textArea.append(String.valueOf(model.getCurrentYear()) + "\n\n");
//
//        for (String s: days) { textArea.append(s + " "); }
//
//        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
//        add(textArea);
        //setLayout(new BorderLayout());
        //add(textArea, BorderLayout.NORTH);
        setLayout(new GridLayout(6, 7));
        //setPreferredSize(new Dimension(300, 50));
        for (int i = 1; i < model.getMonthDays() + 1; i++)
        {
            JButton dayButton = new JButton(String.valueOf(i));
            dayButton.setPreferredSize(new Dimension(50, 50));
            add(dayButton);
        }

        //add(buttons, BorderLayout.CENTER);
    }

    @Override
    public void paintComponent(Graphics g) { super.paintComponent(g); }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
