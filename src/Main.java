import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main extends Application {
    //VARAIABLEN FÜR DIE GRÖßE
    final int w = 600;
    final int h = 500;

    //UHRZEIT GETTEN
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    DateTimeFormatter dtft = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void start(Stage primaryStage) {
        //GRID ERSTELLEN
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));

        //WATCH
            //titels
            Text titleUhr = new Text("Watch");
            titleUhr.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
            Text titleUhrzeit = new Text("Current time: ");
            titleUhrzeit.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
            Text titleDate = new Text("Date: ");
            titleDate.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));

            grid.add(titleUhr, 0, 0, 4, 1);
            grid.add(titleUhrzeit, 0, 1);
            grid.add(titleDate, 0, 2);

            //times
            Text currentTime = new Text(dtft.format(LocalDateTime.now()));
            Text date = new Text(dtf.format(LocalDateTime.now()));

            grid.add(currentTime, 1, 1,2,1);
            grid.add(date, 1, 2, 2, 1);


        //BUTTON + EVENT HANDLER BSP
        Button btn = new Button();
        btn.setText("Update Time");
        grid.add(btn, 3,1);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                currentTime.setText(dtft.format(LocalDateTime.now()));
            }
        });

        //JAVAFX STARTEN
        //grid.setGridLinesVisible(true);
        Scene scene = new Scene(grid, w, h);
        primaryStage.setTitle(dtf.format(LocalDateTime.now()));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}