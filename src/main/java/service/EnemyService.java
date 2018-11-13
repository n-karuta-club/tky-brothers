package service;

import java.util.ArrayList;
import java.util.Random;

import config.EnemyConfig;
import lombok.val;
import unit.Enemy;

public class EnemyService {
    public final static ArrayList<Enemy> enemyList = new ArrayList<>();

    /**
     * Enemyをリストに追加するメソッド
     */
    public static void addEnemy(int xPoint, int yPoint) {
        yPoint = 0;
        if (enemyList.size() > EnemyConfig.MaxEnemy) {
            return;
        }
        enemyList.add(new Enemy(xPoint, yPoint, createDirection(), false, 0, 0));
    }

    /**
     *  初期化
     */
    public static void initialize() {
        addEnemy(100, EnemyConfig.yPoint);
        addEnemy(400, EnemyConfig.yPoint);
        addEnemy(500, EnemyConfig.yPoint);
        addEnemy(600, EnemyConfig.yPoint);
    }

    /**
     * リストを空にする
     *
     */
    public static void resetList() {
        enemyList.clear();
    }

    /**
     * リストからenemyを削除する
     * @param index
     */
    public static void removeEnemy(int index) {
        enemyList.remove(index);
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
