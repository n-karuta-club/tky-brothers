package block;

import java.awt.Graphics;

import config.WindowConfig;
import service.EnemyService;
import service.PlayerService;

public class Timer extends Block {
    private int nowTime = WindowConfig.gameTime;
    private int count = 0;

    /**
     * ゲーム時間を表示する
     */
    public void print(Graphics graphics) {
        graphics.drawString(String.valueOf(nowTime), 50, 50);
    }

    /**
     * ゲーム時間の状態を返却する
     */
    public void status() {
        count++;

        if (count >= 1000 / WindowConfig.gameTime) {
            count = 0;
            nowTime -= 1;
            // 敵を１秒に1体増やす処理
            damageToPlayer();
            EnemyService.addEnemy(300, 100);
        }
    }

    /**
     * ゲーム時間が終わっているかどうかを判定する
     *
     * @return
     */
    public boolean stateNowTime() {
        if (nowTime < 0) {
            return true;
        }
        return false;
    }

    /**
     * playerの無敵時間を作る
     * @return
     */
    private void damageToPlayer() {
        PlayerService.playerList.forEach(player -> {
            if (player.isDamageFlag()) {
                player.addDamageTime();
            }
        });
    }
}
