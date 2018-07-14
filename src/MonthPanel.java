import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MonthPanel extends JPanel implements ChangeListener
{
    private DataModel model;
    //private JButton[] buttons;
    private JTextArea textArea;
    private String[] months = { "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December" };
    private String[] days = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };

    public MonthPanel(DataModel m)
    {
        model = m;
        textArea = new JTextArea();
        String month = months[model.getCurrentMonth()] + " ";
        textArea.setText(month);

        textArea.append(String.valueOf(model.getCurrentYear()) + "\n");

        for (String s: days) { textArea.append(s + " "); }

        add(textArea);

        setLayout(new GridLayout(6, 7));
        for (int i = 1; i < model.getMonthDays() + 1; i++)
        {
            JButton dayButton = new JButton(String.valueOf(i));
            dayButton.setPreferredSize(new Dimension(50, 50));
            add(dayButton);
        }
    }

    @Override
    public void paintComponent(Graphics g) { super.paintComponent(g); }

    @Override
    public void stateChanged(ChangeEvent e)
    {

    }
}
