package View;

import View.Panels.GamePanel;
import View.Panels.MainMenuPanel;
import View.Panels.SettingPanel;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(){
        super("Billiard");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MainMenuPanel());
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
