package Controller;

import Model.Entities.Ball;
import Model.Entities.Pocket;
import Model.Game.Game;

import java.util.ArrayList;

public class PhysicsEngine {
    private ArrayList<Ball> balls;
    private GameController GC;
    private boolean motion = false;

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

    public boolean pocketed(Ball ball, Pocket pocket, double w, double r){
        double dx = ((ball.getX() * w) + r) - pocket.getX() * w;
        double dy = ((ball.getY() * w) + r) - pocket.getY() * w;
        double distance = Math.sqrt(dx * dx + dy * dy);
        return (distance <= 2 * r);
    }

    // BUGFIXED: updatePos ro gozashtam ke serfan jitter nashe
    public void collide(Ball ball, double w, double r){
        for(Pocket pocket: Game.getPockets())
            if(pocketed(ball, pocket, w, r)){
                GC.pocketed(pocket, ball);
            }
        if(ball.getX() <= 1 || ball.getX() + (2 * r / w) >= 9){
            GC.collideWall();
            ball.addVelocity(-2 * ball.getVelocityX(), 0);
            ball.updatePos();
        }
        if(ball.getY() <= 0.75 || ball.getY() + (2 * r / w) >= 4.75){
            GC.collideWall();
            ball.addVelocity(0, -2 * ball.getVelocityY());
            ball.updatePos();
        }
    }

    public void updateBalls(double w, double r){
        anythingMove();
        for(Ball ball: balls){
            if(!ball.isOntable()) continue;
            if(ball.isMoving()){
                for(Ball ball2: balls){
                    if(ball == ball2 || !ball2.isOntable()) continue;
                    if(isCollide(ball, ball2, w, r)){
                        collide(ball, ball2);
                    }
                }
                // Add "if" to optimize
                if(ball.getY() < 1 || ball.getY() > 4.25 || ball.getX() < 1.25 || ball.getX() > 8.5)
                    collide(ball, w, r);
                ball.updatePos();
            }
        }
    }

    public void shoot(double angle, int powerRange, double w) {
//        System.out.println(w);
        motion = true;
        double vx = (w / 118) * Math.cos(angle) * powerRange / 500;
        double vy = (w / 118) * Math.sin(angle) * powerRange / 500;
        Game.getCueBall().addVelocity(vx, vy);
        GC.shot();
    }

    public boolean anythingMove(){
        if(!motion) return false;
        for(Ball ball: balls){
            if(ball.isMoving() && ball.isOntable()) return true;
        }
        GC.shootingEnded();
        motion = false;
        return false;
    }

    public void setGC(GameController GC){
        this.GC = GC;
    }

    public void setCueball(double x, double y, double w, double r) {
        Ball ball = Game.getCueBall();
        boolean state = false;
        if(x <= 1 || x + (2 * r / w) >= 9){
            state = true;
        }
        else if(y <= 0.75 || y + (2 * r / w) >= 4.75){
            state = true;
        }
        else for(Ball temp: Game.getBalls()){
            if(temp.isOntable() && temp != ball){
                double dx = w * (x - temp.getX());
                double dy = w * (y - temp.getY());

                if(dx * dx + dy * dy <= 4 * r * r) state = true;
            }
        }
        if(state){
            ball.setX(-w);
            ball.setY(-w);
        }else{
            ball.setX(x);
            ball.setY(y);
        }
    }
}
