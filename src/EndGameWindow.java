import javax.swing.*;
import java.awt.*;

/** This class takes care of the end game window and its functions. */
public class EndGameWindow {
    private final int windowWidth;
    private final int windowHeight;
    private JFrame frame;
    private JTextArea textArea;
    private final String windowText;

    public EndGameWindow(int width, int height, String text) {
        this.windowWidth = width;
        this.windowHeight = height;
        this.windowText = text;
    }
    /** Method that creates a window with text, based on if the player has lost or won. */
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

        this.frame.add(this.textArea);

        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    /** Method that removes the window and releases all of its resources. */
    public void disposeFrame() {
        this.frame.dispose();
    }
}
