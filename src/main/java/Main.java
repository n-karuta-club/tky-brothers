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

    public void startThread() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

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

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        tky.print(graphics);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        tky.move(event);
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }

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