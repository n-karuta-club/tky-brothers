package util;

import block.Floor;
import config.EnemyConfig;
import config.FireConfig;
import config.PlayerConfig;
import unit.Enemy;
import unit.Fire;
import unit.Player;

public class HitUtil {
    /**
     * X軸の当たり判定
     * @param player
     * @param floor
     * @return
     */
    public static boolean isPlayerXPoint(Player player, Floor floor) {
        if (player.getXPoint() > floor.getXPoint() - PlayerConfig.xSize && player.getXPoint() <= floor.getXPoint() + floor.getXSize()) {
            return false;
        }
        return true;
    }

    /**
     * Y軸の当たり判定
     * @param player
     * @param floor
     * @return
     */
    public static boolean isPlayerYPoint(Player player, Floor floor) {
        if (player.getYPoint() > floor.getYPoint() - floor.getYSize() && player.getYPoint() <= floor.getYPoint()) {
            return false;
        }
        return true;
    }

    /**
     * X軸の当たり判定
     * @param player
     * @param floor
     * @return
     */
    public static boolean isEnemyXPoint(Enemy enemy, Floor floor) {
        if (enemy.getXPoint() > floor.getXPoint() - PlayerConfig.xSize && enemy.getXPoint() <= floor.getXPoint() + floor.getXSize()) {
            return false;
        }
        return true;
    }

    /**
     * Y軸の当たり判定
     * @param player
     * @param floor
     * @return
     */
    public static boolean isEnemyYPoint(Enemy enemy, Floor floor) {
        if (enemy.getYPoint() > floor.getYPoint() - floor.getYSize() && enemy.getYPoint() <= floor.getYPoint()) {
            return false;
        }
        return true;
    }

    /**
     * fireとenemyの当たり判定
     *
     * @param fire
     * @param enemy
     * @return
     */
    public static boolean isHitFireToEnemy(Fire fire, Enemy enemy) {
        if (isHitFireXPoint(fire, enemy) && isHitFireYPoint(fire, enemy)) {
            return true;
        }
        return false;
    }

    /**
     * fireとenemyの当たり判定(X軸)
     *
     * @param fire
     * @param enemy
     * @return
     */
    private static boolean isHitFireXPoint(Fire fire, Enemy enemy) {
        // fire 原点座標からの当たり判定
        if (enemy.getXPoint() < fire.getXPoint() && enemy.getXPoint() + EnemyConfig.xSize > fire.getXPoint()) {
            return true;
        }
        // fire 最大値からの当たり判定
        if (enemy.getXPoint() < fire.getXPoint() + FireConfig.xSize && enemy.getXPoint() + EnemyConfig.xSize > fire.getXPoint() + FireConfig.xSize) {
            return true;
        }
        return false;
    }

    /**
     * fireとenemyの当たり判定(Y軸)
     *
     * @param fire
     * @param enemy
     * @return
     */
    private static boolean isHitFireYPoint(Fire fire, Enemy enemy) {
        // fire 原点座標からの当たり判定
        if (enemy.getYPoint() < fire.getYPoint() && enemy.getYPoint() + EnemyConfig.ySize > fire.getYPoint()) {
            return true;
        }
        // fire 最大値からの当たり判定
        if (enemy.getYPoint() < fire.getYPoint() + FireConfig.ySize && enemy.getYPoint() + EnemyConfig.ySize > fire.getYPoint() + FireConfig.ySize) {
            return true;
        }
        return false;
    }
}
