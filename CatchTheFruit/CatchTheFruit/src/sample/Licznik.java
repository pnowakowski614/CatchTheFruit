package sample;

import javafx.scene.shape.Circle;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Licznik {
    Timer timer = new Timer();
    int milisekundy;
    static int sekundy;
    static int punkty = 0;
    static boolean puszczenie = false;
    static ArrayList <Circle> circles = new ArrayList();

    //licznik czasu

    TimerTask licznikstart = new TimerTask() {
        @Override
        public void run() {
            puszczenie = false;
            milisekundy++;
            if (milisekundy % 10 == 0) {
                sekundy++;
                puszczenie = true;
                }
        }
    };
}
