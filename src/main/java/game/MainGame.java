package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import block.Floor;
import config.WindowConfig;
import service.EnemyService;
import service.FloorService;
import service.MapService;
import service.PlayerService;
import unit.Enemy;
import unit.Player;

public class MainGame extends JPanel implements Runnable, KeyListener {
    Thread thread = null;

    private ArrayList<Player> playerList = PlayerService.playerList;
    private ArrayList<Floor>  floorList = FloorService.floorList;
    private ArrayList<Enemy> enemyList = EnemyService.enemyList;

    public MainGame() {
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
            playerList.forEach(player -> {
                player.status();
                MapService.addGravityToPlayer(player, floorList);
            });
            enemyList.forEach(enemy -> {
                enemy.status();
            });
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * オブジェクトの描写
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        playerList.forEach(player -> {
            player.print(graphics);
        });
        floorList.forEach(floor -> {
            floor.print(graphics);
        });
        enemyList.forEach(enemy -> {
            enemy.print(graphics);
        });
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        playerList.forEach(player -> {
            player.action(keyEvent);
        });
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyReleased(KeyEvent keyEvent) {}
}
