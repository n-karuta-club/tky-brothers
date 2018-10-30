package block;

import java.awt.Graphics;

import config.WindowConfig;

public class Timer extends Block {
    private int nowTime = WindowConfig.gameTime;
    private int count = 0;

    @Override
    public void print(Graphics graphics) {
        graphics.drawString(String.valueOf(nowTime), 50, 50);
    }

    @Override
    public void status() {
        count++;

        if (count >= 20) {
            count = 0;
            nowTime -= 1;
        }
    }

    public boolean stateNowTime() {
        if (nowTime <= 0) {
            return true;
        }
        return false;
    }

}
