package Controller;

import Model.Entities.Pocket;
import Model.Game.Game;
import View.Panels.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectPocket implements MouseListener{
    private GamePanel GP;
    public SelectPocket(GamePanel GP) {
        this.GP = GP;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double w = GP.getWidth() * 0.1;
        for(Pocket pocket: Game.getPockets()){
            if(pocket.pressed(e, w)){
                GP.pocketSelected(pocket);
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
