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
            		eventTableModel.setValueAt(j + "", i, j);
            	} else {
            		eventTableModel.setValueAt("", i, j);
            	}
            }
        }

        JTable t = new JTable(eventTableModel){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
            {
                Component c = super.prepareRenderer(renderer, row, column);

                    if(!isRowSelected(row)){
                        c.setBackground(getBackground());
                        //int modelRow = convertRowIndexToModel(row);
                        String hiddenDataString = (String)getModel().getValueAt(0, COLUMNS-2);
                        //System.out.println(hiddenDataString);
                        String[] hiddenDataRows = hiddenDataString.split("-");
                        String[][] hiddenDataColumns = new String[TIME_ROWS][MONTH_COLUMNS];
                        for(int i = 0; i < TIME_ROWS; i++) {
                        	hiddenDataColumns[i] = hiddenDataRows[i].split(":");
                        }
                        for(int i = 0; i < TIME_ROWS; i++) {
                        	for(int j = 0; j < MONTH_COLUMNS; j++) {
                        		if(!"0".equals(hiddenDataColumns[i][j]) && i == row && j == column) {
                        			int data = Integer.parseInt(hiddenDataColumns[i][j]) % 18;
                        			if(data == 1) c.setBackground(Color.YELLOW);
        	                        if(data == 2) c.setBackground(new Color(176,224,230));
        	                        if(data == 3) c.setBackground(Color.RED);
        	                        if(data == 4) c.setBackground(Color.PINK);
        	                        if(data == 5) c.setBackground(Color.ORANGE);
        	                        if(data == 6) c.setBackground(Color.MAGENTA);
        	                        if(data == 7) c.setBackground(Color.LIGHT_GRAY);
        	                        if(data == 8) c.setBackground(Color.GREEN);
        	                        if(data == 9) c.setBackground(new Color(128, 0, 128));
        	                        if(data == 10) c.setBackground(new Color(0, 128, 128));
        	                        if(data == 11) c.setBackground(new Color(152,251,152));
        	                        if(data == 12) c.setBackground(new Color(128, 128, 0));
        	                        if(data == 13) c.setBackground(new Color(128, 0, 0));
        	                        if(data == 14) c.setBackground(new Color(192, 192, 192));
        	                        if(data == 15) c.setBackground(new Color(0, 255, 255));
        	                        if(data == 16) c.setBackground(new Color(255, 215, 0));
        	                        if(data == 17) c.setBackground(new Color(255, 127, 0));
        	                        if(data == 0) c.setBackground(new Color(210, 105, 30));
                        		}
                        	}
                        }
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
        int eventCount = 1;
        int hiddenDataRow = 1;
        int hiddenDataColumn = 1;
        int[][] hiddenData = new int[TIME_ROWS][MONTH_COLUMNS];
        for(int i = 0; i < TIME_ROWS; i++) {
        	for(int j = 0; j < MONTH_COLUMNS; j++) {
        		hiddenData[i][j] = 0;
        	}
        }
        for(Event event : eventList){
            int eventIndex = (int) event.getStartHour();
            int hightlightIndex = (int) event.getEndHour();
            int eventColumn = event.getDay() - 1;
            eventTableModel.setValueAt(event.getName(), eventIndex, eventColumn + COLUMNS - 1);
            hiddenDataColumn = eventColumn + COLUMNS - 1;
            for(hiddenDataRow = eventIndex; hiddenDataRow < hightlightIndex; hiddenDataRow++){
            	hiddenData[hiddenDataRow][hiddenDataColumn] = eventCount;
            }
            eventCount++;
            
        }
        String hiddenDataString = "";
        for(int i = 0; i < TIME_ROWS; i++) {
        	for(int j = 0; j < MONTH_COLUMNS; j++) {
        		hiddenDataString += hiddenData[i][j] + ":";
        	}
        	hiddenDataString += "-";
        	//System.out.println(hiddenDataString);
        }
        eventTableModel.setValueAt(hiddenDataString, 0, COLUMNS - 2);
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