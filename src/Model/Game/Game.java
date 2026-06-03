package Model.Game;

import Controller.GameController;
import Model.Entities.Ball;
import Model.Entities.Player;

import java.util.ArrayList;

public class Game {
    private static ArrayList<Ball> balls = new ArrayList<>();

    private static Player player1, player2;
    private static Player currentPlayer;

    public static void addBall(Ball ball){
        balls.add(ball);
    }

    public static ArrayList<Ball> getBalls() {
        return balls;
    }
}
