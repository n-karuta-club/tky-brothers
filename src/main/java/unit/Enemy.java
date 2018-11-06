package unit;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import config.EnemyConfig;
import config.FloorConfig;
import config.WindowConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

@Getter
@AllArgsConstructor
public class Enemy extends Unit {
    private int xPoint;
    private int yPoint;
    private String moveDirection;
    private boolean jumpFlag;
    // 1つ前のフレームのy座標
    private int previewYPoint;
    // Y軸の移動距離
    private int moveYPoint;

    @Override
    public void print(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillOval(xPoint, yPoint, EnemyConfig.xSize, EnemyConfig.ySize);
    }

    @Override
    public void status() {
        val random = new Random();
        val rand = random.nextInt(10);
        switch (moveDirection) {
        case "left":
            xPoint += EnemyConfig.move;
            break;
        case "right":
            xPoint -= EnemyConfig.move;
            break;
        }

        if (rand == 0) {
            jumpFlagInit();
            jumpFlag = true;
        }

        if (xPoint > WindowConfig.xSize) {
            xPoint = 0;
        }
        if (xPoint < 0) {
            xPoint = WindowConfig.xSize;
        }

        if (yPoint > WindowConfig.ySize) {
            yPoint = WindowConfig.ySize - (FloorConfig.ySize + EnemyConfig.ySize);
        }

        //  if (yPoint < 0) {
        //      addGravity();
        //      //setJumpFlag(false);
        //      // yPoint = WindowConfig.ySize;
        //      yPoint = 0;
        //  }
        jump();
    }

    public void addGravity() {
        System.out.println("===================");
        this.yPoint += WindowConfig.gravity;
    }

    public void setJumpFlag(boolean flag) {
        jumpFlag = flag;
        moveYPoint = 0;
        previewYPoint = 0;
    }

    /**
     * Y軸の座標を更新するメソッド
     * @param yPoint
     */
    public void setYPoint(int yPoint) {
        this.yPoint = yPoint;
    }

    private void jumpFlagInit() {
        if (jumpFlag) {
            return;
        }
        previewYPoint = yPoint;
        yPoint -= EnemyConfig.jumpPoint;
    }

    /**
     * statusメソッドで呼び出すことでオブジェクトをジャンプできるようにするメソッド
     */
    private void jump() {
        if (!jumpFlag) {
            return;
        }

        int yTmp = yPoint;
        moveYPoint = (yPoint - previewYPoint);
        yPoint += moveYPoint;
        previewYPoint = yTmp;
    }

}
