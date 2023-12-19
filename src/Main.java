//TODO: timer
//      DONE right click capability with flags, make flags reversible
//      win condition and win screen
//      reset method with R key
//      tile reveal algorithm
//      DONE lose condition and end screen/window
//      best time text file
//      flag counter
//
public class Main {

    public static void main(String[] args) {
        Window window = new Window(1920, 1080);
        window.bombPlacer();
        window.numberPlacer();
        window.tileHider();
        window.characterArrayer();
        window.mouseListener();
        window.setUpGUI();
    }
}