import javax.swing.*;
import java.awt.*;

public class CalendarView extends JFrame
{
    //private DataModel model;
    private static final int DEFAULT_WIDTH = 1000;
    private static final int DEFAULT_HEIGHT = 400;

    public CalendarView()
    {
        DataModel model = new DataModel();
        JFrame frame = new JFrame();
        frame.setTitle("Calendar");
        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // --  there are totally two panels, left calendar panel and right panel with eventPanel and topBarPanel


        CalendarInfoPanel calendarInfoPanel = new CalendarInfoPanel(model);

        // left calendar panel
//        MonthPanel monthPanel = new MonthPanel(model);

        // define left side panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.add(calendarInfoPanel);
//        leftPanel.add(monthPanel);

        // R-1 right top bar panel
        RightTopBarPanel rightTopBarPanel = new RightTopBarPanel();

        DayView dayView = new DayView(model);
        // define right side panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(rightTopBarPanel, BorderLayout.NORTH);
        //rightPanel.add(eventPanel, BorderLayout.CENTER); // is able to add after rightpanel is defined.
        rightPanel.add(dayView, BorderLayout.CENTER);


        frame.add(leftPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);
        //frame.add(dayView, BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
