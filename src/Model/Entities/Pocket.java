package Model.Entities;

import java.awt.*;

public class Pocket {
    private int x, y;
    private int r;
    // TODO: Select 1 pocket for last shot(8 th ball)
    // private String Name;


    public Pocket(double x, double y, double r) {
        this.x = (int) x;
        this.y = (int) y;
        this.r = (int) r;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }
}
