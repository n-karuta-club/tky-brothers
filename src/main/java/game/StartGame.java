package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import config.WindowConfig;
import lombok.val;
import service.EnemyService;
import service.FireService;
import service.FloorService;
import service.PlayerService;

public class StartGame extends JPanel implements KeyListener {
    private GameWindow gameWindow;
    private boolean isThisWindow = false;

    /**
     * コンストラクタ
     * @param gameWindow
     */
    public StartGame(GameWindow gameWindow) {
        setPreferredSize(new Dimension(WindowConfig.xSize, WindowConfig.ySize));
        this.gameWindow = gameWindow;
        PlayerService.resetList();
        EnemyService.resetList();
        FireService.resetList();
        FloorService.resetList();
        val button1 = new JButton("1 player");
        val button2 = new JButton("2 player");
        button1.addActionListener(event -> {
            singlePlayerModeSetting();
            isThisWindow = false;
            this.gameWindow.change(new ReadyGame(this.gameWindow));
        });
        button2.addActionListener(event -> {
            twoPlayerModeSetting();
            isThisWindow = false;
            this.gameWindow.change(new ReadyGame(this.gameWindow));
        });
        add(button1);
        add(button2);
        this.gameWindow.addKeyListener(this);
        isThisWindow = true;
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

    /**
     * Keyが押された時
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (!isThisWindow) {
            return;
        }
        switch (e.getKeyCode()) {
        case KeyEvent.VK_S:
            twoPlayerModeSetting();
            isThisWindow = false;
            gameWindow.change(new ReadyGame(gameWindow));
            break;
        case KeyEvent.VK_DOWN:
            singlePlayerModeSetting();
            isThisWindow = false;
            gameWindow.change(new ReadyGame(gameWindow));
        }
    }

    /**
     * Keyをtypeした時
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Keyを離した時
     */
    @Override
    public void keyReleased(KeyEvent e) {}
}
