import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import config.CharacterConfig;
import config.WindowConfig;
import unit.Player;

public class Main extends JPanel implements Runnable, KeyListener {
    Thread thread = null;
    Player tky = new Player(CharacterConfig.xPoint, CharacterConfig.yPoint, CharacterConfig.move, CharacterConfig.life,
            KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE, KeyEvent.VK_RIGHT, false, 0);

    public Main() {
        setPreferredSize(new Dimension(WindowConfig.xSize, WindowConfig.ySize));
        startThread();
        setFocusable(true);
        addKeyListener(this);
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
            tky.jump();
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }
        }
    }

    /**
     * 画面に物体を表示する。
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        tky.print(graphics);
    }

    /**
     * KeyEvent系(Keyが押された時)
     */
    @Override
    public void keyPressed(KeyEvent event) {
        tky.move(event);
    }

    /**
     * KeyEvent系(Keyが押された時)
     */
    @Override
    public void keyTyped(KeyEvent event) {
    }

    /**
     * KeyEvent系(Keyが離された時)
     */
    @Override
    public void keyReleased(KeyEvent event) {
    }

    /**
     * Main文
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tky Brozers");
            frame.add(new Main());
            frame.pack();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }

}