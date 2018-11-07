package block;

import java.awt.Graphics;

import config.WindowConfig;
import service.EnemyService;

public class Timer extends Block {
    private int nowTime = WindowConfig.gameTime;
    private int count = 0;
    private int damageTime = 0;
    private int damageCount = 0;
    private boolean damageFlag = false;

    /**
     * Timerを表示する
     */
    @Override
    public void print(Graphics graphics) {
        graphics.drawString(String.valueOf(nowTime), 50, 50);
    }

    /**
     * Timerの状態を返却する
     */
    @Override
    public void status() {
        count++;

        if (count >= 1000 / WindowConfig.gameTime) {
            count = 0;
            nowTime -= 1;
            // 敵を１秒に1体増やす処理
            EnemyService.addEnemy(300, 100);
        }
    }

    /**
     * 無敵時間を作る
     * @return
     */
    public boolean damageTime() {
        if (damageFlag) {
            damageCount++;
            if (count >= WindowConfig.gameTime) {
                damageTime++;
            }
            if (damageTime >= 3) {
                damageCount = 0;
                damageTime = 0;
                damageFlag = false;
                return false;
            }
            return true;
        }
        damageCount = 0;
        damageTime = 0;
        damageFlag = false;
        return false;
    }

    public void setDamageFlag(boolean damageFlag) {
        this.damageFlag = damageFlag;
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

}
