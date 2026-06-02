package View.Panels;

import View.Components.PocketView;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public GamePanel(){
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        double w = (int)(getWidth() / 10);

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
    }

}
