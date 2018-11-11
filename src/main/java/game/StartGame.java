package game;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import config.WindowConfig;
import lombok.val;
import service.EnemyService;
import service.FloorService;
import service.PlayerService;

public class StartGame extends JPanel {
    /**
     * コンストラクタ
     * @param gameWindow
     */
    public StartGame(GameWindow gameWindow) {
        setPreferredSize(new Dimension(WindowConfig.xSize, WindowConfig.ySize));
        val button1 = new JButton("1 player");
        val button2 = new JButton("2 player");
        button1.addActionListener(event -> {
            singlePlayerModeSetting();
            gameWindow.change(new ReadyGame(gameWindow));
        });
        button2.addActionListener(event -> {
            twoPlayerModeSetting();
            gameWindow.change(new ReadyGame(gameWindow));
        });
        add(button1);
        add(button2);
    }

    /**
     * スタート画面
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawString("タイトル画面", WindowConfig.xSize / 2 - 50, 200);
    }

    /**
     * 一人プレー用の初期設定
     */
    private void singlePlayerModeSetting() {
        PlayerService.addFirstPlayer();
        FloorService.initialize();
        EnemyService.initialize();
    }

    /**
     * 二人プレー用の初期設定
     */
    private void twoPlayerModeSetting() {
        PlayerService.addFirstPlayer();
        PlayerService.addSecondPlayer();
        FloorService.initialize();
        EnemyService.initialize();
    }
}
