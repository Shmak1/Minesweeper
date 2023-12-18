import java.awt.*;

public enum ButtonCharacters {
//    ONE("1", Color.CYAN),
//    TWO("2", Color.GREEN),
//    THREE("3", Color.RED),
//    FOUR("4", Color.BLUE),
//    FIVE("5", Color.PINK),
//    SIX("6", Color.MAGENTA),
//    SEVEN("7", Color.BLACK),
//    EIGHT("8", Color.DARK_GRAY),
//    FLAG("\uF04F", Color.WHITE),
//    BOMB("\uF04D", Color.GRAY);

    ONE("1", Color.GRAY),
    TWO("2", Color.GRAY),
    THREE("3", Color.GRAY),
    FOUR("4", Color.GRAY),
    FIVE("5", Color.GRAY),
    SIX("6", Color.GRAY),
    SEVEN("7", Color.GRAY),
    EIGHT("8", Color.GRAY),
    FLAG("\uF04F", Color.GRAY),
    BOMB("\uF04D", Color.GRAY);

    private String character;
    private Color color;

    ButtonCharacters(String character, Color color){
        this.character = character;
        this.color = color;
    }

    public String getCharacter(){
        return this.character;
    }

    public Color getColor(){
        return this.color;
    }
}
