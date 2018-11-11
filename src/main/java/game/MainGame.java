package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import block.Floor;
import block.Score;
import block.Timer;
import config.PlayerConfig;
import config.WindowConfig;
import lombok.val;
import service.EnemyService;
import service.FireService;
import service.FloorService;
import service.HitService;
import service.MapService;
import service.PlayerService;
import unit.Enemy;
import unit.Fire;
import unit.Player;

public class MainGame extends JPanel implements Runnable, KeyListener {
    private Thread thread = null;
    private GameWindow gameWindow;
    private boolean isThisWindow = false;

    private ArrayList<Player> playerList = PlayerService.playerList;
    private ArrayList<Floor> floorList = FloorService.floorList;
    private ArrayList<Enemy> enemyList = EnemyService.enemyList;
    private ArrayList<Fire> fireList = FireService.fireList;
    private Timer timer = new Timer(WindowConfig.gameTime, 0, 50, 50);
    private Score score = new Score();

    public MainGame(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        startThread();
        isThisWindow = true;
        gameWindow.addKeyListener(this);
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
        boolean lifeFlag = false;

        while (thread == currentThread) {
            for (val player: playerList) {
                player.status();
                MapService.addGravityToPlayer(player, floorList);
                if (HitService.isHitPlayerToEnemy(player, enemyList)) {
                    player.damage();
                }
                if (player.getLife() <= 0) {
                    lifeFlag = true;
                    break;
                }
            };
            enemyList.forEach(enemy -> {
                enemy.status();
                MapService.addGravityToEnemy(enemy, floorList);
            });
            fireList.forEach(fire -> {
                fire.status();
            });
            timer.status();
            if (timer.stateNowTime() || lifeFlag) {
                isThisWindow = false;
                gameWindow.change(new ResultGame(gameWindow, score));
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
        fireList.forEach(fire -> {
            fire.print(graphics);
        });
        for (int index = 0; index < fireList.size(); index++) {
            val fire = fireList.get(index);
            if (HitService.isHitFireToWindow(fire)) {
                FireService.removeFire(index);
            }
            if (HitService.isHitFireToEnemy(fire, enemyList)) {
                score.breakEnemy();
                FireService.removeFire(index);
            }
        }
        timer.print(graphics);
        score.print(graphics);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (!isThisWindow) {
            return;
        }
        PlayerConfig.keyPressed(keyEvent);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (!isThisWindow) {
            return;
        }
        PlayerConfig.keyReleased(keyEvent);
    }
}
