package block;

import java.awt.Graphics;
import java.util.Random;

import config.EnemyConfig;
import config.PipeConfig;
import config.WindowConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;
import service.EnemyService;
import service.PlayerService;

@AllArgsConstructor
@Getter
public class Timer extends Block {
    // 残り時間
    private int nowTime;
    // 時間カウント用
    private int count;
    // X軸座標
    private int xPoint;
    // Y軸座標
    private int yPoint;

    /**
     * ゲーム時間を表示する
     */
    public void print(Graphics graphics) {
        graphics.drawString(String.valueOf(nowTime), xPoint, yPoint);
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
            Math.random();
            val random = new Random();

            if (random.nextInt(2) == 0) {
            	 EnemyService.addEnemy(PipeConfig.XPoint1, EnemyConfig.yPoint);
            } else {
            	 EnemyService.addEnemy(PipeConfig.XPoint2, EnemyConfig.yPoint);
            }
        }
    }

    /**
     * ゲーム時間が終わっているかどうかを判定する
     *
     * @return
     */
    public boolean stateNowTime() {
        if (nowTime < 0) {
        	// Score.remainLives();
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
