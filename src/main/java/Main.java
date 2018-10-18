import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import block.Floor;
import block.Map;
import config.CharacterConfig;
import config.WindowConfig;
import service.MapService;
import unit.Player;

public class Main extends JPanel implements Runnable, KeyListener {
    Thread thread = null;

    Map map = new Map(WindowConfig.xSize, WindowConfig.ySize);
    Player tky = new Player(CharacterConfig.xPoint, CharacterConfig.yPoint, CharacterConfig.move, CharacterConfig.life,
            KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE, KeyEvent.VK_RIGHT, false, 0);
    Floor mainFloor = new Floor(WindowConfig.xSize + 200, 0, WindowConfig.ySize - 32);
    Floor floor1 = new Floor(WindowConfig.xSize /2, 100, WindowConfig.ySize - 120);
    Floor floor2 = new Floor(WindowConfig.xSize /2, 150, WindowConfig.ySize - 220);
    ArrayList<Floor> floorList = new ArrayList<>();

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

        addFloorList();

        while (thread == currentThread) {
            map.status();
            tky.status();
            MapService.addGravityToPlayer(tky, floorList);
            // System.out.println(tky.getYPoint());
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }
        }
    }

    private void addFloorList() {
        floorList.add(mainFloor);
        floorList.add(floor1);
        floorList.add(floor2);
    }

    /**
     * 画面に物体を表示する。
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        map.print(graphics);
        tky.print(graphics);
        mainFloor.print(graphics);
        floor1.print(graphics);
        floor2.print(graphics);
    }

    /**
     * KeyEvent系(Keyが押された時)
     */
    @Override
    public void keyPressed(KeyEvent event) {
        tky.action(event);
    }

    /**
     * KeyEvent系(Keyが押された時)
     */
    @Override
    public void keyTyped(KeyEvent event) {}

    /**
     * KeyEvent系(Keyが離された時)
     */
    @Override
    public void keyReleased(KeyEvent event) {}

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