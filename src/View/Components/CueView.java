package View.Components;

import Model.Entities.Ball;
import Model.Entities.Cue;

import java.awt.*;

public class CueView {
    public static void draw(Graphics2D g2, Cue cue, Ball cueBall,
                            double w, int ballRadius, int powerrange, int cueStyle) {

        double x = cueBall.getX() * w + ballRadius;
        double y = cueBall.getY() * w + ballRadius;
        double angle = cue.getAngle();

        int d = ballRadius + 10 + powerrange;

        Graphics2D g2Copy = (Graphics2D) g2.create();

        g2Copy.translate(x - Math.cos(angle) * d,
                y - Math.sin(angle) * d);

        g2Copy.rotate(angle + Math.PI);

        int temp = (int)(w / 25);
        if(cueStyle == 0) g2Copy.setColor(new Color(205, 133, 63));
        else if(cueStyle == 1) g2Copy.setColor(new Color(60, 40, 20));

        g2Copy.fillRoundRect(0, -temp, (int)(2.5 * w), 2 * temp,
                2 * temp, 2 * temp);

        if(cueStyle == 1){
            g2Copy.setColor(new Color(212, 175, 55));
            g2Copy.fillRect(0, -temp, (int)(0.3 * w), 2 * temp);
        }
        g2Copy.dispose();
    }
}