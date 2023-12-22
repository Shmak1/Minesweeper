//TODO: timer
//      DONE right click capability with flags, make flags reversible
//      win condition //and win screen
//      reset method with R key
//      tile reveal algorithm
//      DONE lose condition and end screen/window
//      best time text file
//      flag counter
//
public class Main {

    public static void main(String[] args) {
        Window window = new Window(1920, 1080);//Vytvorenie okna
        window.bombPlacer();//Polozenie bomb na hraciu plochu
        window.numberPlacer();//Zapisanie cisel okolo bomb
        window.tileHider();//Skrytie vsetkych znakov, kontrolna metoda
        window.characterArrayer();//Ulozenie vsetkych znakov z tlacidiel do pola
        window.mouseListener();//Citanie vstupov mysou
        window.setUpGUI();//Nastavenie a zobrazenie okna
    }
}