package Controller;

import Model.Entities.Ball;
import Model.Game.Game;

import java.util.ArrayList;

public class PhysicsEngine {
    private ArrayList<Ball> balls;

    public PhysicsEngine() {
        balls = Game.getBalls();
    }

    public boolean isCollide(Ball ball1, Ball ball2, double w, double r){
        double dx = w * (ball1.getX() - ball2.getX());
        double dy = w * (ball1.getY() - ball2.getY());

        return (dx * dx + dy * dy <= 4 * r * r);
    }

    public void collide(Ball ball1, Ball ball2) {

        double dx = ball2.getX() - ball1.getX();
        double dy = ball2.getY() - ball1.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if(distance == 0) return;

        double nx = dx / distance;
        double ny = dy / distance;
        double speed = (ball1.getVelocityX() - ball2.getVelocityX()) * nx +
                        (ball1.getVelocityY() - ball2.getVelocityY()) * ny;

        if (speed <= 0)
            return;

        ball1.addVelocity(- speed * nx, - speed * ny);
        ball2.addVelocity(speed * nx, speed * ny);
    }

    // BUGFIXED: updatePos ro gozashtam ke serfan jitter nashe
    public void collide(Ball ball, double w, double r){
        if(ball.getX() <= 1 || ball.getX() + (2 * r / w) >= 9){
            ball.addVelocity(-2 * ball.getVelocityX(), 0);
            ball.updatePos();
        }
        if(ball.getY() <= 0.75 || ball.getY() + (2 * r / w) >= 4.75){
            ball.addVelocity(0, -2 * ball.getVelocityY());
            ball.updatePos();
        }
    }

    public void updateBalls(double w, double r){
        for(Ball ball: balls){
            if(ball.isMoving()){
                for(Ball ball2: balls){
                    if(ball == ball2) continue;
                    if(isCollide(ball, ball2, w, r)){
                        collide(ball, ball2);
                    }
                }
                collide(ball, w, r);
                ball.updatePos();
            }
        }
    }

    public void shoot(double angle, int powerRange, double w) {
//        System.out.println(w);

        double vx = (w / 118) * Math.cos(angle) * powerRange / 500;
        double vy = (w / 118) * Math.sin(angle) * powerRange / 500;
        Game.getCueBall().addVelocity(vx, vy);
    }

    public boolean anythingMove(){
        for(Ball ball: balls){
            if(ball.isMoving()) return true;
        }
        return false;
    }
}
