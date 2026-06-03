package Model.Entities;

import java.awt.*;

public class Ball {
    private int number;
    private Color color;
    private boolean bicolor;

    private double x, y;
    private boolean ontable;

    public Ball(int number, Color color, boolean bicolor, double x, double y, boolean ontable) {
        this.number = number;
        this.color = color;
        this.bicolor = bicolor;
        this.x = x;
        this.y = y;
        this.ontable = ontable;
    }

    public int getNumber() {
        return number;
    }

    public Color getColor() {
        return color;
    }

    public boolean isBicolor() {
        return bicolor;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isOntable() {
        return ontable;
    }
}
