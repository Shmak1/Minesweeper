//TODO: timer
//      DONE right click capability with flags, make flags reversible
//      win condition //and win screen
//      reset method with R key
//      DONE tile reveal algorithm
//      DONE lose condition and end screen/window
//      best time text file
//      flag counter
//      zrusit oramovanie textu v tlacidlach po pravom kliknuti a drzani left clicku
public class Main {

    public static void main(String[] args) {
        Window window = new Window(1920, 1080, 9, 9, 10);//Vytvorenie okna, teoreticke maximum: x, y, 200, 200, 4000; hratelne maximum: x, y, 50, 50, 500
        window.bombPlacer();//Polozenie bomb na hraciu plochu
        window.numberPlacer();//Zapisanie cisel okolo bomb
        window.tileHider();//Skrytie vsetkych znakov, kontrolna metoda
        window.characterArrayer();//Ulozenie vsetkych znakov z tlacidiel do pola
        window.mouseListener();//Citanie vstupov mysou
        window.setUpGUI();//Nastavenie a zobrazenie okna
    }
}