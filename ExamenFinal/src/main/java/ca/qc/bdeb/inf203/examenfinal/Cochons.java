package ca.qc.bdeb.inf203.examenfinal;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class Cochons extends GameObject {
    private Image img;
    private final double vitesseX = 85;
    private boolean left;

    public Cochons() {
        Random rnd = new Random();
        w = 90;
        h = 56;
        y = Main.HEIGHT - h;
        int r = (int) ((int) Main.WIDTH - w);
        x = rnd.nextInt(r);
        int position = rnd.nextInt(2);
        if (position == 0) {
            img = new Image("pig-left.png");
            left = true;
        } else {
            img = new Image("pig-right.png");
            left = false;
        }
    }

    @Override
    public void update(double deltaTemps) {
        if (left) {
            vx = -vitesseX;
        } else {
            vx = vitesseX;
        }
        updatePhysique(deltaTemps);
        depasser();
    }

    private void depasser() {
        if (x < 0) {
            x = 0;
            left = false;
            img = new Image("pig-right.png");
        } else if (x + w > Main.WIDTH) {
            x = Main.WIDTH - w;
            left = true;
            img = new Image("pig-left.png");
        }
    }

    @Override
    public void draw(GraphicsContext context) {
        context.drawImage(img, x, y, w, h);
    }
}
