package Controller;

import Model.Entities.Ball;
import Model.Game.Game;
import View.Panels.GamePanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;



public class GameController implements MouseMotionListener {
    private GamePanel GP;
    public GameController(GamePanel GP){
        this.GP = GP;
    }
    private Color getBallColor(int number) {
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

    public void createBalls() {
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println(1);
        Ball cueBall = Game.getCueBall();

        double w = (int)(GP.getWidth() / 10);
        double angle = Math.atan2(
                e.getY() - cueBall.getY() * w,
                e.getX() - cueBall.getX() * w
        );

        Game.getCue().setAngle(angle);

        GP.repaint();
    }
}
