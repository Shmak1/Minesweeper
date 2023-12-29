import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class Window{
    private int windowWidth;
    private int windowHeight;
    private JFrame frame;
    private JButton[][] buttons;
    private int numberOfMines;
    private int xCords;//pocet tlacidiel na vysku
    private int yCords;//pocet tlacidiel na sirku
    private boolean[][] bombPlacement;
    private boolean[][] flagPlacement;
    private String[][] characterPlacement;
    private boolean gameIsLost = false;

    public Window(int width, int height, int xCords, int yCords, int numberOfMines) {
        this.windowWidth = width;
        this.windowHeight = height;
        this.xCords = xCords;
        this.yCords = yCords;
        this.numberOfMines = numberOfMines;

        final Dimension buttonSize = new Dimension(50, 50);

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
    }

    public void bombPlacer() { //Metoda na nahodne rozmiestnenie min/bomb do JButtonov
        int randIntX;
        int randIntY;
        this.bombPlacement = new boolean[xCords][yCords];

        Random rand = new Random();

        for (int i = 0; i < this.numberOfMines; i++) {
            randIntX = rand.nextInt(this.xCords);
            randIntY = rand.nextInt(this.yCords);

            if (this.buttons[randIntX][randIntY].getText().isEmpty()) {
                this.buttons[randIntX][randIntY].setText("\uF04D");
                this.bombPlacement[randIntX][randIntY] = true;
            } else {
                i--;
            }
        }
    }

    public void numberPlacer() { //Metoda na rozmiestnenie cisel do JButtonov okolo JButtonov obsahujucich znak bomby
        int buttonNumber;
        String buttonText;

        for (int x = 0; x < this.xCords; x++) {
            for (int y = 0; y < this.yCords; y++) {
                if (this.bombPlacement[x][y]) {
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
                        if (!this.buttons[x - 1][y].getText().equals("\uF04D")) {   //if the button does NOT contain a bomb
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

    public void tileHider() {
        for (int x = 0; x < this.xCords; x++) {
            for (int y = 0; y < this.yCords; y++) {
                this.buttons[x][y].setForeground(Color.GRAY);//
                this.buttons[x][y].setBackground(Color.GRAY);//defaultne menit farbu
                this.buttons[x][y].setFocusPainted(false);
            }
        }
    }

    public void characterArrayer(){
        this.characterPlacement = new String[this.xCords][this.yCords];
        for (int x = 0; x < this.xCords; x++) {
            for (int y = 0; y < this.yCords; y++) {
                this.characterPlacement[x][y] = this.buttons[x][y].getText();
            }
        }
    }

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
        secondPanel.setPreferredSize(new Dimension((this.xCords*50), 70));

        Container cp = this.frame.getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(mainPanel, BorderLayout.CENTER);
        cp.add(secondPanel, BorderLayout.NORTH);

        this.frame.pack();
        this.frame.setResizable(false);
        //this.frame.validate();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    public void mouseListener() {
        for (int x = 0; x < this.xCords; x++) {
            for (int y = 0; y < this.yCords; y++) {
                JButton button = this.buttons[x][y];
                int ax = x;
                int ay = y;
                String characterPlacement = this.characterPlacement[x][y];
                //boolean bombPlacement = this.bombPlacement[x][y];
                //boolean flagPlacement = this.flagPlacement[x][y];
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        //boolean[][] flagPlacement = new boolean[9][9];
                        if (SwingUtilities.isRightMouseButton(e) && !gameIsLost) {
                            if (!button.getText().equals("\uF04F")) {
                                button.setText("\uF04F");
                                button.setForeground(Color.WHITE);
                                //flagPlacement[][] = true;
                            }else if(!button.getBackground().equals(Color.GRAY)){
                                button.setText(characterPlacement);

                                switch(characterPlacement){
                                    case "1": button.setForeground(ButtonCharacters.ONE.getColor());
                                        break;
                                    case "2": button.setForeground(ButtonCharacters.TWO.getColor());
                                        break;
                                    case "3": button.setForeground(ButtonCharacters.THREE.getColor());
                                        break;
                                    case "4": button.setForeground(ButtonCharacters.FOUR.getColor());
                                        break;
                                    case "5": button.setForeground(ButtonCharacters.FIVE.getColor());
                                        break;
                                    case "6": button.setForeground(ButtonCharacters.SIX.getColor());
                                        break;
                                    case "7": button.setForeground(ButtonCharacters.SEVEN.getColor());
                                        break;
                                    case "8": button.setForeground(ButtonCharacters.EIGHT.getColor());
                                        break;
                                    case "\uF04D": button.setForeground(ButtonCharacters.BOMB.getColor());
                                        break;
                                }

                            }else {
                                button.setText(characterPlacement);
                                button.setForeground(Color.GRAY);
                            }
                        }

                        if (SwingUtilities.isLeftMouseButton(e) && !button.getText().equals("\uF04F") && !gameIsLost) {
                            if (button.getText().isEmpty()) {//FIXME: zmenit na switch
                                tileRevealer(ax, ay);
                            }
                            if (characterPlacement.equals("1")) {
                                button.setForeground(ButtonCharacters.ONE.getColor());
                                button.setBackground(Color.LIGHT_GRAY);
                            }
                            if (characterPlacement.equals("2")) {
                                button.setForeground(ButtonCharacters.TWO.getColor());
                                button.setBackground(Color.LIGHT_GRAY);
                            }
                            if (characterPlacement.equals("3")) {
                                button.setForeground(ButtonCharacters.THREE.getColor());
                                button.setBackground(Color.LIGHT_GRAY);
                            }
                            if (characterPlacement.equals("4")) {
                                button.setForeground(ButtonCharacters.FOUR.getColor());
                                button.setBackground(Color.LIGHT_GRAY);
                            }
                            if (characterPlacement.equals("5")) {
                                button.setForeground(ButtonCharacters.FIVE.getColor());
                                button.setBackground(Color.LIGHT_GRAY);
                            }
                            if (characterPlacement.equals("6")) {
                                button.setForeground(ButtonCharacters.SIX.getColor());
                                button.setBackground(Color.LIGHT_GRAY);
                            }
                            if (characterPlacement.equals("7")) {
                                button.setForeground(ButtonCharacters.SEVEN.getColor());
                                button.setBackground(Color.LIGHT_GRAY);
                            }
                            if (characterPlacement.equals("8")) {
                                button.setForeground(ButtonCharacters.EIGHT.getColor());
                                button.setBackground(Color.LIGHT_GRAY);
                            }
                            if (characterPlacement.equals("\uF04D")) {
                                button.setBackground(Color.LIGHT_GRAY);
                                gameIsLost = true;
                                setUpLoseCGUI();

                                for (int xx = 0; xx < xCords; xx++) {
                                    for (int yy = 0; yy < yCords; yy++) {
                                        if (bombPlacement[xx][yy]) {
                                            buttons[xx][yy].setForeground(Color.BLACK);
                                            buttons[xx][yy].setBackground(Color.LIGHT_GRAY);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public void setUpLoseCGUI(){
        EndGameWindow loseWindow = new EndGameWindow(250, 105, "You lost");
        loseWindow.setUpGUI();
    }

    public void setUpWinGUI(){
        EndGameWindow winWindow = new EndGameWindow(250, 105, "You won");
        winWindow.setUpGUI();
    }

    public void tileRevealer(int x, int y){
        if ((this.buttons[x][y].getText().isEmpty()) && (!this.buttons[x][y].getBackground().equals(Color.LIGHT_GRAY))) {
            try {
                this.tileRevealer(x - 1, y - 1);
                buttons[x - 1][y - 1].setBackground(Color.LIGHT_GRAY);
            }catch (ArrayIndexOutOfBoundsException aa){
                //System.out.println("Error: " + aa.getMessage);
            }

            try {
                this.tileRevealer(x - 1, y);
                buttons[x - 1][y].setBackground(Color.LIGHT_GRAY);
            }catch (ArrayIndexOutOfBoundsException bb){
                //System.out.println("Error: " + bb.getMessage);
            }

            try {
                this.tileRevealer(x - 1, y + 1);
                buttons[x - 1][y + 1].setBackground(Color.LIGHT_GRAY);
            }catch (ArrayIndexOutOfBoundsException cc){
                //System.out.println("Error: " + cc.getMessage);
            }

            try {
                this.tileRevealer(x, y - 1);
                buttons[x][y - 1].setBackground(Color.LIGHT_GRAY);
            }catch (ArrayIndexOutOfBoundsException dd){
                //System.out.println("Error: " + dd.getMessage);
            }

            buttons[x][y].setBackground(Color.LIGHT_GRAY);
            ////////////////////////////

            try {
                this.tileRevealer(x, y + 1);
                buttons[x][y + 1].setBackground(Color.LIGHT_GRAY);
            }catch (ArrayIndexOutOfBoundsException ee){
                //System.out.println("Error: " + ee.getMessage);
            }

            try {
                this.tileRevealer(x + 1, y - 1);
                buttons[x + 1][y - 1].setBackground(Color.LIGHT_GRAY);
            }catch (ArrayIndexOutOfBoundsException ff){
                //System.out.println("Error: " + ff.getMessage);
            }

            try {
                this.tileRevealer(x + 1, y);
                buttons[x + 1][y].setBackground(Color.LIGHT_GRAY);
            }catch (ArrayIndexOutOfBoundsException gg){
                //System.out.println("Error: " + gg.getMessage);
            }

            try {
                this.tileRevealer(x + 1, y + 1);
                buttons[x + 1][y + 1].setBackground(Color.LIGHT_GRAY);
            }catch (ArrayIndexOutOfBoundsException hh){
                //System.out.println("Error: " + hh.getMessage);
            }
        }
    }
}
