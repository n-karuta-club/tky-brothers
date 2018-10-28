package game;

import javax.swing.JFrame;
import javax.swing.JPanel;

import config.WindowConfig;

public class GameWindow extends JFrame {
    public GameWindow() {
        super("tky-brozers");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WindowConfig.xSize, WindowConfig.ySize);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void change(JPanel panel) {
        getContentPane().removeAll();
        super.add(panel);
        validate();
        repaint();
    }
}
