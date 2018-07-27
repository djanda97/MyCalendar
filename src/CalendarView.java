import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class View extends JFrame implements ChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Model model;
	private List<Event> eventList;
	
	private static final int DEFAULT_WIDTH = 900;
    private static final int DEFAULT_HEIGHT = 400;
    private final static int TEXT_COLUMN = 55;
    private final static int DAYS_IN_WEEK = 7;
    private final static int MAX_DAY_BUTTONS = 37;
    private static final int DAY_VIEW_ROWS = 24;
    private static final int DAY_VIEW_COLUMNS = 1;
    
    MONTHS[] arrayOfMonths = MONTHS.values();
    DAYS[] arrayOfDays = DAYS.values();
    
    private JTextArea textArea;
    private JLabel dayLabel;
    private JButton[] dayButton;
    private TableModel eventTableModel;
    private JTable eventTable;
	
	View(Model model) {
		this.model = model;
		setTitle("Simple Calendar");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLayout(new BorderLayout());
        createMonthView();
        createDayView();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	private void createMonthView()
    {
        JPanel leftButtonPanel = new JPanel();
        leftButtonPanel.setLayout(new FlowLayout());
        
        JButton buttonPrevDay = new JButton("<");
        buttonPrevDay.addActionListener(event -> changeDay("prev"));

        JButton buttonNextDay = new JButton(">");
        buttonNextDay.addActionListener(event -> changeDay("next"));

        JButton buttonCreate = new JButton("CREATE");
        buttonCreate.setBackground(Color.CYAN);
  
        buttonCreate.setOpaque(true);
        buttonCreate.setBorderPainted(false);
        buttonCreate.addActionListener(event ->
        {
            JTextField textFieldEventTitle = new JTextField("Untitled Event", TEXT_COLUMN);
            JTextField textFieldDate = new JTextField(7);
            String currentMonth;
            String currentDay;

            if ((model.getCurrentMonth() + 1) < 10)
            {
                currentMonth = "0" + String.valueOf(model.getCurrentMonth() + 1);
            }
            else
            {
                currentMonth = String.valueOf(model.getCurrentMonth() + 1);
            }

            if (model.getCurrentDay() < 10)
            {
                currentDay = "0" + String.valueOf(model.getCurrentDay());
            }
            else
            {
                currentDay = String.valueOf(model.getCurrentDay());
            }

            String currentDate = currentMonth + "/" + currentDay + "/" + model.getCurrentYear();
            textFieldDate.setText(currentDate);
            textFieldDate.setEditable(false);
            
            JTextField textFieldStartingTime = new JTextField(5);
            JTextField textFieldEndingTime = new JTextField(5);

            JPanel myPanel = new JPanel();
            myPanel.setLayout(new BorderLayout());

            JPanel myPanel1 = new JPanel();
            myPanel1.add(textFieldEventTitle, BorderLayout.NORTH);
            myPanel1.add(Box.createVerticalStrut(15));

            JPanel myPanel2 = new JPanel();
            myPanel2.add(new JLabel("Date: (MM/DD/YYYY)"));
            myPanel2.add(textFieldDate);
            myPanel2.add(new JLabel("Starting time (HH:mm[am/pm])"));
            myPanel2.add(textFieldStartingTime);
            myPanel2.add(new JLabel("to"));
            myPanel2.add(new JLabel("Ending time (HH:mm[am/pm])"));
            myPanel2.add(textFieldEndingTime);

            myPanel.setLayout(new BorderLayout());
            myPanel.add(myPanel1, BorderLayout.NORTH);
            myPanel.add(myPanel2, BorderLayout.SOUTH);

            int input = JOptionPane.showConfirmDialog(null, myPanel,
                    "Please enter event title, date, starting time and ending time \n", JOptionPane.OK_CANCEL_OPTION);

            if (input != JOptionPane.CLOSED_OPTION)
            {
                String eventTitle = textFieldEventTitle.getText();
                String date = textFieldDate.getText();
                String startingTime = textFieldStartingTime.getText();
                String endingTime = textFieldEndingTime.getText();

                if (startingTime.equals("") || endingTime.equals(""))
                {
                    System.out.println("no events created");
                }
                else
                {
                    // ******************************************* Controller **********************************
                    if(!model.createEvent(eventTitle, date, startingTime, endingTime)) {
                    	JOptionPane.showMessageDialog(null, "Conflicting event found! Event not created. Please try again with a different time.",
                    			"Event Info", JOptionPane.WARNING_MESSAGE);
                    } else {
                    	JOptionPane.showMessageDialog(null, "Event created.",
                    			"Event Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        
        leftButtonPanel.add(buttonPrevDay);
        leftButtonPanel.add(buttonNextDay);
        leftButtonPanel.add(buttonCreate);
        
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        String month = arrayOfMonths[model.getCurrentMonth()] + " ";
        textArea.setText(month);
        textArea.append(String.valueOf(model.getCurrentYear()) + "\n\n");
        textArea.setOpaque(false);
        textPanel.add(textArea);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

        JPanel daysOfWeekPanel = new JPanel();
        daysOfWeekPanel.setLayout(new GridLayout(1, DAYS_IN_WEEK));
        JLabel[] dayOfWeekLabel = new JLabel[DAYS_IN_WEEK];
        
        for (int j = 0; j < arrayOfDays.length; j++)
        {
            dayOfWeekLabel[j] = new JLabel(arrayOfDays[j].toString(), JLabel.CENTER);
            daysOfWeekPanel.add(dayOfWeekLabel[j]);
        }

        JPanel daysPanel = new JPanel();
        daysPanel.setLayout(new GridLayout(0, DAYS_IN_WEEK));
        
        dayButton = new JButton[MAX_DAY_BUTTONS];
        //init dayButton
        for(int i = 0; i < MAX_DAY_BUTTONS; i++) {
        	dayButton[i] = new JButton();
        	dayButton[i].setPreferredSize(new Dimension(50, 50));
        }
        updateDayButtons();
       
        
        for(int i = 0; i < MAX_DAY_BUTTONS; i++) {
        	// add action listener to the buttons
        	final int day = i + 1;
            dayButton[i].addActionListener(event ->
            {
            	model.setDay(day);
            	eventList = model.getEventInSelectedView();
                updateDayButtons();

                // update the event day, month, year for eventPanel to use.

                // ****************************************************************
                // wanted to get the data so that can save it to dataModel and change on EventPanel.
                // but it is final, cannot do that
//                int clickedDay = i;

                //  shows the events on that day on DayView panel, go to method
            });
        	
            // Add button to daysPanel
            if(dayButton[i] != null)
        		daysPanel.add(dayButton[i]);
        }

        leftPanel.add(leftButtonPanel);
        leftPanel.add(textPanel);
        leftPanel.add(daysOfWeekPanel);
        leftPanel.add(daysPanel);
        
        add(leftPanel, BorderLayout.WEST);
    }
	
	private void updateDayButtons()
    {
    	GregorianCalendar temp = new GregorianCalendar(model.getCurrentYear(), model.getCurrentMonth(), 1);
    	int k = temp.get(Calendar.DAY_OF_WEEK);
    	int dayButtonIndex = 0;
    	temp.add(Calendar.DATE, -1);
    	int prevDay = temp.get(Calendar.DAY_OF_MONTH) - k + 1;
	    for(; dayButtonIndex < k - 1 ; dayButtonIndex++) {
	    	dayButton[dayButtonIndex].setText(String.valueOf(prevDay++));
	    	dayButton[dayButtonIndex].setEnabled(false);
	    	dayButton[dayButtonIndex].setVisible(false);
	    }
    	for (int i = 0; i < model.getMonthDays(); i++, dayButtonIndex++)
        {
            dayButton[dayButtonIndex].setText(String.valueOf(i+1));
            dayButton[dayButtonIndex].setEnabled(true);
            dayButton[dayButtonIndex].setVisible(true);
            
            dayButton[dayButtonIndex].setBackground(null);
            dayButton[dayButtonIndex].setOpaque(false);
            dayButton[dayButtonIndex].setBorderPainted(true);
            
            if (model.isSameWeek(i+1))
            {
                dayButton[dayButtonIndex].setBackground(Color.CYAN);
                dayButton[dayButtonIndex].setOpaque(true);
                dayButton[dayButtonIndex].setBorderPainted(true);
            }
            if (i + 1 == model.getCurrentDay())
            {
                dayButton[dayButtonIndex].setBackground(Color.GRAY);
                dayButton[dayButtonIndex].setOpaque(true);
                dayButton[dayButtonIndex].setBorderPainted(true);
            }
            if (i + 1 == model.getCurrentDay() && model.isToday())
            {
                dayButton[dayButtonIndex].setBackground(Color.BLUE);
                dayButton[dayButtonIndex].setOpaque(true);
                dayButton[dayButtonIndex].setBorderPainted(true);
            }
        }
    	
    	for(int d = 0; dayButtonIndex < MAX_DAY_BUTTONS; d++, dayButtonIndex++) {
    		dayButton[dayButtonIndex].setText(String.valueOf(d+1));
    		dayButton[dayButtonIndex].setEnabled(false);
    		dayButton[dayButtonIndex].setVisible(false);
    	}
    	int month = model.getCurrentMonth(); 
    	textArea.setText(String.valueOf(arrayOfMonths[month]) + " ");
        textArea.append(String.valueOf(model.getCurrentYear()) + "\n\n");
    }
	
	private void updateDayLabel() {
		DAYS day = arrayOfDays[model.getDayOfWeek() - 1];
		String label = day.toString() + " " + model.getCurrentMonth() + "/" + model.getCurrentDay();
		dayLabel.setText(label);
	}
	
	private void createDayView()
    {
		JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.writeToFile();
				System.exit(0);
			}
		});
        
        JPanel quitButtonPanel = new JPanel();
        quitButtonPanel.setLayout(new FlowLayout());
        quitButtonPanel.add(quitButton);
        
        JPanel dayView = new JPanel();
        dayView.setLayout(new FlowLayout());
        
        dayLabel = new JLabel();
        dayView.add(dayLabel);
        updateDayLabel();
        
        JPanel timePanel = new JPanel();
        timePanel.add(timeTextArea());
        
        eventTableModel = new DefaultTableModel(DAY_VIEW_ROWS, DAY_VIEW_COLUMNS);
        eventTable = new JTable(eventTableModel);
        for(int i = 0; i < DAY_VIEW_ROWS; ++i){
        	eventTable.setRowHeight(i, 32);
        }
        
        updateEventView();
        JPanel fullPanel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane();
        
        fullPanel.add(timePanel, BorderLayout.WEST);
        fullPanel.add(eventTable, BorderLayout.CENTER);
        scrollPane.setViewportView(fullPanel);
        
        rightPanel.add(quitButtonPanel);
        rightPanel.add(dayView);
        rightPanel.add(scrollPane);
        
        add(rightPanel);
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
	
	private void changeDay(String option)
    {
        if (option.equalsIgnoreCase("prev"))
        {
        	model.prevDay();
        }
        else if (option.equalsIgnoreCase("next"))
        {
        	model.nextDay();
        }
    }
	
	private void updateEventView() {
		List<Event> eventList = model.getEventInSelectedView();
        for(Event event : eventList){
            int startTime = (int) event.startTime.get(Calendar.HOUR) - 1;
            eventTableModel.setValueAt(event.getTitle(), startTime, DAY_VIEW_COLUMNS - 1);
        }
        //DefaultTableModel tableModel = (DefaultTableModel) eventTable.getModel();
        //tableModel.fireTableDataChanged();
        
        for(int i = 0; i < DAY_VIEW_ROWS; ++i){
            eventTable.setRowHeight(i, 32);
        }
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		updateDayButtons();
		updateDayLabel();
		updateEventView();
	}
}
