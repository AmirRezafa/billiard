package View.Panels;

import Controller.GameController;
import Controller.PhysicsEngine;
import Model.Entities.Ball;
import Model.Game.Game;
import View.Components.BallView;
import View.Components.CueView;
import View.Components.PocketView;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private int ballR = 5;
    private GameController GC;
    private PhysicsEngine PE;

    public GamePanel(){
        setBackground(Color.BLACK);
        PE = new PhysicsEngine();

        GC = new GameController(this, PE);
        GC.createBalls();

        addMouseMotionListener(GC);
        addMouseListener(GC);

        Timer timer = new Timer(
                16,
                e -> repaint()
        );
        timer.start();
    }

    private void drawPowerBar(Graphics2D g2, int power, int x, int y, int width, int height) {
        int filledWidth = width * power / 100;
        GradientPaint color = new GradientPaint(x, y,  new Color(180, 255, 0),
                x + width, y, new Color(255, 80, 0)
        );
        g2.setColor(Color.GRAY);
        g2.fillRoundRect(x, y, width, height, 15, 15);
        g2.setPaint(color);
        g2.fillRoundRect(x, y, filledWidth, height, 15, 15);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        double w = getWidth() * 0.1;

        ballR = (int)(w / 6);

        PE.updateBalls(w, ballR);

        g2.setColor(new Color(160, 82, 45));
        g2.fillRoundRect((int)(0.75 * w), (int)(0.5 * w),
                (int)(8.5 * w), (int)(4.5 * w), (int)(w/5), (int)(w/5));

        g2.setColor(Color.GREEN);
        g2.fillRoundRect((int)w, (int)(w * 0.75), (int)(w * 8)
                , (int)(w * 4), (int)(w/3), (int)(w/3));

        g2.setColor(Color.BLACK);
        double r = w * 0.2;
        PocketView.Draw(w, 0.75 * w, r, g2);
        PocketView.Draw(w * 5, 0.75 * w - w/20,0.9 * r, g2);
        PocketView.Draw(9 * w, 0.75 * w, r, g2);

        PocketView.Draw(w, 4 * w + 0.75 * w, r, g2);
        PocketView.Draw(w * 5, 4 * w + 0.75 * w + w/20,0.9 * r, g2);
        PocketView.Draw(9 * w, 4 * w + 0.75 * w, r, g2);

        for(Ball ball: Game.getBalls()){
            BallView.Draw((int)(ball.getX() * w), (int)(ball.getY() * w),
                    0, ballR, ball.getNumber(),
                    ball.getColor(), ball.isBicolor(), g2);
        }

        if(GC.isShowCue()) CueView.draw(g2, Game.getCue(), Game.getCueBall(), w, ballR, GC.getPowerrange());

        drawPowerBar(g2, GC.getPowerrange(), (int)(0.75 * w), (int)(5.1 * w), (int)(5 * w), 25);
    }

    public int getBallR() {
        return ballR;
    }
}
