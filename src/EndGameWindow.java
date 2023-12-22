import javax.swing.*;
import java.awt.*;

public class EndGameWindow {
    private int windowWidth;
    private int windowHeight;
    private JFrame frame;
    private JTextArea textArea;
    private boolean hasAlreadyBeenSetUp = false;
    private String windowText;

    public EndGameWindow(int w, int h, String text){
        this.windowWidth = w;
        this.windowHeight = h;
        this.windowText = text;
    }

    public void setUpGUI() {
        frame = new JFrame();
        frame.setSize(this.windowWidth, this.windowHeight);
        //TODO: DONE zmazat java ikonku v rohu
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);

        //textArea = new JTextArea("\n        You lost");
        textArea = new JTextArea("\n        " + this.windowText);
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
