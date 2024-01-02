//TODO: add filter for textFields for numbers only

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartingWindow {
    private int windowWidth;
    private int windowHeight;
    private JFrame frame;
    private JTextField gameWidth;
    private JTextField gameHeight;
    private JTextField numberOfMines;
    private JLabel gameWidthLabel;
    private JLabel gameHeightLabel;
    private JLabel numberOfMinesLabel;
    private JButton button;
    private int gameWidthInt;
    private int gameHeightInt;
    private int numberOfMinesInt;

    StartingWindow(int width, int height) {
        this.windowWidth = width;
        this.windowHeight = height;

        this.gameWidthLabel = new JLabel("Number of rows");
        this.gameWidthLabel.setFont(new Font("Arial", Font.PLAIN, 15));

        this.gameHeightLabel = new JLabel("Number of columns");
        this.gameHeightLabel.setFont(new Font("Arial", Font.PLAIN, 15));

        this.numberOfMinesLabel = new JLabel("Number of mines");
        this.numberOfMinesLabel.setFont(new Font("Arial", Font.PLAIN, 15));

        final Dimension buttonSize = new Dimension(100, 50);

        this.button = new JButton("Start");
        this.button.setPreferredSize(buttonSize);
        this.button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        this.button.setFont(new Font("Arial", Font.BOLD, 14));
        this.button.setHorizontalAlignment(SwingConstants.CENTER);
        this.button.setHorizontalTextPosition(SwingConstants.CENTER);
        this.button.setFocusable(false);
    }

    public void setUpStartingWindow() {
        int maxCharacters = 3;
        this.gameWidth = new JTextField(2);
        this.gameWidth.setFont(new Font("Arial", Font.PLAIN, 15));
        this.gameWidth.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent a) {
                if (gameWidth.getText().length() >= maxCharacters) {
                    a.consume();
                }
            }
        });

        this.gameHeight = new JTextField(2);
        this.gameHeight.setFont(new Font("Arial", Font.PLAIN, 15));
        this.gameHeight.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent b) {
                if (gameHeight.getText().length() >= maxCharacters) {
                    b.consume();
                }
            }
        });

        this.numberOfMines = new JTextField(2);
        this.numberOfMines.setFont(new Font("Arial", Font.PLAIN, 15));
        this.numberOfMines.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent c) {
                if (numberOfMines.getText().length() >= maxCharacters) {
                    c.consume();
                }
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(2, 5, 2, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(this.gameWidth, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(this.gameWidthLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(this.gameHeight, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(this.gameHeightLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(this.numberOfMines, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(this.numberOfMinesLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(this.button, constraints);

        this.frame = new JFrame("Choose your game size");
        this.frame.setSize(this.windowWidth, this.windowHeight);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.getContentPane().add(panel);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setAlwaysOnTop(true);
        this.frame.setVisible(true);

        this.mouseListener();
    }

    public void mouseListener() {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    saveGameGenerationInput();

                    frame.dispose();

                    if (gameWidthInt < 4 || gameWidthInt > 50 || gameHeightInt < 4 || gameHeightInt > 50 || numberOfMinesInt > (gameWidthInt * gameHeightInt)){
                        gameWidthInt = 9;
                        gameHeightInt = 9;
                        numberOfMinesInt = 10;
                    }

                    Window window = new Window(1920, 1080, gameWidthInt, gameHeightInt, numberOfMinesInt);//Vytvorenie okna, teoreticke maximum: x, y, 200, 200, 4000; hratelne maximum: x, y, 50, 50, 500
                    window.bombPlacer();//Polozenie bomb na hraciu plochu
                    window.numberPlacer();//Zapisanie cisel okolo bomb
                    window.tileHider();//Skrytie vsetkych znakov, kontrolna metoda
                    window.characterArrayer();//Ulozenie vsetkych znakov z tlacidiel do pola
                    window.mouseListener();//Citanie vstupov mysou
                    window.setUpGUI();//Nastavenie a zobrazenie okna
                    window.keyboardListener();
                }
            }
        });
    }

    public void saveGameGenerationInput() {
        try {
            this.gameWidthInt = Integer.parseInt(this.gameWidth.getText());
        } catch (NumberFormatException e) {
            this.gameWidthInt = 9;
        }

        try {
            this.gameHeightInt = Integer.parseInt(this.gameHeight.getText());
        } catch (NumberFormatException e) {
            this.gameHeightInt = 9;
        }

        try {
            this.numberOfMinesInt = Integer.parseInt(this.numberOfMines.getText());
        } catch (NumberFormatException e) {
            this.numberOfMinesInt = 10;
        }
    }
}