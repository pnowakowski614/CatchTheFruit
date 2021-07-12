package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    static int Rozdzielczosc_X = 600, Rozdzielczosc_Y = 500;
    Licznik licznik = new Licznik();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setResizable(false);
        stage.setTitle("Catch The Fruit");
        stage.setScene(Menu.menu(stage));
        stage.show();
    }

    @Override
    public void stop() {
        licznik.timer.cancel();
    }

}
