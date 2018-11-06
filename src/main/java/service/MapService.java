package service;

import java.util.ArrayList;

import block.Floor;
import config.FloorConfig;
import config.PlayerConfig;
import config.WindowConfig;
import lombok.val;
import unit.Enemy;
import unit.Player;
import util.MapUtil;

public class MapService {

    /**
     * playerに重力を与えるメソッド
     * @param player
     * @param floorList
     */
    public static void addGravityToPlayer(Player player, ArrayList<Floor> floorList) {
        boolean gravityFlag = true;

        // それぞれのfloorインスタンスとの接触を判定する
        // どれかのfloorと接していればgravityFlagをfalseにする
        for (val floor : floorList) {
            if (!MapUtil.isPlayerGravity(player, floor)) {
                if (MapUtil.clashPlayerFloor(player)) {
                    // System.out.println("Under Hit!!!!!!!!!!");
                    gravityFlag = true;
                    break;
                }
                gravityFlag = false;
                player.setYPoint(floor.getYPoint());
                // System.out.println("player no hit");
                break;
            }
        }
        //  System.out.println(player.getPreviewYPoint());
        //  System.out.println(player.isJumpFlag());

        // playerに重力を与えるか与えないかの処理
        // 与えなければplayerのjumpFlagをfalseにする
        if (gravityFlag) {
            player.addGravity();
            player.setJumpFlag(true);
        } else {
            player.setYPoint(player.getYPoint() - FloorConfig.ySize);
            player.setJumpFlag(false);
        }

        // playerが上下運動をしながら待機している時はjumpFlagをfalseにする
        floorList.forEach(floor -> {
            // System.out.println(floor.getYPoint() - floor.getYSize() + ":" + (player.getYPoint() + 1));
            if (floor.getYPoint() - floor.getYSize() == (player.getYPoint() - 1)) {
                player.setJumpFlag(false);
            }
        });

        // 一番下の地面に着地しておく用
        if (player.getYPoint() >= WindowConfig.ySize - PlayerConfig.ySize * 2) {
            player.setJumpFlag(false);
        }
    }

    /**
     * enemyに重力を与える処理
     *
     * @param enemy
     * @param floorList
     */
    public static void addGravityToEnemy(Enemy enemy, ArrayList<Floor> floorList) {
        boolean enemygravityFlag = true;

        // それぞれのfloorインスタンスとの接触を判定する
        // どれかのfloorと接していればgravityFlagをfalseにする
        for (val floor : floorList) {
            if (!MapUtil.isEnemyGravity(enemy, floor)) {
                if (MapUtil.clashEnemyFloor(enemy)) {
                    System.out.println("Under Hit!!!!!!!!!!");
                    enemygravityFlag = true;
                    break;
                }
                enemygravityFlag = false;
                enemy.setYPoint(floor.getYPoint());
                System.out.println("enemy no hit");
                break;
            }
        }

        // System.out.println(enemy.getPreviewYPoint());
        // System.out.println(enemy.isJumpFlag());
        // System.out.println(enemy.getXPoint() + ":" + enemy.getYPoint());
        System.out.println(enemy.getMoveYPoint());
        System.out.println(enemy.getXPoint() + ":" + enemy.getYPoint());

        // enemyに重力を与えるか与えないかの処理
        // 与えなければenemyのjumpFlagをfalseにする
        if (enemygravityFlag) {
            enemy.addGravity();
            enemy.setJumpFlag(true);
            System.out.println("grabity true");
        } else {
            enemy.setYPoint(enemy.getYPoint() - FloorConfig.ySize);
            enemy.setJumpFlag(false);
            System.out.println("gravity false");
        }

        // playerが上下運動をしながら待機している時はjumpFlagをfalseにする
        floorList.forEach(floor -> {
            // System.out.println(floor.getYPoint() - floor.getYSize() + ":" + (enemy.getYPoint() + 1));
            if (floor.getYPoint() - floor.getYSize() == (enemy.getYPoint() - 1)) {
                enemy.setJumpFlag(false);
            }
        });

        // 一番下の地面に着地しておく用
        if (enemy.getYPoint() >= WindowConfig.ySize - PlayerConfig.ySize * 2) {
            System.out.println("gravity false 2");
            enemy.setJumpFlag(false);
        }
    }

}
