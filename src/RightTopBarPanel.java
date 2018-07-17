import javax.swing.*;
import java.awt.*;

public class RightTopBarPanel extends JPanel
{
    private DataModel model;

    public RightTopBarPanel(DataModel m)
    {
        model = m;


        setLayout(new GridLayout(1, 5));
        setSize(50, 10);

        JButton buttonDay = new JButton("Day");
        JButton buttonWeek = new JButton("Week");
        JButton buttonMonth = new JButton("Month");
        JButton buttonAgenda = new JButton("Agenda");
        JButton buttonFromFile = new JButton("From File");

        add(buttonDay);
        add(buttonWeek);
        add(buttonMonth);
        add(buttonAgenda);
        add(buttonFromFile);

        buttonDay.addActionListener(event ->
        {
            // call get day method
            // model.goto method.
        });

        buttonWeek.addActionListener(event ->
        {

        });

        buttonMonth.addActionListener(event ->
        {

        });

        buttonAgenda.addActionListener(event ->
        {

        });

        buttonFromFile.addActionListener(event ->
        {
            // this filePath location should change to your own file location to test it out.
            String filePath = "/Users/sijiagao/IdeaProjects/Group Calendar/src/input.txt";
            model.readFromFile(filePath);

            // then should show the event being created in the TextArea
        });
    }
}
