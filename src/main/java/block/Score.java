package block;

import java.awt.Font;
import java.awt.Graphics;

import config.ScoreConfig;
import config.WindowConfig;
import lombok.Getter;
import lombok.val;
import service.PlayerService;

@Getter
public class Score extends Block {
    private int point = 0;

    /**
     * printComponentメソッドで呼び出すことでオブジェクトを表示するためのメソッド
     */
    @Override
    public void print(Graphics graphics) {
    	Font score = new Font("DIALOG",Font.PLAIN,20);
    	graphics.setFont(score);
        graphics.drawString(String.valueOf(point), WindowConfig.xSize / 2, 50);
    }

    /**
     * オブジェクトの状態を確認するメソッド。
     * runメソッドで呼び出す
     */
    @Override
    public void status() {
    }

    /**
     * 敵を倒した時のスコアを追加するメソッド
     */
    public void breakEnemy() {
        point += ScoreConfig.enemyBreakPoint;
    }

    public int remainLives() {
        int lifePoint = 0;
        // playerlist.forEach(player -> {
        for (val player : PlayerService.playerList) {
            lifePoint += (player.getLife() * ScoreConfig.livesPoint);
        }
        ;
        return lifePoint;
    }
}
