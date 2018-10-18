package service;

import block.Floor;
import config.CharacterConfig;
import unit.Player;

public class HitService {
    public static boolean isPlayerGravity(Player player, Floor floor) {
        // if (isXPoint(player, floor) || isYPoint(player, floor)) {
        if (isYPoint(player, floor) || isXPoint(player, floor)) {
        // if (isYPoint(player, floor)) {
        // if (isXPoint(player, floor)) {
            return true;
        }
        return false;
    }

    private static boolean isXPoint(Player player, Floor floor) {
        if (player.getXPoint() > floor.getXPoint() && player.getXPoint() <= floor.getXPoint() + floor.getXSize()) {
            return false;
        }
        return true;
    }

    private static boolean isYPoint(Player player, Floor floor) {
        System.out.println("player" + player.getYPoint());
        System.out.println("floor" + floor.getYPoint());
        if (player.getYPoint() != floor.getYPoint() - CharacterConfig.ySize) {
            return true;
        }
        return false;
    }
}
