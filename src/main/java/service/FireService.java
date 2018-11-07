package service;

import java.util.ArrayList;

import com.sun.glass.events.KeyEvent;

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

    public static void createFire(int xPoint, int yPoint, int lastMove, int playerNumber) {
        val direction = fireDirection(lastMove, playerNumber);
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

    public static void removeFire(int index) {
        fireList.remove(index);
    }

    private static String fireDirection(int lastMove, int playerNumber) {
        val direction = playerDirectionKey(playerNumber);
        if (lastMove == direction) {
            return "left";
        }
        return "right";
    }

    private static int playerDirectionKey(int playerNumber) {
        if (playerNumber == 1) {
            return KeyEvent.VK_LEFT;
        }
        return KeyEvent.VK_A;
    }

}
