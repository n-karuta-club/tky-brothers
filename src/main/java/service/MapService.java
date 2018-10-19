package service;

import java.util.ArrayList;

import block.Floor;
import config.WindowConfig;
import lombok.val;
import unit.Player;

public class MapService {

    /**
     * playerに重力を与えるメソッド
     * @param player
     * @param floorList
     */
    public static void addGravityToPlayer(Player player, ArrayList<Floor> floorList) {
        boolean gravityFlag = true;

        // それぞれのfloorインスタンスとの接触を判定する
        // どれかのインスタンスと接していればgravityFlagをfalseにする
        for (val floor : floorList) {
            if (!HitService.isPlayerGravity(player, floor)) {
                if (HitService.clashPlayerFloor(player)) {
                    break;
                }
                gravityFlag = false;
                player.setYPoint(floor.getYPoint());
                break;
            }
        }

        // playerに重力を与えるか与えないかの処理
        // 与えなければplayerのjumpFlagをfalseにする
        if (gravityFlag) {
            // System.out.println("gravity");
            player.addGravity();
            player.setJumpFlag(true);
        } else {
            // System.out.println("flag");
            player.setJumpFlag(false);
        }

        if (player.getYPoint() >= WindowConfig.ySize - 64) {
            player.setYPoint(WindowConfig.ySize - 64);
            player.setJumpFlag(false);
        }

        System.out.println(player.getMoveYPoint());
        System.out.println(gravityFlag);
    }
}
