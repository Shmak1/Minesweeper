import java.io.FileWriter;
import java.io.IOException;

public final class HighScore {
    private static int playerTime;
    private static String playerName = "Yes";
    private static final double baseScore = 999.0;
    private static double numberOfTiles;
    private static double numberOfMines;
    private static int intScore;

    private HighScore() {

    }

    public static void setPlayerTime(int time) {
        if (time <= 0) {
            playerTime = 1;
        } else if (time >= 999) {
            playerTime = 1000;
        } else {
            playerTime = 999 - time;
        }
    }

    public static void setPlayerName(String name) {
        playerName = name;
    }

    public static void setNumberOfTiles(int tiles) {
        numberOfTiles = tiles;
    }

    public static void setNumberOfMines(int mines) {
        numberOfMines = mines;
    }

    //returns 0 if the player took longer than 999 seconds to solve the game
    public static int getScore() { //TODO: sometimes if i lose on 0 time it just wont calculate any further score
        if (playerTime == 1000) {
            return 0;
        }

        double score = (1_000.0 * numberOfTiles * numberOfMines) / playerTime;

        intScore = (int) score;

        writeScoreToFile();
        return intScore;
    }

    public static void writeScoreToFile() {
        try {
            FileWriter writer = new FileWriter("scores.txt", true);
            writer.append(playerName + " " + intScore + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred when writing to the file");
        }
    }


}
