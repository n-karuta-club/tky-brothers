package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.sampled.Clip;
import javax.swing.JPanel;

import block.Score;
import block.Timer;
import config.WindowConfig;
import lombok.val;
import music.SoundControl;
import service.ParserService;
import service.ScoreService;

public class ResultGame extends JPanel implements Runnable, KeyListener {
    private boolean isThisWindow = false;
    private GameWindow gameWindow;
    private Timer timer;
    private Thread thread = null;
    private Score score;
    private ArrayList<HashMap<String, String>> hash;
    private boolean isConnectionScoreServer = true;
    private Clip bgm;

    /**
     * コンストラクタ
     *
     * @param gameWindow
     * @param score
     */
    public ResultGame(GameWindow gameWindow, Score score) {
        setPreferredSize(new Dimension(WindowConfig.xSize, WindowConfig.ySize));
        // val startButton = new JButton("タイトルへ戻る");
        // val scoreLabel = new JLabel(String.valueOf(score.getPoint() + score.remainLives()));

        this.gameWindow = gameWindow;
        this.score = score;
        this.timer = new Timer(3, 0, WindowConfig.xSize - 50, WindowConfig.ySize - 50);
        this.bgm = SoundControl.play(getClass().getResource("../resource/sound/result.wav"));

        // スコアをサーバに送る
        isConnectionScoreServer = ScoreService.postScore(score.getPoint());

        // スコアサーバとつながっていた場合、スコアをサーバから受け取る
        if (isConnectionScoreServer) {
            hash = ParserService.jsonToHashMap(ScoreService.getScore());
        }

        System.out.println(hash);

        //  startButton.addActionListener(event -> {
        //      if (isThisWindow) {
        //          isThisWindow = false;
        //          this.gameWindow.change(new StartGame(this.gameWindow));
        //      }
        //  });

        // add(startButton);
        // add(scoreLabel);
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
        // 背景
        graphics.fillRect(0, 0, WindowConfig.xSize, WindowConfig.ySize);
        val scoreFont = new Font("impact", Font.PLAIN, WindowConfig.fontSize);
        val rankingFont = new Font("impact", Font.PLAIN, WindowConfig.fontSize / 2);
        val timerFont = new Font("impact", Font.PLAIN, WindowConfig.fontSize / 2);

        // スコアサーバと接続できている場合とできていない場合での表示わけ
        if (isConnectionScoreServer) {
            // 自分のスコア
            graphics.setColor(Color.WHITE);
            graphics.setFont(scoreFont);
            graphics.drawString("ID: " + String.valueOf(hash.size()),
                    WindowConfig.xSize / 2 - WindowConfig.fontSize, WindowConfig.ySize / 2);
            graphics.drawString("SCORE: " + String.valueOf(score.getPoint() + score.remainLives()),
                    WindowConfig.xSize / 2 - WindowConfig.fontSize, WindowConfig.ySize / 2 + WindowConfig.fontSize);

            // ランキングのスコア
            graphics.setFont(rankingFont);
            graphics.drawString("ID", WindowConfig.xSize / 2 - 300,
                    150);
            graphics.drawString("SCORE", WindowConfig.xSize / 2 - 250,
                    150);
            for (int index = 0; index < 5; index++) {
                int i = 0;
                for (val entry : hash.get(index).entrySet()) {
                    graphics.drawString(entry.getValue(), WindowConfig.xSize / 2 - 300 + i * 50,
                            200 + 50 * index);
                    i++;
                    // graphics.drawString(entry.getValue(), WindowConfig.xSize / 2 - 250,
                    //        100 + 50 * index);
                    // break;
                }
            }
        } else {
            // 自分のスコア
            graphics.setColor(Color.WHITE);
            graphics.setFont(scoreFont);
            graphics.drawString("SCORE: " + String.valueOf(score.getPoint() + score.remainLives()),
                    WindowConfig.xSize / 2 - WindowConfig.fontSize * 2, WindowConfig.ySize / 2);
        }

        graphics.setFont(timerFont);
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
            bgm.stop();
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
