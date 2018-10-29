package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import block.Floor;
import service.EnemyService;
import service.FloorService;
import service.MapService;
import service.PlayerService;
import unit.Enemy;
import unit.Player;

public class MainGame extends JPanel implements Runnable, KeyListener {
    private Thread thread = null;
    // private GameWindow gameWindow;

    private ArrayList<Player> playerList = PlayerService.playerList;
    private ArrayList<Floor>  floorList = FloorService.floorList;
    private ArrayList<Enemy> enemyList = EnemyService.enemyList;

    public MainGame(GameWindow gameWindow) {
        // this.gameWindow = gameWindow;
        startThread();
        gameWindow.addKeyListener(this);
        setFocusable(true);
        System.out.println("hoge");
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
