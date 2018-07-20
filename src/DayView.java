import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class DayView extends JPanel implements ChangeListener
{

    private static final int rows = 23;
    private static final int column = 1;
    private static String[][] events;
    private static DataModel model;
    private static JLabel dateLabel;
    private static JTable eventTable;

    // added for the date changes on the left panel
    private int day;
    private int month;
    private int year;

//    private static String[] testDataColumn = {"Monday 13"};
    private String[] testDataColumn = {"Monday 13"};
//    private String[] testDataColumn;

    public DayView(int day, int month, int year, int startingTime, int endingTime){

    }

    public DayView(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public DayView( String[] testDataColumn){

        this.testDataColumn = testDataColumn;
    }

    public void PrintChangedDate(){
        System.out.println("PrintChangedDate method in DayView");
        System.out.println(this.month + " " + this.day + " " + this.year);


    }

    public DayView(DataModel dataModel)
    {
        model = dataModel;
        events = new String[rows][column];

        // ***********************************************************
        // The following code makes the initial screen has the DayView
        // code copied from paintComponent method.
//        super.paintComponent(g);
        setLayout(new BorderLayout());
//        Graphics2D g2 = (Graphics2D) g;
        JScrollPane scrollPane = new JScrollPane();
        dateLabel = new JLabel("Monday 3/31");

        JPanel fullPanel = new JPanel(new BorderLayout());
        eventTable = createEventTable();
        JPanel timePanel = new JPanel();
        timePanel.add(timeTextArea());
        fullPanel.add(timePanel, BorderLayout.WEST);
        fullPanel.add(verticalSeparator());
        fullPanel.add(eventTable, BorderLayout.CENTER);

        scrollPane.setViewportView(fullPanel);
        add(dateLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
//        drawSeperator(g2);
        // ***********************************************************

    }

//    private static JTable createEventTable()
    private JTable createEventTable()
    {
        JTable t = new JTable(testDataRow(), testDataColumn);

        for (int i = 0; i < rows; ++i)
        {
            t.setRowHeight(i, 32);
        }
        return t;
    }

    private static JComponent verticalSeparator()
    {
        JSeparator x = new JSeparator(SwingConstants.VERTICAL);
        x.setPreferredSize(new Dimension(3, 50));
        return x;
    }

    private static JTextArea timeTextArea()
    {
        JTextArea area = new JTextArea();
        area.append("\n");
        for (int i = 1; i < 13; ++i)
        {
            String s = " " + i + "am \n\n";
            area.append(s);
        }
        for (int i = 1; i < 12; ++i)
        {
            String s = " " + i + "pm \n\n";
            area.append(s);
        }
        return area;
    }

    private static String[][] testDataRow()
    {
        String data[][] = new String[23][1];
        // for(int i = 0; i < rows; ++i){
        //     String s = "This is line number" + i;
        //     data[i][0] = s;
        // }
        return data;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setLayout(new BorderLayout());
        Graphics2D g2 = (Graphics2D) g;
        JScrollPane scrollPane = new JScrollPane();
        dateLabel = new JLabel("Monday 3/31");

        JPanel fullPanel = new JPanel(new BorderLayout());
        eventTable = createEventTable();
        JPanel timePanel = new JPanel();
        timePanel.add(timeTextArea());
        fullPanel.add(timePanel, BorderLayout.WEST);
        fullPanel.add(verticalSeparator());
        fullPanel.add(eventTable, BorderLayout.CENTER);

        scrollPane.setViewportView(fullPanel);
        add(dateLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        drawSeperator(g2);
    }

    @Override
    public void stateChanged(ChangeEvent event)
    {

    }

    private void drawSeperator(Graphics2D g2)
    {
        double x = eventTable.getWidth();
//        System.out.println(x);
        Point2D start = new Point2D.Double(2, 2);
        Point2D end = new Point2D.Double(2, 2);
        Line2D seperator = new Line2D.Double(start, end);
        g2.draw(seperator);
    }
}