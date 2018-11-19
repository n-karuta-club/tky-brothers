package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

import block.Map;
import config.WindowConfig;
import music.SoundPlayer;
import service.EnemyService;
import service.FireService;
import service.FloorService;
import service.PlayerService;

public class StartGame extends JPanel implements KeyListener {
    private GameWindow gameWindow;
    private boolean isThisWindow = false;
    private Map map;
    private Clip bgm;
    /**
     * コンストラクタ
     * @param gameWindow
     */
    public StartGame(GameWindow gameWindow) {
        setPreferredSize(new Dimension(WindowConfig.xSize, WindowConfig.ySize));
        this.gameWindow = gameWindow;
        map = new Map();
        PlayerService.resetList();
        EnemyService.resetList();
        FireService.resetList();
        FloorService.resetList();
        this.gameWindow.addKeyListener(this);
        isThisWindow = true;
        this.bgm = SoundPlayer.playStart();
    }

    /**
     * スタート画面
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        map.print(graphics);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(getClass().getResourceAsStream("images/1player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphics.drawImage(bufferedImage, WindowConfig.xSize / 2 - 120, WindowConfig.ySize / 2, WindowConfig.titleXSize, WindowConfig.titleYSize, null);
        try {
            bufferedImage = ImageIO.read(getClass().getResourceAsStream("images/2player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphics.drawImage(bufferedImage, WindowConfig.xSize / 2 - 120, WindowConfig.ySize / 2 + 50, WindowConfig.titleXSize, WindowConfig.titleYSize, null);
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
            this.bgm.stop();
            break;
        case KeyEvent.VK_DOWN:
            singlePlayerModeSetting();
            isThisWindow = false;
            gameWindow.change(new ReadyGame(gameWindow));
            this.bgm.stop();
        }
    }

    /**
     * Keyをtypeした時
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Keyを離した時
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
