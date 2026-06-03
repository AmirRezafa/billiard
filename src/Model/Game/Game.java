package Model.Game;

import Model.Entities.Ball;

import java.util.ArrayList;

public class Game {
    private static ArrayList<Ball> balls;

    public static void addBall(Ball ball){
        balls.add(ball);
    }

    public static ArrayList<Ball> getBalls() {
        return balls;
    }
}
