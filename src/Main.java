//TODO: FIX reset method with R key
//      best time text file

public class Main {

    public static void main(String[] args) {
        Window window = new Window(1920, 1080, 9, 9, 10);//Vytvorenie okna, teoreticke maximum: x, y, 200, 200, 4000; hratelne maximum: x, y, 50, 50, 500
        window.bombPlacer();//Polozenie bomb na hraciu plochu
        window.numberPlacer();//Zapisanie cisel okolo bomb
        window.tileHider();//Skrytie vsetkych znakov, kontrolna metoda
        window.characterArrayer();//Ulozenie vsetkych znakov z tlacidiel do pola
        window.mouseListener();//Citanie vstupov mysou

        window.setUpGUI();//Nastavenie a zobrazenie okna
        window.keyboardListener();
    }
}