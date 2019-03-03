import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) {
        //VARAIABLEN FÜR DIE GRÖßE
        int w = 300;
        int h = 250;

        //BUTTON + EVENT HANDLER BSP
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });


        //UHRZEIT GETTEN
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtft = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now)); //2016/11/16 12:08:43

        //TEXT ANZEIGEN
        Text uhrzeit = new Text();
        uhrzeit.setText("Uhrzeit: " + dtft.format(now));
        Text date = new Text();
        date.setText("Datum: " + dtf.format(now));

        //JAVAFX STARTEN
        StackPane root = new StackPane();
            root.getChildren().addAll(uhrzeit, date);
        Scene scene = new Scene(root, w, h);
        primaryStage.setTitle(dtf.format(now));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}