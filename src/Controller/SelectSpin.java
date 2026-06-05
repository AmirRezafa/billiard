package Controller;

import View.Panels.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectSpin implements MouseListener {
    private GamePanel GP;
    public SelectSpin(GamePanel GP) {
        this.GP = GP;
    }

    

    @Override
    public void mouseClicked(MouseEvent e) {
        double w = GP.getWidth() * 0.1;
        int r = (int)(0.35 * w);
        int[] xs = new int[5], ys = new int[5];
        xs[0] = (int)(5 * w); ys[0] = (int)(1.75 * w);
        xs[1] = (int)(5 * w); ys[1] = (int)(2.75 * w);
        xs[2] = (int)(5 * w); ys[2] = (int)(3.75 * w);
        xs[3] = (int)(4 * w); ys[3] = (int)(2.75 * w);
        xs[4] = (int)(6 * w); ys[4] = (int)(2.75 * w);

        for(int i = 0; i < 5; i++){
            double dx = e.getX() - xs[i];
            double dy = e.getY() - ys[i];

            if(dx * dx + dy * dy < r * r){
                GP.spinSelected(i);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
