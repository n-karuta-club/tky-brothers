package service;

import java.util.ArrayList;

import config.FireConfig;
import config.PlayerConfig;
import lombok.val;
import unit.Fire;

public class FireService {
    public static final ArrayList<Fire> fireList = new ArrayList<>();
    private static int playerFireNum = 0;

    /**
     * リストにFireを追加するメソッド
     * @param xPoint
     * @param yPoint
     * @param moveDirection
     * @param playerNumber
     */
    public static void addFireList(int xPoint, int yPoint, String moveDirection, int playerNumber) {
        fireList.add(new Fire(xPoint, yPoint + PlayerConfig.ySize / 2, moveDirection, playerNumber));
    }

    /**
     * リストを空にするメソッド
     */
    public static void resetList() {
        fireList.clear();
    }

    /**
     * ファイアを追加する
     *
     * @param xPoint
     * @param yPoint
     * @param lastMove
     * @param playerNumber
     */
    public static void createFire(int xPoint, int yPoint, int lastMove, int playerNumber) {
        val direction = PlayerConfig.playerDirection(lastMove, playerNumber);
        playerFireNum = 0;

        fireList.forEach(fire -> {
            if (fire.getPlayerNumber() == playerNumber) {
                playerFireNum++;
            }
        });

        if (playerFireNum < FireConfig.max) {
            addFireList(xPoint, yPoint, direction, playerNumber);
        }
    }

    /**
     * リストから要素を削除する
     *
     * @param index
     */
    public static void removeFire(int index) {
        fireList.remove(index);
    }

}
