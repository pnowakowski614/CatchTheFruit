package sample;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Wyniki {
    public static Scene wyniki(Stage primaryStage) throws FileNotFoundException {

        Pane WynikiPane = new Pane();
        Text tytulwyniki = new Text(10, 20, "Ostatnie wyniki:");
        int j = 40;

        File openFile = new File("tablica.txt");
        Scanner FileScanner = new Scanner(openFile);

        while(FileScanner.hasNextLine()){
            String currentLine = FileScanner.nextLine();
            Text tekst = new Text(10, j, currentLine);
            WynikiPane.getChildren().add(tekst);
            j = j + 20;
        }

        WynikiPane.getChildren().add(tytulwyniki);

    return new Scene(WynikiPane, Main.Rozdzielczosc_X, Main.Rozdzielczosc_Y);
    }
}
