import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {
    //VARAIABLEN FÜR DIE GRÖßE
    final int w = 600;
    final int h = 500;
    LocalDateTime stopStart = LocalDateTime.now();
    boolean stopwRunning = false;

    //TIME FORMATS
    DateTimeFormatter time_format_date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    DateTimeFormatter time_format_time = DateTimeFormatter.ofPattern("HH:mm:ss");
    DateTimeFormatter stop_format = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");

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
        Text stopped_time = new Text("0:0:0:000");
        Button timerStart = new Button("Start Timer");
        Button timerStop = new Button("Stop Timer");
        Button timerReset = new Button("Reset Timer");
        Button timerResume = new Button("Resume Timer");
        grid.add(stopped_time, 0, 4);
        grid.add(timerStart, 4,4,1,1);
        grid.add(timerStop, 5,4,1,1);
        grid.add(timerReset, 7, 4,1,1);
        grid.add(timerResume, 6, 4,1,1);

        timerStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stopwRunning = true;
                stopStart = LocalDateTime.now();
            }
        });
        timerStop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stopwRunning = false;
            }
        });
        timerReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stopwRunning = false;
                stopped_time.setText("0:0:0:000");
            }
        });
        timerResume.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stopwRunning = true;
            }
        });



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

        //update Stopwatch
        Timer stopTimer = new Timer();
        stopTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(stopwRunning)stopped_time.setText(getTimeDiff(stopStart, LocalDateTime.now()));
            }
        }, 0, 100);

        //JAVAFX STARTEN
        //grid.setGridLinesVisible(true);
        Scene scene = new Scene(grid, w, h);
        primaryStage.setTitle(time_format_date.format(LocalDateTime.now()));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public String getTimeDiff(LocalDateTime from, LocalDateTime to){
        DateTimeFormatter h = DateTimeFormatter.ofPattern("HH");
        DateTimeFormatter m = DateTimeFormatter.ofPattern("mm");
        DateTimeFormatter s = DateTimeFormatter.ofPattern("ss");
        DateTimeFormatter ss = DateTimeFormatter.ofPattern("SSS");
        long millis = from.until(to, ChronoUnit.MILLIS);
        long sec = from.until(to, ChronoUnit.SECONDS);
        long min = from.until(to, ChronoUnit.MINUTES);
        long hrs = from.until(to, ChronoUnit.HOURS);
        long save;
        if(millis > 1000){
            save = millis%1000;
            millis %= 1000;
            sec += (millis - save)/1000;
        }
        if(sec>60){
            save = sec%60;
            sec %= 60;
            min += (sec-save)/60;
        }
        if(min>60){
            save = min%60;
            min %= 60;
            hrs += (min-save)/60;
        }
        return hrs + ":" + min + ":" + sec + ":" + millis + "";
    }

    public static void main(String[] args) {
        launch(args);
    }
}