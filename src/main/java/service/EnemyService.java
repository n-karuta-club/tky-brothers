package service;

import java.util.ArrayList;
import java.util.Random;

import config.EnemyConfig;
import config.PipeConfig;
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
        addEnemy(PipeConfig.XPoint1, EnemyConfig.yPoint);
        addEnemy(PipeConfig.XPoint1, EnemyConfig.yPoint);
        addEnemy(PipeConfig.XPoint2, EnemyConfig.yPoint);
        addEnemy(PipeConfig.XPoint2, EnemyConfig.yPoint);
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
        switch(random.nextInt(4)) {
        case 0:  return "right";
        case 1:  return "Right";
        case 2:  return "left";
        default:  return "Left";
        }
    }
}
