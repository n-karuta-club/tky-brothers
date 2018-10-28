package service;

import java.util.ArrayList;
import java.util.Random;

import config.EnemyConfig;
import lombok.val;
import unit.Enemy;

public class EnemyService {
    public static final ArrayList<Enemy> enemyList = new ArrayList<>();

    /**
     * Enemyをリストに追加するメソッド
     */
    public static void addEnemy(int xPoint, int yPoint) {
        enemyList.add(new Enemy(xPoint, yPoint, createDirection()));
    }

    public static void initialize() {
        addEnemy(100, EnemyConfig.yPoint);
        addEnemy(400, EnemyConfig.yPoint);
    }

    /**
     * Enemyの進む方向を決めるメソッド
     * @return
     */
    private static String createDirection() {
        Math.random();
        val random = new Random();

        if (random.nextInt(2) == 0) {
            return "right";
        } else {
            return "left";
        }
    }

}
