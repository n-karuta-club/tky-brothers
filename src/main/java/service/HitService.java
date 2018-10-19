package service;

import block.Floor;
import unit.Player;

public class HitService {
    /**
     * playerインスタンスに重力を与えるか与えないか判定するメソッド
     * @param player
     * @param floor
     * @return
     */
    public static boolean isPlayerGravity(Player player, Floor floor) {
        if (isYPoint(player, floor) || isXPoint(player, floor)) {
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
            System.out.println("hoge");
            player.reverseMoveYPoint();
            return true;
        }
        return false;
    }

    public static boolean isPlayerFloor(Player player, Floor floor) {
        return true;
    }

    /**
     * X軸の判定
     * @param player
     * @param floor
     * @return
     */
    private static boolean isXPoint(Player player, Floor floor) {
        if (player.getXPoint() > floor.getXPoint() && player.getXPoint() <= floor.getXPoint() + floor.getXSize()) {
            return false;
        }
        return true;
    }

    /**
     * Y軸の判定
     * @param player
     * @param floor
     * @return
     */
    private static boolean isYPoint(Player player, Floor floor) {
        // System.out.println("player" + player.getYPoint());
        //  System.out.println("floor" + floor.getYPoint());
        // if (player.getYPoint() != floor.getYPoint() - CharacterConfig.ySize) {
        //     return true;
        // }
        if (player.getYPoint() > floor.getYPoint() - floor.getYSize() * 2 && player.getYPoint() <= floor.getYPoint()) {
            return false;
        }
        return true;
    }
}
