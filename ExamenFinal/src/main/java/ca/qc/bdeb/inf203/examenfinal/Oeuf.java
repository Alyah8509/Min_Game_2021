package ca.qc.bdeb.inf203.examenfinal;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class Oeuf extends GameObject {
    Image img = new Image("oeuf.png");

    public Oeuf() {
        Random random = new Random();
        h = img.getHeight();
        w = img.getWidth();
        y = 0 - h;
        int r = (int) ((int) Main.WIDTH - w);
        x = random.nextInt(r);
        vx = 0;
        ay = 50;

    }

    protected boolean tomber() {
        if (y + h >= Main.HEIGHT) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void draw(GraphicsContext context) {
        context.drawImage(img, x, y);
    }
}
