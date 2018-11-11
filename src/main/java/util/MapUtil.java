package util;

import block.Floor;
import unit.Enemy;
import unit.Player;

public class MapUtil {
    /**
     * playerインスタンスに重力を与えるか与えないか判定するメソッド
     * @param player
     * @param floor
     * @return
     */
    public static boolean isPlayerGravity(Player player, Floor floor) {
        return (HitUtil.isPlayerYPoint(player, floor) || HitUtil.isPlayerXPoint(player, floor));
    }

    /**
     * playerが床と設置しているか判定するメソッド
     * @param player
     */
    public static boolean clashPlayerFloor(Player player) {
        return player.getMoveYPoint() < 0;
    }

    /**
     * 敵が床と設置しているか判定するメソッド
     *
     * @param enemy
     * @return
     */
    public static boolean clashEnemyFloor(Enemy enemy) {
        return enemy.getMoveYPoint() < 0;
    }

    /**
     * 敵に重力を与えるか判定するメソッド
     *
     * @param enemy
     * @param floor
     * @return
     */
    public static boolean isEnemyGravity(Enemy enemy, Floor floor) {
        return (HitUtil.isEnemyYPoint(enemy, floor) || HitUtil.isEnemyXPoint(enemy, floor));
    }
}
