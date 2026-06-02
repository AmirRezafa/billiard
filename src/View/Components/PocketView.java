package View.Components;

import java.awt.*;

public class PocketView {
    public static void Draw(double x, double y, double r, Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.fillOval((int)(x - r), (int)(y - r), (int)(2 * r), (int)(2 * r));
    }

}
