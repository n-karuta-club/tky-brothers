package game;

import javax.swing.JFrame;
import javax.swing.JPanel;

import config.PlayerConfig;
import config.WindowConfig;
import service.EnemyService;
import service.FireService;
import service.FloorService;
import service.PlayerService;

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
        System.out.println("================");
        System.out.println(PlayerService.playerList);
        System.out.println(EnemyService.enemyList);
        System.out.println(FloorService.floorList);
        System.out.println(FireService.fireList);
        System.out.println("================");
        PlayerConfig.setAllKeyFalse();
        getContentPane().removeAll();
        super.add(panel);
        validate();
        repaint();
    }
}
