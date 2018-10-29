package game;

import javax.swing.JFrame;
import javax.swing.JPanel;

import config.WindowConfig;

public class GameWindow extends JFrame {
    /**
     * 画面の設定
     */
    public GameWindow() {
        super("tky-brozers");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WindowConfig.xSize, WindowConfig.ySize);
        setLocationRelativeTo(null);
        setFocusable(true);
        setResizable(false);
    }

    /**
     * 画面遷移を行うためのメソッド
     * @param panel
     */
    public void change(JPanel panel) {
        getContentPane().removeAll();
        super.add(panel);
        validate();
        repaint();
    }
}
