import javax.swing.*;
import java.awt.*;

public class CalendarView extends JFrame
{
    //private DataModel model;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    public CalendarView()
    {
        JFrame frame = new JFrame();
        frame.setTitle("Calendar");
        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        MonthPanel panel = new MonthPanel();
        frame.add(panel);
        //pack();
        //setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
