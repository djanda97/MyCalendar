//import javax.swing.*;
//import java.awt.*;
//
//public class LeftTopBarPanel extends CalendarInfoPanel {
//    private DataModel model;
//    private CalendarInfoPanel calendarInfoPanel;
//
//    public LeftTopBarPanel(DataModel m)
//    {
//        model = m;
//        calendarInfoPanel = new CalendarInfoPanel(model);
//
////        setLayout(new GridLayout(1, 3));
//        setLayout(new FlowLayout());
////        setSize(80, 10);
//
//        JButton buttonToday = new JButton("Today");
//        JButton buttonPrev = new JButton("<");
//        JButton buttonNext = new JButton(">");
//        JButton buttonCreate = new JButton("CREATE");
//
//        add(buttonToday);
//        add(buttonPrev);
//        add(buttonNext);
//        add(buttonCreate);
//
//        buttonToday.addActionListener(event -> {
//            // call get day method
//            // model.goto method.
//        });
//
//        buttonPrev.addActionListener(event -> {
//            int prevMonth = model.getCurrentMonth() - 1;
//            calendarInfoPanel.textArea.setText(String.valueOf(months[prevMonth]));
//        });
//
//        buttonNext.addActionListener(event -> {
//            int nextMonth = model.getCurrentMonth() + 1;
//            calendarInfoPanel.textArea.setText(String.valueOf(months[nextMonth]));
//        });
//
//        buttonCreate.addActionListener(event -> {
//            // first pop up box
////            String name = JOptionPane.showInputDialog(new JFrame(),
////                    "What is your name?", null);
//
//            final int TEXT_COLUMN = 20;
//            JTextField eventTitle = new JTextField("Untitled Event", TEXT_COLUMN);
//            JTextField date = new JTextField(5);
//            JTextField startingTime = new JTextField(5);
//            JTextField endingTime = new JTextField(5);
//
//            JPanel myPanel = new JPanel();
//            myPanel.add(eventTitle);
//            myPanel.add(Box.createVerticalStrut(15));
////            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
//            myPanel.add(new JLabel("Date:"));
//            myPanel.add(date);
//            myPanel.add(new JLabel("Starting time"));
//            myPanel.add(startingTime);
//            myPanel.add(new JLabel("to"));
//            myPanel.add(new JLabel("Ending time"));
//            myPanel.add(endingTime);
//
//            int result = JOptionPane.showConfirmDialog(null, myPanel,
//                    "Please enter event title, date, starting time and ending time \n", JOptionPane.OK_CANCEL_OPTION);
//
//        });
//    }
//}
