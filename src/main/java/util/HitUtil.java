package util;

import block.Floor;
import unit.Player;

public class HitUtil {
    /**
     * X軸の当たり判定
     * @param player
     * @param floor
     * @return
     */
    public static boolean isXPoint(Player player, Floor floor) {
        if (player.getXPoint() > floor.getXPoint() && player.getXPoint() <= floor.getXPoint() + floor.getXSize()) {
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
    public static boolean isYPoint(Player player, Floor floor) {
        if (player.getYPoint() > floor.getYPoint() - floor.getYSize() * 2 && player.getYPoint() <= floor.getYPoint()) {
            return false;
        }
        return true;
    }
}
