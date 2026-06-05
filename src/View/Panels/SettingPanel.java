package View.Panels;

import View.MainFrame;

import javax.swing.*;
import java.awt.*;

public class SettingPanel extends JPanel {
    private static final Color BackgroundColor = new Color(25, 25, 25);
    private static final Color SideColor = new Color(50, 50, 50);
    private static final Dimension ButtonSize = new Dimension(220, 50);
    private static final Dimension FieldSize = new Dimension(250, 30);

    private MainFrame MF;

    private final JTextField Player1Field = new JTextField();
    private final JTextField Player2Field = new JTextField();
    private final JSlider slider = new JSlider(0, 100, 50);
    private final JComboBox<String> combo = new JComboBox<>(
            new String[]{
                    "Classic",
                    "Modern",
                    "Professional"
            }
    );



    private enum Labels {
        Setting("SETTING"),
        Player1("Player 1 Name: "),
        Player2("Player 2 Name: "),
        CueStyle("Cue Style: "),
        SoundRange("Sound Volume: ");

        private String Text;
        Labels(String Text){
            this.Text = Text;
        }
        JLabel getLabel(int size){
            JLabel label = new JLabel(Text);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, size));
            return label;
        }
    }

    private void addobj(int y, JLabel label, Component comp, GridBagConstraints gbc){
        gbc.gridy = y;
        gbc.gridx = 0;
        add(label, gbc);
        gbc.gridx = 1;
        comp.setPreferredSize(FieldSize);
        comp.setForeground(Color.WHITE);
        add(comp, gbc);
    }

    public SettingPanel(MainFrame MF){
        this.MF = MF;
        setBackground(BackgroundColor);

//        setLayout(new BorderLayout());
//        JLabel Title = Label.Setting.getLabel(30);
//        Title.setHorizontalAlignment(SwingConstants.CENTER);
//        add(Title, BorderLayout.NORTH);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        add(Labels.Setting.getLabel(30), gbc);

        gbc.gridwidth = 1;

        Player1Field.setBackground(SideColor);
        Player2Field.setBackground(SideColor);
        combo.setBackground(SideColor);

        addobj(1, Labels.Player1.getLabel(10), Player1Field, gbc);

        addobj(2, Labels.Player2.getLabel(10), Player2Field, gbc);

        addobj(3, Labels.CueStyle.getLabel(10), combo, gbc);

        slider.setBackground(BackgroundColor);
        addobj(4, Labels.SoundRange.getLabel(10), slider, gbc);

        JPanel ButtonPanel = new JPanel();
        ButtonPanel.setOpaque(false);

        JButton saveButton = new JButton("Save");
        JButton backButton = new JButton("Back");

        saveButton.addActionListener(e -> {
            MF.setContentPane(new MainMenuPanel(MF));
            MF.revalidate();
            MF.repaint();
        });
        backButton.addActionListener(e -> {
            MF.setContentPane(new MainMenuPanel(MF));
            MF.revalidate();
            MF.repaint();
        });

        ButtonPanel.add(backButton);
        ButtonPanel.add(saveButton);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(ButtonPanel, gbc);
    }

    public JTextField getPlayer1Field() {
        return Player1Field;
    }

    public JTextField getPlayer2Field() {
        return Player2Field;
    }

    public JSlider getSlider() {
        return slider;
    }

    public JComboBox<String> getCombo() {
        return combo;
    }
}
