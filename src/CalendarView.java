import javax.swing.*;
import java.awt.*;

public class CalendarView extends JFrame
{
    //private DataModel model;
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 400;

    public CalendarView()
    {
        DataModel model = new DataModel();
        JFrame frame = new JFrame();
        frame.setTitle("Calendar");
        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // --  there are totally two panels, left calendar panel and right panel with eventPanel and topBarPanel

        // L-1 left top calendar panel
//        LeftTopBarPanel leftTopBarPanel = new LeftTopBarPanel(model);

        CalendarInfoPanel calendarInfoPanel = new CalendarInfoPanel(model);

        // L-2 left bottom calendar panel
        MonthPanel monthPanel = new MonthPanel(model);
        //monthPanel.setLayout(new FlowLayout());

        // define left side panel
        JPanel leftPanel = new JPanel();
        //leftPanel.setPreferredSize(new Dimension(500, 400));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
//        leftPanel.add(leftTopBarPanel);
        leftPanel.add(calendarInfoPanel);
        leftPanel.add(monthPanel);
//        leftPanel.setLayout(new BorderLayout());
//        leftPanel.add(leftTopBarPanel, BorderLayout.NORTH);
//        leftPanel.add(calendarInfoPanel, BorderLayout.CENTER);
//        leftPanel.add(monthPanel, BorderLayout.SOUTH);

        // R-1 right top bar panel
        RightTopBarPanel rightTopBarPanel = new RightTopBarPanel();

        // R-2 right bottom event panel -- name can be changed by Viet
        //EventPanel eventPanel = new EventPanel();

        DayView dayView = new DayView(model);
        // define right side panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(rightTopBarPanel, BorderLayout.NORTH);
        //rightPanel.add(eventPanel, BorderLayout.CENTER); // is able to add after rightpanel is defined.
        rightPanel.add(dayView, BorderLayout.CENTER);


        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.CENTER);
        //frame.add(dayView, BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
