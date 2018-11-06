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
        if (HitUtil.isPlayerYPoint(player, floor) || HitUtil.isPlayerXPoint(player, floor)) {
            return true;
        }
        return false;
    }

    /**
     * playerが上昇中であった場合下降する処理に置き換えるメソッド
     * @param player
     */
    public static boolean clashPlayerFloor(Player player) {
        if (player.getMoveYPoint() < 0) {
            // player.reverseMoveYPoint();
            return true;
        }
        return false;
    }

    public static boolean clashEnemyFloor(Enemy enemy) {
        if (enemy.getMoveYPoint() < 0) {
            // player.reverseMoveYPoint();
            return true;
        }
        return false;
    }

    public static boolean isEnemyGravity(Enemy enemy, Floor floor) {
        if (HitUtil.isEnemyYPoint(enemy, floor) || HitUtil.isEnemyXPoint(enemy, floor)) {
            return true;
        }
        return false;
    }
}
