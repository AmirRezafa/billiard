package Controller;

import Model.Entities.Ball;
import Model.Game.Game;

import java.util.ArrayList;

public class PhysicsEngine {
    private ArrayList<Ball> balls;

    public PhysicsEngine() {
        balls = Game.getBalls();
    }

    public void updateBalls(){
        for(Ball ball: balls){
            ball.updatePos();
        }
    }

    public void shoot(double angle, int powerRange, double w) {
//        System.out.println(w);

        double vx = (w / 118) * Math.cos(angle) * powerRange / 500;
        double vy = (w / 118) * Math.sin(angle) * powerRange / 500;
        Game.getCueBall().setVelocity(vx, vy);
    }

    public boolean anythingMove(){
        for(Ball ball: balls){
            if(ball.isMoving()) return true;
        }
        return false;
    }
}
