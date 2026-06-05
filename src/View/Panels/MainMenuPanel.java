package View.Panels;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class MainMenuPanel extends JPanel {
    private static final Color backGroundColor = new Color(25, 25, 25);
    private static final Dimension ButtonSize = new Dimension(220, 50);

    private final JLabel label = new JLabel("Billiard");

    private enum Button{
        Start("Start"),
        Setting("Setting"),
        Exit("Exit");

        private String Name;
        Button(String Name){
            this.Name = Name;
        }

        public String getName() {
            return Name;
        }
    }

    public MainMenuPanel(){
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
            ButtonsPanel.add(Box.createVerticalStrut(15));
            ButtonsPanel.add(temp);
        }
        add(ButtonsPanel);
    }
}
