//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class MonthPanel extends CalendarInfoPanel implements ActionListener
//{
//    private DataModel model;
//
//    public MonthPanel(DataModel m)
//    {
//        model = m;
//        setLayout(new GridLayout(6, 7));
//
//        for (int i = 1; i < model.getMonthDays() + 1; i++)
//        {
//            JButton dayButton = new JButton(String.valueOf(i));
//            dayButton.setPreferredSize(new Dimension(50, 50));
//            add(dayButton);
//
//            if ( i == model.getCurrentDay()){
//                dayButton.setBackground(Color.PINK);
//                dayButton.setOpaque(true);
//                dayButton.setBorderPainted(false);
//
//            }
//            dayButton.addActionListener(event ->{
//                // first function, shows the events on that day on DayView panel, go to method
//
//
//
//
//
//            });
//
//        }
//
//    }
//
//    @Override
//    public void paintComponent(Graphics g) { super.paintComponent(g); }
//
//    @Override
//    public void actionPerformed(ActionEvent e)
//    {
//
//    }
//}
