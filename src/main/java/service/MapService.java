package service;

import java.util.ArrayList;

import block.Floor;
import config.WindowConfig;
import lombok.val;
import unit.Player;

public class MapService {

    public static void addGravityToPlayer(Player player, ArrayList<Floor> floorList) {
        boolean gravityFlag = false;

        for (val floor : floorList) {
            if (!HitService.isPlayerGravity(player, floor)) {
                gravityFlag = true;
                break;
            }
        }

        if (!gravityFlag) {
            System.out.println("gravity");
            player.addGravity();
            player.setJumpFlag(true);
        } else {
            System.out.println("flag");
            player.setJumpFlag(false);
        }

        if (player.getYPoint() >= WindowConfig.ySize - 32) {
            player.setYPoint(WindowConfig.ySize - 64);
            player.setJumpFlag(false);
        }

    }

}
