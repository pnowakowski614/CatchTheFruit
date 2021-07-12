package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;

public class Gra {
    static int WielkoscMenu = 60;
    public static Rectangle kosz;

    public static Scene gra(Stage primaryStage) {
        Licznik licznik = new Licznik();
        Pane Gra = new Pane();

        // tło okna gry
        Rectangle TloMenu = new Rectangle(Main.Rozdzielczosc_X, WielkoscMenu, Color.BROWN);

        Image image = new Image(String.valueOf(Main.class.getResource("foto.jpg")));
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background2 = new Background(backgroundImage);

        Gra.setBackground(background2);

        Text punktytekst = new Text(10, 35, "Punkty: " + licznik.punkty);
        punktytekst.setFont(Font.font("Times New Roman", FontWeight.BLACK, 20));
        punktytekst.setFill(Color.BLACK);

        Text liczniktekst = new Text(350, 35, "Timer: " + licznik.sekundy);
        liczniktekst.setFont(Font.font("Times New Roman", FontWeight.BLACK, 20));
        liczniktekst.setFill(Color.BLACK);

        licznik.timer.scheduleAtFixedRate(licznik.licznikstart, 0, 100);
        AnimationTimer animacja = new AnimationTimer() {

            @Override
            public void handle(long arg0) {

                // obrazek kosza
                Image image2 = new Image(String.valueOf(Main.class.getResource("kosz.png")));
                ImagePattern imagePattern = new ImagePattern(image2);
                kosz.setFill(imagePattern);

                // update czasu i punktów
                liczniktekst.setText("Timer: " + licznik.sekundy);
                punktytekst.setText("Punkty: " + licznik.punkty);

                int jakiPrzedmiot = new Random().nextInt(6);
                int jegoPredkosc;
                Circle circle;

                // tworzenie przedmiotów, ustalanie ich parametrów
                if (licznik.puszczenie) {
                    switch (jakiPrzedmiot) {
                        case 0: //piorko
                            jegoPredkosc = 20;
                            circle = new Circle(new Random().nextInt(690), WielkoscMenu + 10, jegoPredkosc, Color.DARKGRAY);
                            licznik.circles.add(circle);
                            Gra.getChildren().add(circle);
                            Image pioro = new Image(String.valueOf(Main.class.getResource("pioro.png")));
                            ImagePattern pioroPattern = new ImagePattern(pioro);
                            circle.setFill(pioroPattern);
                            break;
                        case 1: //kowadlo
                            jegoPredkosc = 25;
                            circle = new Circle(new Random().nextInt(690), WielkoscMenu + 10, jegoPredkosc, Color.BLACK);
                            licznik.circles.add(circle);
                            Gra.getChildren().add(circle);
                            Image kowadlo = new Image(String.valueOf(Main.class.getResource("kowadlo.png")));
                            ImagePattern kowadloPattern = new ImagePattern(kowadlo);
                            circle.setFill(kowadloPattern);
                            break;
                        case 2: //jablko
                            jegoPredkosc = 18;
                            circle = new Circle(new Random().nextInt(690), WielkoscMenu + 10, jegoPredkosc, Color.RED);
                            licznik.circles.add(circle);
                            Gra.getChildren().add(circle);
                            Image jablko = new Image(String.valueOf(Main.class.getResource("apple.png")));
                            ImagePattern jablkoPattern = new ImagePattern(jablko);
                            circle.setFill(jablkoPattern);
                            break;
                        case 3: //gruszka
                            jegoPredkosc = 15;
                            circle = new Circle(new Random().nextInt(690), WielkoscMenu + 10, jegoPredkosc, Color.GREEN);
                            licznik.circles.add(circle);
                            Gra.getChildren().add(circle);
                            Image gruszka = new Image(String.valueOf(Main.class.getResource("gruszka.png")));
                            ImagePattern gruszkaPattern = new ImagePattern(gruszka);
                            circle.setFill(gruszkaPattern);
                            break;
                        case 4: //zgniłe jabłko
                            jegoPredkosc = 17;
                            circle = new Circle(new Random().nextInt(690), WielkoscMenu + 10, jegoPredkosc, Color.GREEN);
                            licznik.circles.add(circle);
                            Gra.getChildren().add(circle);
                            Image jablkozgnil = new Image(String.valueOf(Main.class.getResource("jablkozgnil.png")));
                            ImagePattern jablkozgnilPattern = new ImagePattern(jablkozgnil);
                            circle.setFill(jablkozgnilPattern);
                            break;
                        case 5: //zgniła gruszka
                            jegoPredkosc = 16;
                            circle = new Circle(new Random().nextInt(690), WielkoscMenu + 10, jegoPredkosc, Color.GREEN);
                            licznik.circles.add(circle);
                            Gra.getChildren().add(circle);
                            Image gruszkazgnil = new Image(String.valueOf(Main.class.getResource("gruszkazgnil.png")));
                            ImagePattern gruszkazgnilPattern = new ImagePattern(gruszkazgnil);
                            circle.setFill(gruszkazgnilPattern);
                            break;
                        default:
                            System.out.println("Wystąpił błąd");
                            break;
                    }
                }

                //spadanie i dostosowanie prędkości obiektów
                for (int i = 0; i < licznik.circles.size(); i++) {
                    var thisCircle = licznik.circles.get(i);
                    if(thisCircle.getRadius()==20){
                    thisCircle.setLayoutY(thisCircle.getLayoutY() + (thisCircle.getRadius()-15)/10 + thisCircle.getLayoutY() / 150);}

                    else if(thisCircle.getRadius()==18){
                        thisCircle.setLayoutY(thisCircle.getLayoutY() + (thisCircle.getRadius()-5)/10 + thisCircle.getLayoutY() / 150);}

                    else thisCircle.setLayoutY(thisCircle.getLayoutY() + thisCircle.getRadius()/10 + thisCircle.getLayoutY() / 150);

                    if (thisCircle.getLayoutY() > Main.Rozdzielczosc_Y) {
                        Gra.getChildren().remove(thisCircle);
                        licznik.circles.remove(i);
                    }

                    //zmiana stanu punktów w zależności od złapanego obiektu
                    else if (thisCircle.getLayoutY() >= Main.Rozdzielczosc_Y - 100 && thisCircle.getCenterX() - 32 < kosz.getX() &&
                    thisCircle.getCenterX() > kosz.getX()) {
                        switch ((int) thisCircle.getRadius()) {
                            case 20:
                            case 25:
                                licznik.punkty=0;
                                break;
                            case 18:
                            case 15:
                                licznik.punkty++;
                                break;

                            case 17:
                            case 16:
                                if (licznik.punkty <= 0) {
                                    licznik.punkty = 0;
                                }
                                else licznik.punkty--;
                                break;
                        }
                        Gra.getChildren().remove(thisCircle);
                        licznik.circles.remove(i);
                    }
                }
            }

        };
        animacja.start();

        //popup na koniec gry, ruszanie myszką
        Gra.setOnMouseMoved(e -> {
            if(Licznik.sekundy >= 60) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Koniec gry");
                alert.setHeaderText("Twój wynik to " + Licznik.punkty);
                Licznik.puszczenie = false;
                licznik.timer.cancel();
                animacja.stop();

                try {
                    FileWriter zapis = new FileWriter("tablica.txt", true);
                    zapis.write("\n");
                    zapis.write(String.valueOf(Licznik.punkty) + " pkt");
                    zapis.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


                ButtonType Wyjscie = new ButtonType("Wyjscie");
                ButtonType WynikiButton = new ButtonType("Ostatnie Wyniki");

                alert.getButtonTypes().setAll(Wyjscie, WynikiButton);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == Wyjscie) {
                    primaryStage.close();
                }
                else if (result.get() == WynikiButton) {
                    try {
                        primaryStage.setScene(Wyniki.wyniki(primaryStage));
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            kosz.setX(e.getX());
        });

        // ustawienie kosza w środku okna
        kosz = new Rectangle(50, 30);
        kosz.setX(Main.Rozdzielczosc_X / 2);
        kosz.setY(Main.Rozdzielczosc_Y - 30);

        Gra.getChildren().addAll(TloMenu, kosz, punktytekst, liczniktekst);
        return new Scene(Gra, Main.Rozdzielczosc_X, Main.Rozdzielczosc_Y);
    }
}
