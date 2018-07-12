import javax.swing.*;
import java.awt.*;

public class CalendarView extends JFrame
{
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    public CalendarView()
    {
        setTitle("Calendar");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        MonthPanel panel = new MonthPanel();
        add(panel);
        //pack();
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
