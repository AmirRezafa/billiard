package View;

import View.Panels.MainMenuPanel;
import View.Panels.SettingPanel;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(){
        super("Billiard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setContentPane(new MainMenuPanel());
        setContentPane(new SettingPanel());
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
