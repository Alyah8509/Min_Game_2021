package ca.qc.bdeb.inf203.examenfinal;

import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {
    protected double x, y, vx, vy, ax, ay, w, h;

    protected void updatePhysique(double deltaTemps) {
        vx += deltaTemps * ax;
        vy += deltaTemps * ay;

        x += deltaTemps * vx;
        y += deltaTemps * vy;
    }

    public double getHaut() {
        return y;
    }
    public double getBas() {
        return y + h;
    }
    public double getGauche() {
        return x;
    }
    public double getDroite() {
        return x + w;
    }

    public void update(double deltaTemps) {
        updatePhysique(deltaTemps);
    }

    public abstract void draw(GraphicsContext context);
}
