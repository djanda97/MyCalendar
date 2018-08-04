import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;


public class MonthView extends JPanel implements ChangeListener, View {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataModel model;
    private static Calendar calendar;
    
    private static final int TIME_ROWS = 24;
    private static final int TIME_COLUMN = 1;
    private static final int MONTH_COLUMNS = 32;
    private static final int COLUMNS = 2;
    private static final int ROW_HEIGHT = 64;
    private static final int COLUMN_WIDTH = 150;
    private TableModel eventTableModel;
    private JLabel dateLabel;
    private JTable eventTable;

    public MonthView(DataModel dataModel){
        model = dataModel;
        calendar = model.getCal();
        eventTableModel = new DefaultTableModel(TIME_ROWS, MONTH_COLUMNS);

        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane();
        dateLabel = new JLabel();
        updateLabel();

        JPanel fullPanel = new JPanel(new BorderLayout());
        eventTable = createEventTable();
        updateEventTable();
        fullPanel.add(timeTable(), BorderLayout.WEST);
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
        eventTable = createEventTable();
        updateEventTable();   
    }

    @Override
    public JTable createEventTable(){
    	
    	int daysInMonth = model.getMonthDays() + 1;
    	
        for(int i = 0; i < TIME_ROWS; ++i){
            for(int j = 0; j < daysInMonth; ++j){
            	if(i == 0) {
            		eventTableModel.setValueAt("" + j, i, j);
            	} else {
            		eventTableModel.setValueAt("" + 0, i, j);
            	}
            }
        }

        JTable t = new JTable(eventTableModel){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
            {
                Component c = super.prepareRenderer(renderer, row, column);
                	System.out.println(column);
                    if(!isRowSelected(row)){
                        c.setBackground(getBackground());
                        int modelRow = convertRowIndexToModel(row);
                        String hiddenData = (String)getModel().getValueAt(modelRow, COLUMNS-2);
                        String[] data = hiddenData.split("-");
                        if("1".equals(data[0]) && column == Integer.parseInt(data[1])) c.setBackground(Color.YELLOW);
                        if("2".equals(data[0]) && column == Integer.parseInt(data[1])) c.setBackground(new Color(176,224,230));
                        if("3".equals(data[0]) && column == Integer.parseInt(data[1])) c.setBackground(Color.RED);
                        if("4".equals(data[0]) && column == Integer.parseInt(data[1])) c.setBackground(Color.PINK);
                        if("5".equals(data[0]) && column == Integer.parseInt(data[1])) c.setBackground(Color.ORANGE);
                        if("6".equals(data[0]) && column == Integer.parseInt(data[1])) c.setBackground(Color.MAGENTA);
                        if("7".equals(data[0]) && column == Integer.parseInt(data[1])) c.setBackground(Color.LIGHT_GRAY);
                        if("8".equals(data[0]) && column == Integer.parseInt(data[1])) c.setBackground(Color.GREEN);
                        if("9".equals(data[0]) && column == Integer.parseInt(data[1])) c.setBackground(new Color(128, 0, 128));
                        if("10".equals(data[0]) && column == Integer.parseInt(data[1])) c.setBackground(new Color(0, 128, 128));
                        if("11".equals(data[0]) && column == Integer.parseInt(data[1])) c.setBackground(new Color(152,251,152));
                        if("12".equals(data[0]) && column == Integer.parseInt(data[1])) c.setBackground(new Color(128, 128, 0));
                        if("13".equals(data[0]) && column == Integer.parseInt(data[1])) c.setBackground(new Color(128, 0, 0));
                        if("14".equals(data[0]) && column == Integer.parseInt(data[1])) c.setBackground(new Color(192, 192, 192));
                        if("15".equals(data[0]) && column == Integer.parseInt(data[1])) c.setBackground(new Color(0, 255, 255));
                        if("16".equals(data[0]) && column == Integer.parseInt(data[1])) c.setBackground(new Color(255, 215, 0));
                        if("17".equals(data[0]) && column == Integer.parseInt(data[1])) c.setBackground(new Color(255, 127, 0));
                        if("18".equals(data[0]) && column == Integer.parseInt(data[1])) c.setBackground(new Color(210, 105, 30));

                    }

                return c;
            }
        };

        for(int i = 0; i < TIME_ROWS; ++i){
            t.setRowHeight(i, ROW_HEIGHT/2);
        }

        for(int i = 1; i < daysInMonth; ++i){
            t.getColumnModel().getColumn(i).setPreferredWidth(COLUMN_WIDTH);
        }
       
        t.getColumnModel().getColumn(0).setMinWidth(0);
        t.getColumnModel().getColumn(0).setMaxWidth(0);
        t.getColumnModel().getColumn(0).setWidth(0);
        return t;
    }

    @Override
    public JTable timeTable(){
        TableModel model = new DefaultTableModel(TIME_ROWS, TIME_COLUMN);
        for(int i = 1; i < 13; ++i){
            String s = " " + i + "am"; 
            model.setValueAt(s, i, TIME_COLUMN-1);
        }
        for(int i = 1; i < 12; ++i){
            String s = " " + i + "pm"; 
            model.setValueAt(s, i+12, TIME_COLUMN-1);
        }
        JTable t = new JTable(model);
        for(int i = 0; i < TIME_ROWS; ++i){
            t.setRowHeight(i, ROW_HEIGHT);
        }
        return t;
    }

    @Override
    public void updateEventTable(){
        List<Event> eventList = model.getEventInSelectedView("month");
        int hiddenData = 1;
        for(Event event : eventList){
            int eventIndex = (int) event.getStartHour();
            int hightlightIndex = (int) event.getEndHour();
            int eventColumn = event.getDay() - 1;
            eventTableModel.setValueAt(event.getName(), eventIndex, eventColumn + COLUMNS - 1);
            for(int i = eventIndex; i <= hightlightIndex; ++i){
                eventTableModel.setValueAt(hiddenData + "-" + (eventColumn + COLUMNS - 1), i, COLUMNS - 2);
            }
            ++hiddenData;
        }

        for(int i = 0; i < TIME_ROWS; ++i){
            eventTable.setRowHeight(i, ROW_HEIGHT);
        }
    }
    
    @Override
    public void updateLabel(){
        DAYS[] arrayOfDays = DAYS.values();
        int day = calendar.get(Calendar.DATE);
        int dayofweek = calendar.get(Calendar.DAY_OF_WEEK);
        String s = arrayOfDays[dayofweek] + " " + Integer.toString(day);
        dateLabel.setText(s);
    }

}