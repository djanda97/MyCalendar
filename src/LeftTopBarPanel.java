import javax.swing.*;
import java.awt.*;

public class LeftTopBarPanel extends CalendarInfoPanel {
    private DataModel model;
    private CalendarInfoPanel calendarInfoPanel;

    public LeftTopBarPanel(DataModel m)
    {
        model = m;
        calendarInfoPanel = new CalendarInfoPanel(model);

//        setLayout(new GridLayout(1, 3));
        setLayout(new FlowLayout());
//        setSize(80, 10);

        JButton buttonToday = new JButton("Today");
        JButton buttonPrev = new JButton("<");
        JButton buttonNext = new JButton(">");

        add(buttonToday);
        add(buttonPrev);
        add(buttonNext);

        buttonToday.addActionListener(event -> {
            // call get day method
            // model.goto method.
        });

        buttonPrev.addActionListener(event -> {
            int prevMonth = model.getCurrentMonth() - 1;
            calendarInfoPanel.textArea.setText(String.valueOf(months[prevMonth]));
        });

        buttonNext.addActionListener(event -> {
            int nextMonth = model.getCurrentMonth() + 1;

        });
    }
}
