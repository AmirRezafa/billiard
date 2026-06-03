package App;

import Controller.GameController;
import View.MainFrame;

public class Main{
    public static void main(String[] args) {
        GameController.createBalls();
        new MainFrame().show();
    }
}