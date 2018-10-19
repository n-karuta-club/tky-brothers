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
import config.FloorConfig;
import config.WindowConfig;
import service.MapService;
import unit.Enemy;
import unit.Player;

public class Main extends JPanel implements Runnable, KeyListener {
    Thread thread = null;
    ArrayList<Floor> floorList = new ArrayList<>();

    Map map = new Map(WindowConfig.xSize, WindowConfig.ySize);
    Player tky = new Player(CharacterConfig.xPoint, CharacterConfig.yPoint, CharacterConfig.move, CharacterConfig.life,
            KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE, KeyEvent.VK_RIGHT, false, 0, 0);
    Floor mainFloor = new Floor(-10, WindowConfig.ySize - 32, WindowConfig.xSize + 100, FloorConfig.ySize);
    Floor floor1 = new Floor(-10, WindowConfig.ySize - 150, WindowConfig.xSize / 2, FloorConfig.ySize);
    Floor floor2 = new Floor(100, WindowConfig.ySize - 200, WindowConfig.xSize / 2, FloorConfig.ySize);

    Enemy enemy1 = new Enemy(100, CharacterConfig.yPoint, "left");
    Enemy enemy2 = new Enemy(100, CharacterConfig.yPoint, "right");

    /**
     * コンストラクタ
     */
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
            enemy1.status();
            enemy2.status();
            MapService.addGravityToPlayer(tky, floorList);
            // System.out.println(tky.getYPoint());
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
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
        map.print(graphics);
        tky.print(graphics);
        mainFloor.print(graphics);
        floor1.print(graphics);
        floor2.print(graphics);
        enemy1.print(graphics);
        enemy2.print(graphics);
    }

    /**
     *  床オブジェクトのリストを作成する
     */
    private void addFloorList() {
        floorList.add(mainFloor);
        floorList.add(floor1);
        floorList.add(floor2);
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