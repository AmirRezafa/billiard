package Controller;

import Model.Entities.Ball;
import Model.Game.Game;
import View.Panels.GamePanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;



public class GameController implements MouseListener, MouseMotionListener {
    private GamePanel GP;
    private PhysicsEngine PE;
    private boolean showCue = false;

    private boolean dragging = false;
    private int startx, starty;
    private int powerrange = 0;
    public double angle;

    public GameController(GamePanel GP, PhysicsEngine PE){
        this.GP = GP;
        this.PE = PE;
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
        if(!dragging) return;
        double dx = e.getX() - startx;
        double dy = e.getY() - starty;
        powerrange = (int)(Math.sqrt(dx * dx + dy * dy));

        Ball cueBall = Game.getCueBall();
        double w = GP.getWidth() * 0.1;
        double dxx = e.getX() - GP.getBallR() - cueBall.getX() * w;
        double dyy = e.getY() - GP.getBallR() - cueBall.getY() * w;

        double angle = Math.atan2(dyy, dxx);

        powerrange = Math.max(0, (int)(powerrange - w * Math.abs(this.angle - angle)));

//        if(Math.abs(this.angle - angle) > 1 || distance < (GP.getBallR() * 3)){
//            powerrange = 0;
//        }
    }



    @Override
    public void mouseMoved(MouseEvent e) {
        if(PE.anythingMove()) return;
        Ball cueBall = Game.getCueBall();
        double w = GP.getWidth() * 0.1;
        double dx = e.getX() - GP.getBallR() - cueBall.getX() * w;
        double dy = e.getY() - GP.getBallR() - cueBall.getY() * w;

        angle = Math.atan2(dy, dx);
        double distance = Math.sqrt(dx * dx + dy * dy);

        showCue = distance < (GP.getBallR() * 3);
        Game.getCue().setAngle(angle);
        powerrange = 0;
    }

    public boolean isShowCue() {
        return showCue;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(showCue){
            dragging = true;
            startx = e.getX();
            starty = e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(dragging){
            if(powerrange > 1) PE.shoot(angle, powerrange, GP.getWidth() * 0.1);
            else System.out.println("Cancelled");
            dragging = false;
            showCue = false;
            powerrange = 0;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int getPowerrange() {
        if(powerrange > 100) return 100;
        return powerrange;
    }
}
