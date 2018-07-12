import javax.swing.*;

public class TextPanel extends JPanel
{
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private static final int ROWS = 25;
    private static final int COLUMNS = 25;

    public TextPanel()
    {
        textArea = new JTextArea(ROWS, COLUMNS);
        scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
    }


}
