package block;

import java.awt.Graphics;

import config.ScoreConfig;
import config.WindowConfig;
import lombok.Getter;

@Getter
public class Score extends Block {
    private int point = 0;

    @Override
    public void print(Graphics graphics) {
        graphics.drawString(String.valueOf(point), WindowConfig.xSize / 2, 50);
    }

    @Override
    public void status() {
        // TODO 自動生成されたメソッド・スタブ
    }

    public void breakEnemy() {
        point += ScoreConfig.enemyBreakPoint;
    }
}
