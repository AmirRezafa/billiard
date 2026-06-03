package Controller;

import Model.Entities.Ball;
import Model.Game.Game;

import java.awt.*;
import java.util.ArrayList;



public class GameController {
    private static Color getBallColor(int number) {
        return switch (number) {
            case 1, 9 -> Color.YELLOW;
            case 2, 10 -> Color.BLUE;
            case 3, 11 -> Color.RED;
            case 4, 12 -> new Color(128, 0, 128);
            case 5, 13 -> Color.ORANGE;
            case 6, 14 -> new Color(0, 200, 0);
            case 7, 15 -> new Color(128, 0, 0);
            case 8 -> Color.black;
            default -> Color.WHITE;
        };
    }

    public static void createBalls() {
        double startX = 6.5;
        double startY = 2.6;

        Game.addBall(new Ball(0, Color.WHITE, false,
                startX - 4, startY, true));

        int[][] rack = {
                {1},
                {10, 2},
                {3, 8, 11},
                {12, 5, 13, 6},
                {7, 14, 4, 15, 9}
        };

        for (int i = 0; i < rack.length; i++) {

            double x = startX + i * 0.216;
            double y = startY - i * 0.252;

            for (int j = 0; j < rack[i].length; j++) {
                int number = rack[i][j];
                Game.addBall(new Ball(number, getBallColor(number), number >= 9,
                        x, y + j * 0.504, true));
            }
        }
    }
}
