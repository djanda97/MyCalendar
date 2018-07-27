import java.awt.*;
import java.awt.geom.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;


enum DAYS {
	Dummy, Sun, Mon, Tue, Wed, Thu, Fri, Sat;
}

public class DayView extends JPanel implements ChangeListener{
    
    private static final int rows = 24;
    private static final int column = 1;
    private TableModel eventTableModel;
    private DataModel model;
    private JLabel dateLabel;
    private JTable eventTable;
    private static GregorianCalendar calendar;
    private int[] tempDates;

    public DayView(DataModel dataModel){
        model = dataModel;
        calendar = dataModel.getCal();
        eventTableModel = new DefaultTableModel(rows,column);
        tempDates = new int[]{calendar.get(Calendar.DATE),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.YEAR)};


        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane();
        dateLabel = new JLabel();
        updateLabel();

        JPanel fullPanel = new JPanel(new BorderLayout());
        eventTable = createEventTable();
        updateEventTable();
        JPanel timePanel = new JPanel();
        timePanel.add(timeTextArea());
        fullPanel.add(timePanel, BorderLayout.WEST);
        fullPanel.add(eventTable, BorderLayout.CENTER);
        scrollPane.setViewportView(fullPanel);

        add(dateLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }


    @Override
    public void stateChanged(ChangeEvent event){
        updateLabel();
        updateEventTable();   
    }

    private JTable createEventTable(){
        JTable t = new JTable(eventTableModel);
        for(int i = 0; i < rows; ++i){
            t.setRowHeight(i, 32);
        }
        return t;
    }

    private JTextArea timeTextArea(){
        JTextArea area = new JTextArea();
        area.append("\n");
        for(int i = 1; i < 13; ++i){
            String s = " " + i + "am \n\n"; 
            area.append(s);
        }
        for(int i = 1; i < 12; ++i){
            String s = " " + i + "pm \n\n"; 
            area.append(s);
        }
        return area;
    }

    private void updateEventTable(){
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1; 
        int day = calendar.get(Calendar.DATE);

        if(tempDates[0] != day || tempDates[1] != month || tempDates[2] != year){
            for(int i = 0; i < rows; ++i){
                eventTableModel.setValueAt("", i, column-1);
            }    
        }

        List<Event> eventList = model.getEventInSelectedView(year, month, day, year, month, day);
        for(Event event : eventList){
            int startTime = (int)event.getStartHour()-1;
            eventTableModel.setValueAt(event.getName(), startTime, column-1);
        }

        DefaultTableModel tableModel = (DefaultTableModel) eventTable.getModel();
        tableModel.fireTableDataChanged();

        for(int i = 0; i < rows; ++i){
            eventTable.setRowHeight(i, 32);
        }
    }
    private void updateLabel(){
        DAYS[] arrayOfDays = DAYS.values();
        int day = calendar.get(Calendar.DATE);
        int dayofweek = calendar.get(Calendar.DAY_OF_WEEK);
        String s = arrayOfDays[dayofweek] + " " + Integer.toString(day);
        dateLabel.setText(s);
    }

}