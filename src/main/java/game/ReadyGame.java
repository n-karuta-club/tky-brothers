package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import block.Timer;
import config.WindowConfig;
import lombok.val;

public class ReadyGame extends JPanel implements Runnable {
    private Timer timer;
    private Thread thread = null;
    private GameWindow gameWindow;

    /**
     * コンストラクタ
     * @param gameWindow
     */
    public ReadyGame(GameWindow gameWindow) {
        setPreferredSize(new Dimension(WindowConfig.xSize, WindowConfig.ySize));
        timer = new Timer(3, 0, WindowConfig.xSize / 2, WindowConfig.ySize / 2);
        this.gameWindow = gameWindow;
        thread = null;
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
                gameWindow.change(new MainGame(gameWindow));
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
     * スタート画面
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.fillRect(0, 0, WindowConfig.xSize, WindowConfig.ySize);
        // 80はフォントサイズ
        val font = new Font("impact", Font.PLAIN, WindowConfig.fontSize);
        graphics.setColor(Color.WHITE);
        graphics.setFont(font);
        timer.print(graphics);
    }
}
