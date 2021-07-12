package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Menu {
    public static Scene menu(Stage primaryStage) {

        // obraz tła

        Image image = new Image(String.valueOf(Main.class.getResource("backmenu.jpg")));
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        // czcionka do tytułu

        String currentFontFile = "deflore.ttf";
        InputStream fontStream = Main.class.getResourceAsStream(currentFontFile);
        Font bgFont = Font.loadFont(fontStream, 50);

        // tworzenie menu

        BorderPane MenuPane = new BorderPane();
        MenuPane.setBackground(background);

        Label tytul = new Label("CATCH THE FRUIT");
        tytul.setFont(bgFont);

        // zmiana rozdzielczości przy rozpoczęciu nowej gry

        Button NowaGra = new Button("Nowa Gra");
        NowaGra.setOnAction(e -> {Main.Rozdzielczosc_X = 450; Main.Rozdzielczosc_Y = 690; primaryStage.setScene(Gra.gra(primaryStage));});

        Button Wynik = new Button("Ostatnie Wyniki");
        Wynik.setOnAction(e -> {
            try {
                primaryStage.setScene(Wyniki.wyniki(primaryStage));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        Button Wyjscie = new Button("Wyjscie");
        Wyjscie.setOnAction(e -> primaryStage.close());

        VBox menuVbox = new VBox();
        menuVbox.setSpacing(20);

        menuVbox.getChildren().addAll(tytul, NowaGra, Wynik, Wyjscie);
        menuVbox.setAlignment(Pos.CENTER);
        MenuPane.setCenter(menuVbox);

        return new Scene(MenuPane, Main.Rozdzielczosc_X, Main.Rozdzielczosc_Y);
    }
}
