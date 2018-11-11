package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import block.Score;
import block.Timer;
import config.WindowConfig;
import lombok.val;
import service.ScoreService;

public class ResultGame extends JPanel implements Runnable, KeyListener {
    private boolean isThisWindow = false;
    private GameWindow gameWindow;
    private Timer timer;
    private Thread thread = null;

    public ResultGame(GameWindow gameWindow, Score score) {
        setPreferredSize(new Dimension(WindowConfig.xSize, WindowConfig.ySize));
        val startButton = new JButton("タイトルへ戻る");
        val scoreLabel = new JLabel(String.valueOf(score.getPoint()));
        this.gameWindow = gameWindow;
        this.timer = new Timer(3, 0, WindowConfig.xSize - 50, WindowConfig.ySize - 50);

        // スコアをサーバに送る
        ScoreService.postScore(score.getPoint());

        startButton.addActionListener(event -> {
            if (isThisWindow) {
                isThisWindow = false;
                this.gameWindow.change(new StartGame(this.gameWindow));
            }
        });

        add(startButton);
        add(scoreLabel);
        this.gameWindow.addKeyListener(this);
        startThread();
    }

    /**
     * プログラム実行時のThreadを取得し、Threadをスタートする。
     */
    public void startThread() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * プログラムを実行する。
     */
    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();

        while (thread == currentThread) {
            timer.status();
            if (timer.stateNowTime()) {
                isThisWindow = true;
                break;
            }
            repaint();
            try {
                Thread.sleep(WindowConfig.sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * タイマー表示画面
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (timer.getNowTime() > 0) {
            timer.print(graphics);
        }
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
        case KeyEvent.VK_DOWN:
        case KeyEvent.VK_S:
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
