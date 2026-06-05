package View.Panels;

import Model.Utils.File;
import View.MainFrame;
import javax.swing.*;
import java.awt.*;

public class RecordsPanel extends JPanel {
    private static final Color BackgroundColor = new Color(25, 25, 25);
    private static final Color SideColor = new Color(50, 50, 50);

    public RecordsPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        setBackground(BackgroundColor);

        JPanel panel = new JPanel();
        panel.setBackground(BackgroundColor);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        var data = File.getLastGames();
        for (String[] game : data) {
            JLabel label = new JLabel(
                    game[0] + " (Score: " + game[2] + ", FoulCount:" + game[1] + ")" +
                            "  vs  " +
                            game[3] + " (Score: " + game[5] + ", FoulCount:" + game[4] + ")");

            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 22));

            JPanel row = new JPanel();
            row.setBackground(SideColor);
            row.add(label);
            panel.add(Box.createVerticalStrut(10));
            panel.add(row);
        }

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBorder(null);

        add(scroll, BorderLayout.CENTER);

        JButton back = new JButton("Back");

        back.addActionListener(e -> {
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
            frame.repaint();
        });

        JPanel south = new JPanel();
        south.setBackground(BackgroundColor);
        south.add(back);

        add(south, BorderLayout.SOUTH);
    }
}