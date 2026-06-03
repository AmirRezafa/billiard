package View.Panels;

import Controller.GameController;
import Controller.PhysicsEngine;
import Model.Entities.Ball;
import Model.Entities.Pocket;
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
    private boolean firstFrame = true;

    public GamePanel(){
        setBackground(Color.BLACK);
        PE = new PhysicsEngine();

        GC = new GameController(this, PE);

        addMouseMotionListener(GC);
        addMouseListener(GC);

        Timer timer = new Timer(
                8,
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

        ballR = (int) (w * 0.15);

        PE.updateBalls(w, ballR);

        g2.setColor(new Color(160, 82, 45));
        g2.fillRoundRect((int) (0.75 * w), (int) (0.5 * w),
                (int) (8.5 * w), (int) (4.5 * w), (int) (w / 5), (int) (w / 5));

        g2.setColor(Color.GREEN);
        g2.fillRoundRect((int) w, (int) (w * 0.75), (int) (w * 8)
                , (int) (w * 4), (int) (w / 3), (int) (w / 3));

        if (firstFrame) {
            firstFrame = false;
            GC.createPockets();
        }
        drawPowerBar(g2, GC.getPowerrange(), (int) (2.5 * w), (int) (6 * w), (int) (5 * w), (int)(w / 5));

        for (Pocket pocket : Game.getPockets()) {
            PocketView.Draw((int)(pocket.getX() * w), (int)(pocket.getY() * w),
                    (int)(pocket.getR() * w), g2);
        }

        for (Ball ball : Game.getBalls()) {
            if (!ball.isOntable()) continue;
            BallView.Draw((int) (ball.getX() * w), (int) (ball.getY() * w),
                    0, ballR, ball.getNumber(),
                    ball.getColor(), ball.isBicolor(), g2);
        }

        if (GC.isShowCue()) CueView.draw(g2, Game.getCue(), Game.getCueBall(), w, ballR, GC.getPowerrange());


        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, (int)(w / 5)));

        g2.drawString("Ball In Hand", (int) (0.75 * w),(int) (0.5 * w - w / 8));

        String timeText = "Time: ?????";

        FontMetrics fm = g2.getFontMetrics();

        g2.drawString(timeText, (int) (9.25 * w) - fm.stringWidth(timeText),
                (int) (0.5 * w - w / 8));

        String turn = "Turn: " + "player 2";
        String player2s = "AmirReza: " + 15;

        g2.drawString("Awmir: " + 5, (int) (0.75 * w), (int) (5.25 * w));

        g2.drawString(turn, (int) (5 * w) -
                        fm.stringWidth(turn) / 2, (int) (5.25 * w));

        g2.drawString(player2s, (int) (9.25 * w) - fm.stringWidth(player2s), (int) (5.25 * w));

        BallView.Draw((int) (0.75 * w), (int) (5.25 * w) + (int)(ballR * 0.5),
                0, (int)ballR, 7,
                new Color(128, 0, 0), false, g2);

        BallView.Draw((int) (9.25 * w) - fm.stringWidth(player2s), (int) (5.25 * w) + (int)(ballR * 0.5),
                0, (int)(ballR), 15,
                new Color(128, 0, 0), true, g2);

    }

    public int getBallR() {
        return ballR;
    }
}
