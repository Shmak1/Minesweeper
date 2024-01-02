import javax.swing.*;
import java.awt.*;

public class EndGameWindow {
    private int windowWidth;
    private int windowHeight;
    private JFrame frame;
    private JTextArea textArea;
    private String windowText;

    public EndGameWindow(int width, int height, String text) {
        this.windowWidth = width;
        this.windowHeight = height;
        this.windowText = text;
    }

    public void setUpGUI() {
        this.frame = new JFrame();
        this.frame.setSize(this.windowWidth, this.windowHeight);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setUndecorated(true);
        this.frame.setAlwaysOnTop(true);

        this.textArea = new JTextArea("\n        " + this.windowText);
        this.textArea.setEditable(false);
        this.textArea.setFocusable(false);
        this.textArea.setFont(new Font("Arial", Font.BOLD, 30));

        this.frame.add(textArea);

        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    public void disposeFrame(){
        this.frame.dispose();
    }
}
