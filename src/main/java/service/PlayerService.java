package service;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import config.PlayerConfig;
import unit.Player;

public class PlayerService {
    public static final ArrayList<Player> playerList = new ArrayList<>();

    public static void addFirstPlayer() {
        playerList.add(new Player(PlayerConfig.xPoint - 100, PlayerConfig.yPoint, PlayerConfig.move, PlayerConfig.life,
                KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE, KeyEvent.VK_RIGHT, false, 0, 0));
    }

    public static void addSecondPlayer() {
        playerList.add(new Player(PlayerConfig.xPoint + 100, PlayerConfig.yPoint, PlayerConfig.move, PlayerConfig.life,
                KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_D, false, 0, 0));
    }
}