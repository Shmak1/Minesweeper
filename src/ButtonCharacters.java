import java.awt.*;

/** This enum stores Strings and Colors of all possible characters is the game. */
public enum ButtonCharacters {

    EMPTY("", Color.WHITE),
    BACKGROUND("", Color.decode("#7B7B7B")),
    FOREGROUND("", Color.decode("#7B7B7B")),
    ONE("1", Color.decode("#0000FF")),
    TWO("2", Color.decode("#007B00")),
    THREE("3", Color.decode("#FF0000")),
    FOUR("4", Color.decode("#00007B")),
    FIVE("5", Color.decode("#7B0000")),
    SIX("6", Color.decode("#007B7B")),
    SEVEN("7", Color.decode("#000000")),
    EIGHT("8", Color.decode("#7B7B7B")),
    FLAG("\uF04F", Color.decode("#080808")),
    BOMB("\uF04D", Color.decode("#0000FF"));

    private final String character;
    private final Color color;

    ButtonCharacters(String character, Color color) {
        this.character = character;
        this.color = color;
    }

    /** Method that returns a character. */
    public String getCharacter() {
        return this.character;
    }

    /** Method that returns a characters color. */
    public Color getColor() {
        return this.color;
    }
}
