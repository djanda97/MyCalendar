import javax.swing.*;
import java.awt.*;
import java.util.GregorianCalendar;

public class LeftTopBarPanel extends JPanel{
    private DataModel model;
    private JButton[] buttons;
    private JTextArea weekField;
    private JTextArea monthField;
    private JTextArea yearField;
    private GregorianCalendar cal;

    public LeftTopBarPanel()//DataModel m)
    {
//        setLayout(new GridLayout(1, 3));
        setLayout(new FlowLayout());
//        setSize(80, 10);

        JButton buttonToday = new JButton("Today");
        JButton buttonPrev = new JButton("<");
        JButton buttonNext = new JButton(">");

        add(buttonToday);
        add(buttonPrev);
        add(buttonNext);

        buttonToday.addActionListener(event->{
            // call get day method
            // model.goto method.
        });
    }
}
