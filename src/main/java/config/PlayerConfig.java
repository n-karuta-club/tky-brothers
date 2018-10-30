package config;

import java.awt.event.KeyEvent;

public class PlayerConfig {
    public static final int move = 5;
    public static final int life  = 3;
    public static final int xSize = 32;
    public static final int ySize = 32;
    public static final int xPoint = WindowConfig.xSize / 2;
    public static final int yPoint = WindowConfig.ySize - 64;
    public static final int jumpPoint = 15;

    private static boolean isUP = false;
    private static boolean isDOWN = false;
    private static boolean isLEFT = false;
    private static boolean isRIGHT = false;
    private static boolean isW = false;
    private static boolean isS = false;
    private static boolean isA = false;
    private static boolean isD = false;

    public static boolean isPressing(int iKeyCode) {
        switch (iKeyCode) {
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

    public static void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
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
}