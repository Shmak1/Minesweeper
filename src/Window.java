import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Random;

/** This class takes care of the main game window and its functions. */
public class Window {
    private final int windowWidth;
    private final int windowHeight;
    private JFrame frame;
    private final JButton[][] buttons;    //[0][0] is the upper left corner
    private JTextArea mineCounterTextArea;
    private JTextArea timerTextArea;
    private Timer timer;
    private final int numberOfMines;
    private int numberOfRemainingMines;
    private int secondsPassed;
    private final int xCords; //number of buttons on the x-axis
    private final int yCords; //number of buttons on the y-axis
    private boolean[][] minePlacement;
    private final boolean[][] flagPlacement;
    private String[][] characterPlacement;
    private boolean gameIsFinished = false;
    private boolean timerHasBeenStarted = false;
    private boolean startingWindowCannotBeCreated = false;  //variable used to make sure that starting window can be created only once every reset
    private EndGameWindow loseWindow;
    private EndGameWindow winWindow;

    public Window(int width, int height, int xCords, int yCords, int numberOfMines) {
        this.windowWidth = width;
        this.windowHeight = height;
        this.xCords = xCords;
        this.yCords = yCords;
        this.numberOfMines = numberOfMines;
        this.numberOfRemainingMines = this.numberOfMines;
        this.secondsPassed = 0;

        final Dimension buttonSize = new Dimension(50, 50); //Size of a button, any lower and text stops displaying

        this.buttons = new JButton[this.xCords][this.yCords];
        this.flagPlacement = new boolean[this.xCords][this.yCords];

        for (int x = 0; x < this.xCords; x++) {
            for (int y = 0; y < this.yCords; y++) {
                this.buttons[x][y] = new JButton("");
                this.buttons[x][y].setPreferredSize(buttonSize);
                this.buttons[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                this.buttons[x][y].setFont(new Font("Wingdings-Regular-Font.ttf", Font.BOLD, 14));
                this.buttons[x][y].setHorizontalAlignment(SwingConstants.CENTER);
                this.buttons[x][y].setHorizontalTextPosition(SwingConstants.CENTER);
                this.flagPlacement[x][y] = false;
            }
        }

        HighScore.setNumberOfTiles(this.xCords * this.yCords);
        HighScore.setNumberOfMines(this.numberOfMines);
    }

    /** Method that randomly places selected number of mines inside the button grid */
    public void minePlacer() {
        int randIntX;
        int randIntY;
        this.minePlacement = new boolean[this.xCords][this.yCords];

        Random rand = new Random();

        for (int i = 0; i < this.numberOfMines; i++) {
            randIntX = rand.nextInt(this.xCords);
            randIntY = rand.nextInt(this.yCords);

            if (this.buttons[randIntX][randIntY].getText().isEmpty()) {
                this.buttons[randIntX][randIntY].setText("\uF04D");
                this.minePlacement[randIntX][randIntY] = true;
            } else {
                i--;
            }
        }
    }

    /** Method that places numbers inside buttons that have a mine/mines around them, it works by iterating through all the buttons
     *  and if the button coordinates match the minePlacement coordinates, it adds 1 to every tile around it that is NOT a mine,
     *  try catch blocks prevent the code from stepping out of the array. */
    public void numberPlacer() {
        int buttonNumber;
        String buttonText;

        for (int x = 0; x < this.xCords; x++) {
            for (int y = 0; y < this.yCords; y++) {
                if (this.minePlacement[x][y]) {
                    try {
                        if (!this.buttons[x - 1][y - 1].getText().equals("\uF04D")) { //if the button does NOT contain a bomb
                            if (this.buttons[x - 1][y - 1].getText().isEmpty()) {
                                this.buttons[x - 1][y - 1].setText("1");
                            } else {
                                buttonText = this.buttons[x - 1][y - 1].getText();
                                buttonNumber = Integer.parseInt(buttonText);
                                buttonNumber++;
                                this.buttons[x - 1][y - 1].setText(Integer.toString(buttonNumber));
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                        //System.out.println("Error: " + a.getMessage());
                    }

                    try {
                        if (!this.buttons[x - 1][y].getText().equals("\uF04D")) {
                            if (this.buttons[x - 1][y].getText().isEmpty()) {
                                this.buttons[x - 1][y].setText("1");
                            } else {
                                buttonText = this.buttons[x - 1][y].getText();
                                buttonNumber = Integer.parseInt(buttonText);
                                buttonNumber++;
                                this.buttons[x - 1][y].setText(Integer.toString(buttonNumber));
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException b) {
                        //System.out.println("Error: " + b.getMessage());
                    }

                    try {
                        if (!this.buttons[x - 1][y + 1].getText().equals("\uF04D")) {
                            if (this.buttons[x - 1][y + 1].getText().isEmpty()) {
                                this.buttons[x - 1][y + 1].setText("1");
                            } else {
                                buttonText = this.buttons[x - 1][y + 1].getText();
                                buttonNumber = Integer.parseInt(buttonText);
                                buttonNumber++;
                                this.buttons[x - 1][y + 1].setText(Integer.toString(buttonNumber));
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException c) {
                        //System.out.println("Error: " + c.getMessage());
                    }

                    try {
                        if (!this.buttons[x][y - 1].getText().equals("\uF04D")) {
                            if (this.buttons[x][y - 1].getText().isEmpty()) {
                                this.buttons[x][y - 1].setText("1");
                            } else {
                                buttonText = this.buttons[x][y - 1].getText();
                                buttonNumber = Integer.parseInt(buttonText);
                                buttonNumber++;
                                this.buttons[x][y - 1].setText(Integer.toString(buttonNumber));
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException d) {
                        //System.out.println("Error: " + d.getMessage());
                    }

                    try {
                        if (!this.buttons[x][y + 1].getText().equals("\uF04D")) {
                            if (this.buttons[x][y + 1].getText().isEmpty()) {
                                this.buttons[x][y + 1].setText("1");
                            } else {
                                buttonText = this.buttons[x][y + 1].getText();
                                buttonNumber = Integer.parseInt(buttonText);
                                buttonNumber++;
                                this.buttons[x][y + 1].setText(Integer.toString(buttonNumber));
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("Error: " + e.getMessage());
                    }

                    try {
                        if (!this.buttons[x + 1][y - 1].getText().equals("\uF04D")) {
                            if (this.buttons[x + 1][y - 1].getText().isEmpty()) {
                                this.buttons[x + 1][y - 1].setText("1");
                            } else {
                                buttonText = this.buttons[x + 1][y - 1].getText();
                                buttonNumber = Integer.parseInt(buttonText);
                                buttonNumber++;
                                this.buttons[x + 1][y - 1].setText(Integer.toString(buttonNumber));
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException f) {
                        //System.out.println("Error: " + f.getMessage());
                    }

                    try {
                        if (!this.buttons[x + 1][y].getText().equals("\uF04D")) {
                            if (this.buttons[x + 1][y].getText().isEmpty()) {
                                this.buttons[x + 1][y].setText("1");
                            } else {
                                buttonText = this.buttons[x + 1][y].getText();
                                buttonNumber = Integer.parseInt(buttonText);
                                buttonNumber++;
                                this.buttons[x + 1][y].setText(Integer.toString(buttonNumber));
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException g) {
                        //System.out.println("Error: " + g.getMessage());
                    }

                    try {
                        if (!this.buttons[x + 1][y + 1].getText().equals("\uF04D")) {
                            if (this.buttons[x + 1][y + 1].getText().isEmpty()) {
                                this.buttons[x + 1][y + 1].setText("1");
                            } else {
                                buttonText = this.buttons[x + 1][y + 1].getText();
                                buttonNumber = Integer.parseInt(buttonText);
                                buttonNumber++;
                                this.buttons[x + 1][y + 1].setText(Integer.toString(buttonNumber));
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException h) {
                        //System.out.println("Error: " + h.getMessage());
                    }
                }
            }
        }
    }

    /** Method that hides the text inside the tiles by making the FOREGROUND and the BACKGROUND the same color. */
    public void tileHider() {
        for (int x = 0; x < this.xCords; x++) {
            for (int y = 0; y < this.yCords; y++) {
                this.buttons[x][y].setForeground(ButtonCharacters.FOREGROUND.getColor());
                this.buttons[x][y].setBackground(ButtonCharacters.BACKGROUND.getColor());
                this.buttons[x][y].setFocusPainted(false);
            }
        }
    }

    public void characterArrayer() {    //Method that puts all the characters inside the tiles into an array.
        this.characterPlacement = new String[this.xCords][this.yCords];
        for (int x = 0; x < this.xCords; x++) {
            for (int y = 0; y < this.yCords; y++) {
                this.characterPlacement[x][y] = this.buttons[x][y].getText();
            }
        }
    }

    /** Method that puts all the characters inside the tiles into an array. */
    public void setUpGUI() {
        this.frame = new JFrame();
        this.frame.setSize(this.windowWidth, this.windowHeight);
        this.frame.setTitle("Minesweeper");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(this.xCords, this.yCords));

        for (int x = 0; x < this.xCords; x++) {
            for (int y = 0; y < this.yCords; y++) {
                mainPanel.add(this.buttons[x][y]);
            }
        }

        JPanel secondPanel = new JPanel();
        secondPanel.setPreferredSize(new Dimension((this.xCords * 50), 70));
        secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.X_AXIS));

        Container cp = this.frame.getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(mainPanel, BorderLayout.CENTER);
        cp.add(secondPanel, BorderLayout.NORTH);

        this.mineCounterTextArea = new JTextArea(String.valueOf(this.numberOfRemainingMines));
        this.mineCounterTextArea.setEditable(false);
        this.mineCounterTextArea.setFocusable(false);
        this.mineCounterTextArea.setFont(new Font("Arial", Font.BOLD, 30));
        this.mineCounterTextArea.setBackground(secondPanel.getBackground());
        Border mineCounterEmptyBorder = BorderFactory.createEmptyBorder(17, 25, 0, 0);
        this.mineCounterTextArea.setBorder(mineCounterEmptyBorder);
        secondPanel.add(this.mineCounterTextArea);

        this.timerTextArea = new JTextArea(String.valueOf(0)); //FIXME: emancipate timer from mineCounter
        this.timerTextArea.setEditable(false);
        this.timerTextArea.setFocusable(false);
        this.timerTextArea.setFont(new Font("Arial", Font.BOLD, 30));
        this.timerTextArea.setBackground(secondPanel.getBackground());
        Border timerEmptyBorder = BorderFactory.createEmptyBorder(17, this.xCords * 50 - 160, 0, 0);
        this.timerTextArea.setBorder(timerEmptyBorder);
        secondPanel.add(this.timerTextArea);

        this.frame.pack();
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    /** Method that updates the mineCounter textArea every time a flag is placed or picked up. */
    public void updateMineCounter(boolean plusOrMinus) {
        if (plusOrMinus) {
            this.numberOfRemainingMines++;
            this.mineCounterTextArea.setText(String.valueOf(this.numberOfRemainingMines));
        } else {
            this.numberOfRemainingMines--;
            this.mineCounterTextArea.setText(String.valueOf(this.numberOfRemainingMines));
        }
    }

    /** Method that counts from 0 to 999 seconds from the first interaction to the game end. */
    public void timer() {
        int delay = 1000; //milliseconds
        this.timer = new Timer(delay, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent z) {
                if (Window.this.secondsPassed >= 999 || Window.this.gameIsFinished) {
                    Window.this.timer.stop();
                } else {
                    Window.this.secondsPassed++;
                    Window.this.updateTimer(Window.this.secondsPassed);
                }
            }
        });
        Window.this.timer.start();
    }

    /** Method that updates the timer textArea every second */
    public void updateTimer(int secondsPassed) {
        Window.this.timerTextArea.setText(String.valueOf(secondsPassed));
    }

    /** Method that waits for the player to press the 'R' key. When the key is pressed, it calls the resetter() method that resets the game. */
    public void keyboardListener() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_R && !Window.this.startingWindowCannotBeCreated) {
                    Window.this.resetter();
                    return true;
                } else {
                    Window.this.startingWindowCannotBeCreated = true;
                    return false;
                }
            }
        });
    }

    /** Method that allows the player to interact with the buttons by left/right-clicking and afterward takes care of handling the changes accompanying the interaction. */
    public void mouseListener() {
        for (int x = 0; x < this.xCords; x++) {
            for (int y = 0; y < this.yCords; y++) {
                int ax = x;
                int ay = y;
                Window.this.buttons[ax][ay].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e) && !Window.this.gameIsFinished) {  //right-click
                            if (!Window.this.buttons[ax][ay].getText().equals("\uF04F") && Window.this.buttons[ax][ay].getBackground().equals(ButtonCharacters.BACKGROUND.getColor())) {  //right-click when there is no flag placed
                                Window.this.buttons[ax][ay].setText(ButtonCharacters.FLAG.getCharacter());
                                Window.this.buttons[ax][ay].setForeground(ButtonCharacters.FLAG.getColor());

                                Window.this.updateMineCounter(false);

                                Window.this.flagPlacement[ax][ay] = true;

                                if (Arrays.deepEquals(Window.this.flagPlacement, Window.this.minePlacement)) {
                                    Window.this.setUpWinGUI();
                                    Window.this.gameIsFinished = true;
                                }

                            } else if (!Window.this.buttons[ax][ay].getBackground().equals(ButtonCharacters.BACKGROUND.getColor())) { //right-click when there is already a flag, but there is a character under it
                                if (Window.this.buttons[ax][ay].getText().equals("\uF04F")) {
                                    Window.this.updateMineCounter(true);
                                }
                                Window.this.buttons[ax][ay].setText(Window.this.characterPlacement[ax][ay]);

                                Window.this.flagPlacement[ax][ay] = false;
                            } else {
                                Window.this.buttons[ax][ay].setText(Window.this.characterPlacement[ax][ay]);
                                Window.this.buttons[ax][ay].setForeground(ButtonCharacters.FOREGROUND.getColor());
                                Window.this.updateMineCounter(true);
                            }
                        }

                        if (SwingUtilities.isLeftMouseButton(e) && !Window.this.buttons[ax][ay].getText().equals("\uF04F") && !Window.this.gameIsFinished) { //left-click
                            if (!Window.this.timerHasBeenStarted) {
                                Window.this.timer();
                                Window.this.timerHasBeenStarted = true;
                            }

                            if (Window.this.buttons[ax][ay].getText().isEmpty()) {
                                Window.this.tileRevealer(ax, ay);
                            } else {
                                switch (Window.this.characterPlacement[ax][ay]) {
                                    case "1":   Window.this.buttons[ax][ay].setForeground(ButtonCharacters.ONE.getColor());
                                                Window.this.buttons[ax][ay].setBackground(Color.LIGHT_GRAY);
                                        break;
                                    case "2":   Window.this.buttons[ax][ay].setForeground(ButtonCharacters.TWO.getColor());
                                                Window.this.buttons[ax][ay].setBackground(Color.LIGHT_GRAY);
                                        break;
                                    case "3":   Window.this.buttons[ax][ay].setForeground(ButtonCharacters.THREE.getColor());
                                                Window.this.buttons[ax][ay].setBackground(Color.LIGHT_GRAY);
                                        break;
                                    case "4":   Window.this.buttons[ax][ay].setForeground(ButtonCharacters.FOUR.getColor());
                                                Window.this.buttons[ax][ay].setBackground(Color.LIGHT_GRAY);
                                        break;
                                    case "5":   Window.this.buttons[ax][ay].setForeground(ButtonCharacters.FIVE.getColor());
                                                Window.this.buttons[ax][ay].setBackground(Color.LIGHT_GRAY);
                                        break;
                                    case "6":   Window.this.buttons[ax][ay].setForeground(ButtonCharacters.SIX.getColor());
                                                Window.this.buttons[ax][ay].setBackground(Color.LIGHT_GRAY);
                                        break;
                                    case "7":   Window.this.buttons[ax][ay].setForeground(ButtonCharacters.SEVEN.getColor());
                                                Window.this.buttons[ax][ay].setBackground(Color.LIGHT_GRAY);
                                        break;
                                    case "8":   Window.this.buttons[ax][ay].setForeground(ButtonCharacters.EIGHT.getColor());
                                                Window.this.buttons[ax][ay].setBackground(Color.LIGHT_GRAY);
                                        break;
                                    case "\uF04D":  Window.this.buttons[ax][ay].setBackground(Color.LIGHT_GRAY);
                                                    Window.this.gameIsFinished = true;
                                                    Window.this.setUpLoseCGUI();

                                                    for (int xx = 0; xx < Window.this.xCords; xx++) {
                                                        for (int yy = 0; yy < Window.this.yCords; yy++) {
                                                            if (Window.this.minePlacement[xx][yy]) {
                                                                Window.this.buttons[xx][yy].setForeground(Color.BLACK);
                                                                Window.this.buttons[xx][yy].setBackground(Color.LIGHT_GRAY);
                                                            }
                                                        }
                                                    }
                                        break;
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    /** Method that creates a window after the player has clicked on a mine */
    public void setUpLoseCGUI() {
        this.frame.setTitle("Press R to restart");
        this.loseWindow = new EndGameWindow(250, 105, "You lost");
        this.loseWindow.setUpGUI();
    }

    /** Method that creates a window after the player has placed all the flags on the correctly */
    public void setUpWinGUI() {
        this.frame.setTitle("Press R to restart");
        this.winWindow = new EndGameWindow(250, 105, "You won");
        this.winWindow.setUpGUI();
        HighScore.setPlayerTime(this.secondsPassed);
        HighScore.getScore();
    }

    /** Method that reveals empty tiles and one tile further. It works by going around the clicked button and revealing all the empty tiles and afterward recursively
     *   calling itself 2 tiles away from the original tile in 8 directions(↑,↓,←,→,↖,↗,↘,↙). The try-catch blocks are there so that the method does NOT step out of the array.
     *   For further reference look at "Stuff/bomb count alg.txt" */
    public void tileRevealer(int x, int y) {
        if ((this.buttons[x][y].getText().isEmpty()) && (!this.buttons[x][y].getBackground().equals(Color.LIGHT_GRAY))) {
            try {
                this.tileRevealer(x - 1, y - 1);
                this.buttons[x - 1][y - 1].setBackground(Color.LIGHT_GRAY);
                switch (this.buttons[x - 1][y - 1].getText()) {
                    case "1": this.buttons[x - 1][y - 1].setForeground(ButtonCharacters.ONE.getColor());
                        break;
                    case "2": this.buttons[x - 1][y - 1].setForeground(ButtonCharacters.TWO.getColor());
                        break;
                    case "3": this.buttons[x - 1][y - 1].setForeground(ButtonCharacters.THREE.getColor());
                        break;
                    case "4": this.buttons[x - 1][y - 1].setForeground(ButtonCharacters.FOUR.getColor());
                        break;
                    case "5": this.buttons[x - 1][y - 1].setForeground(ButtonCharacters.FIVE.getColor());
                        break;
                    case "6": this.buttons[x - 1][y - 1].setForeground(ButtonCharacters.SIX.getColor());
                        break;
                    case "7": this.buttons[x - 1][y - 1].setForeground(ButtonCharacters.SEVEN.getColor());
                        break;
                    case "8": this.buttons[x - 1][y - 1].setForeground(ButtonCharacters.EIGHT.getColor());
                        break;
                    default: break;
                }

            } catch (ArrayIndexOutOfBoundsException aa) {
                //System.out.println("Error: " + aa.getMessage);
            }

            try {
                this.tileRevealer(x - 1, y);
                this.buttons[x - 1][y].setBackground(Color.LIGHT_GRAY);
                switch (this.characterPlacement[x - 1][y]) {
                    case "1": this.buttons[x - 1][y].setForeground(ButtonCharacters.ONE.getColor());
                        break;
                    case "2": this.buttons[x - 1][y].setForeground(ButtonCharacters.TWO.getColor());
                        break;
                    case "3": this.buttons[x - 1][y].setForeground(ButtonCharacters.THREE.getColor());
                        break;
                    case "4": this.buttons[x - 1][y].setForeground(ButtonCharacters.FOUR.getColor());
                        break;
                    case "5": this.buttons[x - 1][y].setForeground(ButtonCharacters.FIVE.getColor());
                        break;
                    case "6": this.buttons[x - 1][y].setForeground(ButtonCharacters.SIX.getColor());
                        break;
                    case "7": this.buttons[x - 1][y].setForeground(ButtonCharacters.SEVEN.getColor());
                        break;
                    case "8": this.buttons[x - 1][y].setForeground(ButtonCharacters.EIGHT.getColor());
                        break;
                    default: break;
                }
            } catch (ArrayIndexOutOfBoundsException bb) {
                //System.out.println("Error: " + bb.getMessage);
            }

            try {
                this.tileRevealer(x - 1, y + 1);
                this.buttons[x - 1][y + 1].setBackground(Color.LIGHT_GRAY);
                switch (this.characterPlacement[x - 1][y + 1]) {
                    case "1": this.buttons[x - 1][y + 1].setForeground(ButtonCharacters.ONE.getColor());
                        break;
                    case "2": this.buttons[x - 1][y + 1].setForeground(ButtonCharacters.TWO.getColor());
                        break;
                    case "3": this.buttons[x - 1][y + 1].setForeground(ButtonCharacters.THREE.getColor());
                        break;
                    case "4": this.buttons[x - 1][y + 1].setForeground(ButtonCharacters.FOUR.getColor());
                        break;
                    case "5": this.buttons[x - 1][y + 1].setForeground(ButtonCharacters.FIVE.getColor());
                        break;
                    case "6": this.buttons[x - 1][y + 1].setForeground(ButtonCharacters.SIX.getColor());
                        break;
                    case "7": this.buttons[x - 1][y + 1].setForeground(ButtonCharacters.SEVEN.getColor());
                        break;
                    case "8": this.buttons[x - 1][y + 1].setForeground(ButtonCharacters.EIGHT.getColor());
                        break;
                    default: break;
                }
            } catch (ArrayIndexOutOfBoundsException cc) {
                //System.out.println("Error: " + cc.getMessage);
            }

            try {
                this.tileRevealer(x, y - 1);
                this.buttons[x][y - 1].setBackground(Color.LIGHT_GRAY);
                switch (this.characterPlacement[x][y - 1]) {
                    case "1": this.buttons[x][y - 1].setForeground(ButtonCharacters.ONE.getColor());
                        break;
                    case "2": this.buttons[x][y - 1].setForeground(ButtonCharacters.TWO.getColor());
                        break;
                    case "3": this.buttons[x][y - 1].setForeground(ButtonCharacters.THREE.getColor());
                        break;
                    case "4": this.buttons[x][y - 1].setForeground(ButtonCharacters.FOUR.getColor());
                        break;
                    case "5": this.buttons[x][y - 1].setForeground(ButtonCharacters.FIVE.getColor());
                        break;
                    case "6": this.buttons[x][y - 1].setForeground(ButtonCharacters.SIX.getColor());
                        break;
                    case "7": this.buttons[x][y - 1].setForeground(ButtonCharacters.SEVEN.getColor());
                        break;
                    case "8": this.buttons[x][y - 1].setForeground(ButtonCharacters.EIGHT.getColor());
                        break;
                    default: break;
                }
            } catch (ArrayIndexOutOfBoundsException dd) {
                //System.out.println("Error: " + dd.getMessage);
            }

            this.buttons[x][y].setBackground(Color.LIGHT_GRAY);

            try {
                this.tileRevealer(x, y + 1);
                this.buttons[x][y + 1].setBackground(Color.LIGHT_GRAY);
                switch (this.characterPlacement[x][y + 1]) {
                    case "1": this.buttons[x][y + 1].setForeground(ButtonCharacters.ONE.getColor());
                        break;
                    case "2": this.buttons[x][y + 1].setForeground(ButtonCharacters.TWO.getColor());
                        break;
                    case "3": this.buttons[x][y + 1].setForeground(ButtonCharacters.THREE.getColor());
                        break;
                    case "4": this.buttons[x][y + 1].setForeground(ButtonCharacters.FOUR.getColor());
                        break;
                    case "5": this.buttons[x][y + 1].setForeground(ButtonCharacters.FIVE.getColor());
                        break;
                    case "6": this.buttons[x][y + 1].setForeground(ButtonCharacters.SIX.getColor());
                        break;
                    case "7": this.buttons[x][y + 1].setForeground(ButtonCharacters.SEVEN.getColor());
                        break;
                    case "8": this.buttons[x][y + 1].setForeground(ButtonCharacters.EIGHT.getColor());
                        break;
                    default: break;
                }
            } catch (ArrayIndexOutOfBoundsException ee) {
                //System.out.println("Error: " + ee.getMessage);
            }

            try {
                this.tileRevealer(x + 1, y - 1);
                this.buttons[x + 1][y - 1].setBackground(Color.LIGHT_GRAY);
                switch (this.characterPlacement[x + 1][y - 1]) {
                    case "1": this.buttons[x + 1][y - 1].setForeground(ButtonCharacters.ONE.getColor());
                        break;
                    case "2": this.buttons[x + 1][y - 1].setForeground(ButtonCharacters.TWO.getColor());
                        break;
                    case "3": this.buttons[x + 1][y - 1].setForeground(ButtonCharacters.THREE.getColor());
                        break;
                    case "4": this.buttons[x + 1][y - 1].setForeground(ButtonCharacters.FOUR.getColor());
                        break;
                    case "5": this.buttons[x + 1][y - 1].setForeground(ButtonCharacters.FIVE.getColor());
                        break;
                    case "6": this.buttons[x + 1][y - 1].setForeground(ButtonCharacters.SIX.getColor());
                        break;
                    case "7": this.buttons[x + 1][y - 1].setForeground(ButtonCharacters.SEVEN.getColor());
                        break;
                    case "8": this.buttons[x + 1][y - 1].setForeground(ButtonCharacters.EIGHT.getColor());
                        break;
                    default: break;
                }
            } catch (ArrayIndexOutOfBoundsException ff) {
                //System.out.println("Error: " + ff.getMessage);
            }

            try {
                this.tileRevealer(x + 1, y);
                this.buttons[x + 1][y].setBackground(Color.LIGHT_GRAY);
                switch (this.characterPlacement[x + 1][y]) {
                    case "1": this.buttons[x + 1][y].setForeground(ButtonCharacters.ONE.getColor());
                        break;
                    case "2": this.buttons[x + 1][y].setForeground(ButtonCharacters.TWO.getColor());
                        break;
                    case "3": this.buttons[x + 1][y].setForeground(ButtonCharacters.THREE.getColor());
                        break;
                    case "4": this.buttons[x + 1][y].setForeground(ButtonCharacters.FOUR.getColor());
                        break;
                    case "5": this.buttons[x + 1][y].setForeground(ButtonCharacters.FIVE.getColor());
                        break;
                    case "6": this.buttons[x + 1][y].setForeground(ButtonCharacters.SIX.getColor());
                        break;
                    case "7": this.buttons[x + 1][y].setForeground(ButtonCharacters.SEVEN.getColor());
                        break;
                    case "8": this.buttons[x + 1][y].setForeground(ButtonCharacters.EIGHT.getColor());
                        break;
                    default: break;
                }
            } catch (ArrayIndexOutOfBoundsException gg) {
                //System.out.println("Error: " + gg.getMessage);
            }

            try {
                this.tileRevealer(x + 1, y + 1);
                this.buttons[x + 1][y + 1].setBackground(Color.LIGHT_GRAY);
                switch (this.characterPlacement[x + 1][y + 1]) {
                    case "1": this.buttons[x + 1][y + 1].setForeground(ButtonCharacters.ONE.getColor());
                        break;
                    case "2": this.buttons[x + 1][y + 1].setForeground(ButtonCharacters.TWO.getColor());
                        break;
                    case "3": this.buttons[x + 1][y + 1].setForeground(ButtonCharacters.THREE.getColor());
                        break;
                    case "4": this.buttons[x + 1][y + 1].setForeground(ButtonCharacters.FOUR.getColor());
                        break;
                    case "5": this.buttons[x + 1][y + 1].setForeground(ButtonCharacters.FIVE.getColor());
                        break;
                    case "6": this.buttons[x + 1][y + 1].setForeground(ButtonCharacters.SIX.getColor());
                        break;
                    case "7": this.buttons[x + 1][y + 1].setForeground(ButtonCharacters.SEVEN.getColor());
                        break;
                    case "8": this.buttons[x + 1][y + 1].setForeground(ButtonCharacters.EIGHT.getColor());
                        break;
                    default: break;
                }
            } catch (ArrayIndexOutOfBoundsException hh) {
                //System.out.println("Error: " + hh.getMessage);
            }
        }
    }

    /** Method that deletes all the windows and releases their resources. Afterward it calls main so that the whole program can start again. */
    public void resetter() {
        this.frame.dispose();

        try {
            this.loseWindow.disposeFrame();
        } catch (NullPointerException ignored) {

        }

        try {
            this.winWindow.disposeFrame();
        } catch (NullPointerException ignored) {

        }

        this.startingWindowCannotBeCreated = false;

        Main.main(new String[]{});
    }
}