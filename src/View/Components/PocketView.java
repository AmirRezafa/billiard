package View.Components;

import java.awt.*;

public class PocketView {
    public static void Draw(int x, int y, int r, Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.fillOval(x, y, r * 2, r * 2);
    }

}
