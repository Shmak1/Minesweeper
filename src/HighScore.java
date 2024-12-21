import java.io.FileWriter;
import java.io.IOException;

public final class HighScore {
    private static int playerTime;
    private static String playerName = "Yes";
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

        //double score = (1_000.0 * numberOfTiles * numberOfMines) / playerTime;
        //double score = ((1_00 * numberOfMines) - numberOfTiles * 10) - (10 * playerTime); //TODO: test the alg more
        //double score = (1000.0 / (playerTime + 1)) + (10000.0 / (numberOfTiles + 1)) + (numberOfMines * numberOfMines);
        //double score = (1000.0 / (playerTime + 1)) + (10000.0 / ((numberOfTiles / 10.0) + 1)) + (numberOfMines * numberOfMines);
        //double score = (1000.0 / (playerTime + 1)) + (5000.0 / ((numberOfTiles / 5.0) + 1)) + (numberOfMines * numberOfMines);
        //double score = (1000.0 / (playerTime + 1)) + (3000.0 / ((numberOfTiles / 3.0) + 1)) + (2 * (numberOfMines * numberOfMines));
        double score = (1000.0 / (playerTime + 1)) + (2500.0 / ((numberOfTiles / 2.0) + 1)) + (2 * (numberOfMines * numberOfMines));

        //TODO: fix the act that when i accidentally flag something wrong the game becomes unwinnable

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
