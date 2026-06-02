package View;

import View.Panels.MainMenuPanel;
import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(){
        super("Billiard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MainMenuPanel());
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
