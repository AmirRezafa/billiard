package View.Panels;

import View.MainFrame;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private static final Color backGroundColor = new Color(25, 25, 25);
    private static final Dimension ButtonSize = new Dimension(220, 50);

    private final JLabel label = new JLabel("Billiard");

    private static MainFrame MF;
    // :(((( SAD FOR Abbreviation

    private enum Button{
        Start("Start") {
            @Override
            public void clicked() {
                MF.setContentPane(new GamePanel());
                MF.revalidate();
                MF.repaint();
            }
        },
        Setting("Setting") {
            @Override
            public void clicked() {
                MF.setContentPane(new SettingPanel(MF));
                MF.revalidate();
                MF.repaint();

            }
        },
        Exit("Exit") {
            @Override
            public void clicked() {
                System.out.println("DONE :((((");
                System.exit(0);
            }
        };

        private String Name;
        Button(String Name){
            this.Name = Name;
        }

        public String getName() {
            return Name;
        }

        public abstract void clicked();
    }

    public MainMenuPanel(MainFrame MF){
        this.MF = MF;
        setLayout(new GridBagLayout());
        setBackground(backGroundColor);

        JPanel ButtonsPanel = new JPanel();
        ButtonsPanel.setBackground(backGroundColor);
        ButtonsPanel.setLayout(new BoxLayout(ButtonsPanel, BoxLayout.Y_AXIS));


        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(Color.white);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        ButtonsPanel.add(label);
        ButtonsPanel.add(Box.createVerticalStrut(15));
        Dimension d = getPreferredSize();
        for(Button button: Button.values()){
            JButton temp = new JButton(button.getName());
            temp.setAlignmentX(Component.CENTER_ALIGNMENT);
            temp.setSize(ButtonSize);
            temp.setMinimumSize(ButtonSize);
            temp.setMaximumSize(ButtonSize);
            temp.setFocusPainted(false);
            temp.addActionListener(e -> button.clicked());
            ButtonsPanel.add(Box.createVerticalStrut(15));
            ButtonsPanel.add(temp);
        }
        add(ButtonsPanel);
    }
}
