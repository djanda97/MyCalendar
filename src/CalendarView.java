import javax.swing.*;
import java.awt.*;

public class CalendarView extends JFrame
{
    //private DataModel model;
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 600;

    public CalendarView()
    {
        JFrame frame = new JFrame();
        frame.setTitle("Calendar");
        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // --  there are totally two panels, left calendar panel and right panel with eventPanel and topBarPanel

        // L-1 left top calendar panel
        LeftTopBarPanel leftTopBarPanel = new LeftTopBarPanel();

        // L-2 left bottom calendar panel
        MonthPanel calendarPanel = new MonthPanel();

        // define left side panel
        JPanel leftPanel = new JPanel();
        leftPanel.add(leftTopBarPanel, BorderLayout.NORTH);
        leftPanel.add(calendarPanel, BorderLayout.SOUTH);

        // R-1 right top bar panel
        RightTopBarPanel rightTopBarPanel = new RightTopBarPanel();

        // R-2 right bottom event panel -- name can be changed by Viet
        EventPanel eventPanel = new EventPanel();

        // define right side panel
        JPanel rightPanel = new JPanel();
        rightPanel.add(rightTopBarPanel, BorderLayout.NORTH);
        rightPanel.add(eventPanel, BorderLayout.CENTER); // is able to add after rightpanel is defined.

        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
