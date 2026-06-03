package Model.Game;

import Controller.GameController;
import Model.Entities.Ball;
import Model.Entities.Cue;
import Model.Entities.Player;
import Model.Entities.Pocket;

import java.util.ArrayList;

public class Game {
    private static ArrayList<Ball> balls = new ArrayList<>();
    private static ArrayList<Pocket> pockets = new ArrayList<>();

    private static Player player1, player2;
    private static Player currentPlayer;

    private static Cue cue;

    static{
        cue = new Cue();
    }

    public static void addBall(Ball ball){
        balls.add(ball);
    }

    public static ArrayList<Ball> getBalls() {
        return balls;
    }

    public static Ball getCueBall() {
        return balls.get(0);
}

    public static Cue getCue() {
        return cue;
    }

    public static void addPocket(Pocket pocket){ pockets.add(pocket); }

    public static ArrayList<Pocket> getPockets() { return pockets; }
}
