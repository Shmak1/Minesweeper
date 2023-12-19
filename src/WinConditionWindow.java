import javax.swing.*;
import java.awt.*;

public class WinConditionWindow {
    private int windowWidth;
    private int windowHeight;
    private JFrame frame;
    private JTextArea textArea;
    private boolean hasAlreadyBeenSetUp = false;

    public WinConditionWindow(int w, int h){
            this.windowWidth = w;
            this.windowHeight = h;
    }

    public void setUpGUI() {
        frame = new JFrame();
        frame.setSize(this.windowWidth, this.windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);

        textArea = new JTextArea("\n        You won");
        textArea.setEditable(false);
        //textArea.setSize(this.windowWidth, this.windowHeight);
        textArea.setFocusable(false);
        textArea.setFont(new Font("Arial", Font.BOLD, 30));

        frame.add(textArea);

        //frame.pack();
        frame.setResizable(false);
        //frame.validate();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
