package util;

import block.Floor;
import config.PlayerConfig;
import unit.Enemy;
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
}
