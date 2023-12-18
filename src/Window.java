import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;



public class Window{
    private int windowWidth;
    private int windowHeight;
    private JFrame frame;
    private JButton[][] buttons;
    private int numberOfMines = 10;
    private int xCords = 9;
    private int yCords = 9;
    private boolean[][] bombPlacement;
    private String[][] characterPlacement;
    private boolean gameIsLost = false;


    public Window(int w, int h) {
        this.windowWidth = w;
        this.windowHeight = h;

        Dimension buttonSize = new Dimension(50, 50);

        this.buttons = new JButton[this.xCords][this.yCords];

        for (int x = 0; x < this.xCords; x++) {
            for (int y = 0; y < this.yCords; y++) {
                buttons[x][y] = new JButton("");
                buttons[x][y].setPreferredSize(buttonSize);
                buttons[x][y].setFont(new Font("Wingdings-Regular-Font.ttf", Font.BOLD, 14));
                buttons[x][y].setHorizontalAlignment(SwingConstants.CENTER);
                buttons[x][y].setHorizontalTextPosition(SwingConstants.CENTER);
            }
        }
    }

    public void bombPlacer() {
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

    public void numberPlacer() {
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
                String characterPlacement = this.characterPlacement[x][y];
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e) && gameIsLost == false) {
                            if (!button.getText().equals("\uF04F")) {
                                button.setText("\uF04F");
                                button.setForeground(Color.WHITE);
                            }else {
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
                            }
                        }

                        if (SwingUtilities.isLeftMouseButton(e) && !button.getText().equals("\uF04F") && gameIsLost == false) {
                            if (button.getText().isEmpty()) {
                                button.setBackground(Color.LIGHT_GRAY);
                            }
                            if (button.getText().equals("1")) {
                                button.setForeground(Color.CYAN);
                                button.setBackground(Color.LIGHT_GRAY);
                            }
                            if (button.getText().equals("2")) {
                                button.setForeground(Color.GREEN);
                                button.setBackground(Color.LIGHT_GRAY);
                            }
                            if (button.getText().equals("3")) {
                                button.setForeground(Color.RED);
                                button.setBackground(Color.LIGHT_GRAY);
                            }
                            if (button.getText().equals("4")) {
                                button.setForeground(Color.BLUE);
                                button.setBackground(Color.LIGHT_GRAY);
                            }
                            if (button.getText().equals("5")) {
                                button.setForeground(Color.PINK);
                                button.setBackground(Color.LIGHT_GRAY);
                            }
                            if (button.getText().equals("6")) {
                                button.setForeground(Color.MAGENTA);
                                button.setBackground(Color.LIGHT_GRAY);
                            }
                            if (button.getText().equals("7")) {
                                button.setForeground(Color.BLACK);
                                button.setBackground(Color.LIGHT_GRAY);
                            }
                            if (button.getText().equals("8")) {
                                button.setForeground(Color.DARK_GRAY);
                                button.setBackground(Color.LIGHT_GRAY);
                            }
                            if (button.getText().equals("\uF04D")) {
                                button.setBackground(Color.LIGHT_GRAY);
                                gameIsLost = true;
                                setUpLCGUI();



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

    public void setUpLCGUI(){
        LoseConditionWindow lcWindow = new LoseConditionWindow(250, 105);
        lcWindow.setUpGUI();
    }

}
