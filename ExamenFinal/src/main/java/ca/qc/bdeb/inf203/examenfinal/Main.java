package ca.qc.bdeb.inf203.examenfinal;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    public static final double WIDTH = 640, HEIGHT = 480;
    public static Text score=new Text("Score : 0");
    private static long start=0;
    private Partie partie;
    private AnimationTimer timer;
    private AnimationTimer timer1;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        var root = new Pane();
        var scene = new Scene(root, WIDTH, HEIGHT);
        var canvas = new Canvas(WIDTH, HEIGHT);
        VBox vBox=new VBox();
        score.setFont(Font.font(40));
        HBox hBox=new HBox();
        ImageView c1=new ImageView(new Image("coeur.png"));
        ImageView c2=new ImageView(new Image("coeur.png"));
        ImageView c3=new ImageView(new Image("coeur.png"));
        hBox.getChildren().addAll(c1,c2,c3);
        vBox.getChildren().addAll(score,hBox);
        root.getChildren().addAll(canvas,vBox);

        var context = canvas.getGraphicsContext2D();

        partie = new Partie();

        scene.setOnKeyPressed((e) -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                Platform.exit();
            } else {
                Input.setKeyPressed(e.getCode(), true);
            }
        });

        scene.setOnKeyReleased((e) -> {
            Input.setKeyPressed(e.getCode(), false);
        });

         timer = new AnimationTimer() {
            long lastTime = System.nanoTime();

            @Override
            public void handle(long now) {

                double deltaTemps = (now - lastTime) * 1e-9;

                deltaTemps = Math.min(deltaTemps, 1 / 30.0);
                context.drawImage(new Image("arriere-plan.png"), 0, 0);
                partie.update(deltaTemps);
                partie.draw(context);
                score.setText("Score : "+partie.getScore());
                if (partie.getCoeur()==2){
                    hBox.getChildren().remove(c1);
                }else if (partie.getCoeur()==1){
                    hBox.getChildren().remove(c2);
                }else if (partie.getCoeur()==0){
                    timer.stop();
                     timer1=new AnimationTimer() {
                        @Override
                        public void handle(long now) {
                            partie.drawI(context);
                            if (start==0){
                                start=System.currentTimeMillis();
                            }else {
                                long end = System.currentTimeMillis();
                                long temps = TimeUnit.MILLISECONDS.toSeconds(end - start);
                                if (temps >= 3) {
                                    start=0;
                                    timer1.stop();
                                    partie=new Partie();
                                    score.setText("Score : 0");
                                    hBox.getChildren().addAll(c1,c2);
                                    timer.start();
                                }
                            }
                        }
                    };timer1.start();

                }
                lastTime = now;
            }
        };
        timer.start();
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Examen Final");
        primaryStage.show();
    }
}