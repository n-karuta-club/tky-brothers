package service;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import config.PlayerConfig;
import unit.Player;

public class PlayerService {
    public final static ArrayList<Player> playerList = new ArrayList<>();

    /**
     * player1のインスタンスを生成
     */
    public static void addFirstPlayer() {
        playerList.add(new Player(PlayerConfig.xPoint - 100, PlayerConfig.yPoint, PlayerConfig.move, PlayerConfig.life,
                KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, false, 0, 0, 1, false, 0));
    }

    /**
     * player2のインスタンスを生成
     */
    public static void addSecondPlayer() {
        playerList.add(new Player(PlayerConfig.xPoint + 100, PlayerConfig.yPoint, PlayerConfig.move, PlayerConfig.life,
                KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S ,KeyEvent.VK_D, false, 0, 0, 2, false, 0));
    }

    /**
     * playerListの初期化
     */
    public static void resetList() {
        playerList.clear();
    }
}
