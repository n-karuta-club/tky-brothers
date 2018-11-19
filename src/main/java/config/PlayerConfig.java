package config;

import java.awt.event.KeyEvent;

import lombok.val;

public class PlayerConfig {
    public static final int move = 5;
    public static final int life = 3;
    public static final int xSize = 64;
    public static final int ySize = 64;
    public static final int xPoint = WindowConfig.xSize / 2;
    public static final int yPoint = WindowConfig.ySize - 64;
    public static final int jumpPoint = 15;
    public static final int damageTime = 3;
    public static final int lifePositionX = 20;
    public static final int lifePositionY = 25;


    private static boolean isUP = false;
    private static boolean isDOWN = false;
    private static boolean isLEFT = false;
    private static boolean isRIGHT = false;
    private static boolean isW = false;
    private static boolean isS = false;
    private static boolean isA = false;
    private static boolean isD = false;

    /**
     * 全てのPlayerキーを押されていない状態にするメソッド
     */
    public static void setAllKeyFalse() {
        isUP = false;
        isDOWN = false;
        isLEFT = false;
        isRIGHT = false;
        isW = false;
        isS = false;
        isA = false;
        isD = false;
    }

    /**
     * キーが押されているか否かの判定
     *
     * @param iKeyCode
     * @return
     */
    public static boolean isPressing(int KeyCode) {
        switch (KeyCode) {
        case KeyEvent.VK_UP:
            return isUP;
        case KeyEvent.VK_DOWN:
            return isDOWN;
        case KeyEvent.VK_LEFT:
            return isLEFT;
        case KeyEvent.VK_RIGHT:
            return isRIGHT;
        case KeyEvent.VK_W:
            return isW;
        case KeyEvent.VK_S:
            return isS;
        case KeyEvent.VK_A:
            return isA;
        case KeyEvent.VK_D:
            return isD;
        }
        return false;
    }

    /**
     * キーを離したかどうかの判定
     *
     * @param e
     */
    public static void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
        case KeyEvent.VK_UP:
            isUP = true;
            break;
        case KeyEvent.VK_DOWN:
            isDOWN = true;
            break;
        case KeyEvent.VK_LEFT:
            isLEFT = true;
            break;
        case KeyEvent.VK_RIGHT:
            isRIGHT = true;
            break;
        case KeyEvent.VK_W:
            isW = true;
            break;
        case KeyEvent.VK_S:
            isS = true;
            break;
        case KeyEvent.VK_A:
            isA = true;
            break;
        case KeyEvent.VK_D:
            isD = true;
            break;
        }
    }

    public static void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            isUP = false;
            break;
        case KeyEvent.VK_DOWN:
            isDOWN = false;
            break;
        case KeyEvent.VK_LEFT:
            isLEFT = false;
            break;
        case KeyEvent.VK_RIGHT:
            isRIGHT = false;
            break;
        case KeyEvent.VK_W:
            isW = false;
            break;
        case KeyEvent.VK_S:
            isS = false;
            break;
        case KeyEvent.VK_A:
            isA = false;
            break;
        case KeyEvent.VK_D:
            isD = false;
            break;
        }
    }

    /**
     * playerの左移動キーの番号を返却する
     *
     * @param playerNumber
     * @return
     */
    public static int playerDirectionKey(int playerNumber) {
        if (playerNumber == 1) {
            return KeyEvent.VK_LEFT;
        }
        return KeyEvent.VK_A;
    }

    /**
     * playerの向いている方向を返却する
     *
     * @param playerNumber
     * @return
     */
    public static String playerDirection(int lastMove, int playerNumber) {
        val direction = playerDirectionKey(playerNumber);
        if (lastMove == direction) {
            return "left";
        }
        return "right";
    }
}