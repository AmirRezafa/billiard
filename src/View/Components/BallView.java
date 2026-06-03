package View.Components;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class BallView {
    public static void Draw(int x, int y, int angle, int r, int number, Color color, boolean bi, Graphics2D g2){
        if(bi) g2.setColor(Color.WHITE);
        else g2.setColor(color);
        g2.fillOval(x, y, 2 * r, 2 * r);

        if(number == 0) return;
        AffineTransform old = g2.getTransform();

        //TODO: Add angle to physic in future ایشالا
        g2.rotate(Math.toRadians(angle), x + r, y + r);

        if(bi) {
            g2.setColor(color);
            g2.fillRoundRect(x + (int) (r / 2), y, r, 2 * r, (int)(r * 0.7), (int)(r * 0.7));
        }

        g2.setTransform(old);
        g2.setColor(Color.WHITE);
        g2.fillOval(x + (int)(r * 0.5), y +  (int)(r * 0.5), r, r);
        g2.setColor(Color.BLACK);
        String text = String.valueOf(number);
        Font font = new Font("Arial", Font.BOLD, (int)(r * 0.6));
        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();

        g2.drawString(text, x + (r + r - fm.stringWidth(text)) / 2,
                y + ((int)(r * 0.9) + r + fm.getAscent()) / 2);

    }
}
