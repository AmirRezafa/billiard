package Model.Entities;

import java.awt.*;

public class Pocket {
    private double x, y;
    private double r;
    // TODO: Select 1 pocket for last shot(8 th ball)
    // private String Name;


    public Pocket(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }
}
