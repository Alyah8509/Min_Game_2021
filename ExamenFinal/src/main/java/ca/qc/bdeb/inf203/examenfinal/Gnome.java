package ca.qc.bdeb.inf203.examenfinal;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Gnome extends GameObject {
    private Image img = new Image("gnome-right.png");
    private final double vitesseX = 250;

    public Gnome() {
        w = 107;
        h = 96;
        y = Main.HEIGHT - h;
        x = Main.WIDTH / 2;

    }

    @Override
    public void update(double deltaTemps) {
        boolean right = Input.isKeyPressed(KeyCode.RIGHT);
        boolean left = Input.isKeyPressed(KeyCode.LEFT);
        if (right) {
            img = new Image("gnome-right.png");
            vx = vitesseX;
        } else if (left) {
            img = new Image("gnome-left.png");
            vx = -vitesseX;
        } else {
            vx = 0;
        }
        updatePhysique(deltaTemps);
        depasser();
    }

    private void depasser() {
        if (x < 0) {
            x = 0;
        } else if (x + w > Main.WIDTH) {
            x = Main.WIDTH - w;
        }
    }

    @Override
    public void draw(GraphicsContext context) {
        context.drawImage(img, x, y, w, h);
    }

    protected boolean ramasser(Oeuf oeuf) {
        double positionXG = x + (w / 2);
        double positionXO = oeuf.x + (oeuf.w / 2);
        double positionYG = y + (h / 2);
        double positionYO = oeuf.y + (oeuf.h / 2);
        double distanceX = positionXG - positionXO;
        double distanceY = positionYG - positionYO;
        double distance = Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
        if (distance <= 50) {
            return true;
        } else {
            return false;
        }
    }
}
