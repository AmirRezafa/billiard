package Model.Entities;

import java.awt.*;

public class Ball {
    private int number;
    private Color color;
    private boolean bicolor;

    private double x, y;
    private double velocityX = 0, velocityY = 0;
    private boolean ontable;
    private boolean moving = false;

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

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocity(double velocityX, double velocityY) {
        moving = true;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void updatePos(){
        if(!moving) return;
        x += velocityX;
        y += velocityY;
        velocityX *= 0.98;
        velocityY *= 0.98;
        if(Math.abs(velocityX) + Math.abs(velocityY) < 0.001){
            velocityX = 0;
            velocityY = 0;
            moving = false;
        }
    }


    public boolean isMoving(){
        return moving;
    }
}
