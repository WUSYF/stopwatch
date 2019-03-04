import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {
    //VARAIABLEN FÜR DIE GRÖßE
    final int w = 600;
    final int h = 500;

    //TIME FORMATS
    DateTimeFormatter time_format_date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    DateTimeFormatter time_format_time = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void start(Stage primaryStage) {
        //GRID ERSTELLEN
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(5, 5, 5, 5));

        //WATCH
        //titels
        Text titleUhr = new Text("Watch");
        titleUhr.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        Text titleUhrzeit = new Text("Current time: ");
        titleUhrzeit.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        Text titleDate = new Text("Date: ");
        titleDate.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));

        grid.add(titleUhr, 0, 0, 3, 1);
        grid.add(titleUhrzeit, 0, 1);
        grid.add(titleDate, 0, 2);

        //times
        Text currentTime = new Text(time_format_time.format(LocalDateTime.now()));
        Text date = new Text(time_format_date.format(LocalDateTime.now()));

        grid.add(currentTime, 1, 1, 2, 1);
        grid.add(date, 1, 2, 2, 1);

        //STOPWATCH
        //titles
        Text titleStopwatch = new Text("Stopwatch");
        titleStopwatch.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        grid.add(titleStopwatch, 0, 3, 3, 1);
        //properties
        Text stopped_time = new Text("00:00");
        grid.add(stopped_time, 0, 4);

        //TIMERS
        //update Time
        Timer watchTimer = new Timer();
        watchTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                currentTime.setText(time_format_time.format(LocalDateTime.now()));
            }
        }, 0, 1000);

        //update Date
        Timer dateTimer = new Timer();
        dateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                date.setText(time_format_date.format(LocalDateTime.now()));
            }
        }, 0, 1000);

        //JAVAFX STARTEN
        grid.setGridLinesVisible(true);
        Scene scene = new Scene(grid, w, h);
        primaryStage.setTitle(time_format_date.format(LocalDateTime.now()));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}