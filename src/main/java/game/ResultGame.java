package game;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import block.Score;
import config.WindowConfig;
import lombok.val;
import service.EnemyService;
import service.FireService;
import service.FloorService;
import service.PlayerService;

public class ResultGame extends JPanel implements KeyListener {
    private boolean isThisWindow = false;
    private GameWindow gameWindow;

    public ResultGame(GameWindow gameWindow, Score score) {
        setPreferredSize(new Dimension(WindowConfig.xSize, WindowConfig.ySize));
        val startButton = new JButton("タイトルへ戻る");
        val scoreLabel = new JLabel(String.valueOf(score.getPoint()));
        isThisWindow = true;
        this.gameWindow = gameWindow;
        PlayerService.resetList();
        EnemyService.resetList();
        FireService.resetList();
        FloorService.resetList();

        startButton.addActionListener(event -> {
            isThisWindow = false;
            this.gameWindow.change(new StartGame(this.gameWindow));
        });

        add(startButton);
        add(scoreLabel);
        this.gameWindow.addKeyListener(this);
        isThisWindow = true;
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
        case KeyEvent.VK_DOWN:
            isThisWindow = false;
            gameWindow.change(new StartGame(gameWindow));
        }
    }

    /**
     * Keyをtypeした時
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Keyを話した時
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
