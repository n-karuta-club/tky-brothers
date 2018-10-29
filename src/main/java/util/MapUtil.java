package util;

import block.Floor;
import unit.Player;

public class MapUtil {
    /**
     * playerインスタンスに重力を与えるか与えないか判定するメソッド
     * @param player
     * @param floor
     * @return
     */
    public static boolean isPlayerGravity(Player player, Floor floor) {
        if (HitUtil.isYPoint(player, floor) || HitUtil.isXPoint(player, floor)) {
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
}
