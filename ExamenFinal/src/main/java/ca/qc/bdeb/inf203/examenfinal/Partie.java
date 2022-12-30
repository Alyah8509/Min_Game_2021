package ca.qc.bdeb.inf203.examenfinal;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Partie {
    private  Gnome gnome = new Gnome();
    private ArrayList<Poules> poules = new ArrayList<>();
    private ArrayList<Cochons> cochons = new ArrayList<>();
    private ArrayList<Oeuf> oeufs = new ArrayList<>();
    private long start = 0;
    private int score = 0;
    private int coeur = 3;

    public Partie() {
        generer();
        oeufs.add(new Oeuf());
    }

    public void update(double deltaTemps) {
        gnome.update(deltaTemps);
        for (Poules poule : poules) {
            poule.update(deltaTemps);
        }
        for (Cochons cochon : cochons) {
            cochon.update(deltaTemps);
        }
        for (Oeuf oeuf : oeufs) {
            oeuf.update(deltaTemps);
        }
        creerOeufs();
        for (int i = 0; i < oeufs.size(); i++) {
            if (oeufs.get(i).tomber()) {
                oeufs.remove(i);
                coeur--;
            }
        }
        verifierScore();
    }

    private void verifierScore() {
        for (int i = 0; i < oeufs.size(); i++) {
            if (gnome.ramasser(oeufs.get(0))) {
                score++;
                oeufs.remove(i);
            }
        }
    }

    private void creerOeufs() {
        if (start == 0) {
            start = System.currentTimeMillis();
        } else {
            long end = System.currentTimeMillis();
            long temps = TimeUnit.MILLISECONDS.toSeconds(end - start);
            if (temps >= 3) {
                start = 0;
                oeufs.add(new Oeuf());
            }
        }
    }

    public void draw(GraphicsContext context) {
        context.drawImage(new Image("arriere-plan.png"), 0, 0);
        for (Poules poule : poules) {
            poule.draw(context);
        }
        for (Cochons cochon : cochons) {
            cochon.draw(context);
        }
        for (Oeuf oeuf : oeufs) {
            oeuf.draw(context);
        }
        gnome.draw(context);
    }

    private void generer() {
        for (int i = 0; i < 3; i++) {
            poules.add(new Poules());
        }
        for (int i = 0; i < 2; i++) {
            cochons.add(new Cochons());
        }
    }

    protected int getScore() {
        return score;
    }
    protected int getCoeur() {
        return coeur;
    }
    protected void drawI (GraphicsContext context){
        context.setFill(Color.RED);
        context.setFont(Font.font(40));
        context.setTextAlign(TextAlignment.CENTER);
        context.fillText("PERDU!",Main.WIDTH/2,Main.HEIGHT/2);
    }

}
