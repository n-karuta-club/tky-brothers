package block;

import java.awt.Graphics;

import config.ScoreConfig;
import config.WindowConfig;
import lombok.Getter;

@Getter
public class Score extends Block {
    private int point = 0;

    /**
     * printComponentメソッドで呼び出すことでオブジェクトを表示するためのメソッド
     * @param graphics
     */
    @Override
    public void print(Graphics graphics) {
        graphics.drawString(String.valueOf(point), WindowConfig.xSize / 2, 50);
    }

    /**
     * オブジェクトの状態を確認するメソッド。
     * runメソッドで呼び出す
     */
    @Override
    public void status() {}

    /**
     * 敵を倒した時のスコアを追加するメソッド
     */
    public void breakEnemy() {
        point += ScoreConfig.enemyBreakPoint;
    }


}
