package unit;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import config.EnemyConfig;
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
    // ジャンプしているかしていないかのフラグ
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
        val isJump = random.nextInt(EnemyConfig.sponeRate);

        switch (moveDirection) {
        case "left":
            xPoint += EnemyConfig.move;
            break;
        case "right":
            xPoint -= EnemyConfig.move;
            break;
        }

        if (isJump == 0 && jumpFlag == false) {
            jumpFlagInit();
            jumpFlag = true;
        }

        if (xPoint > WindowConfig.xSize) {
            xPoint = 0;
        }
        if (xPoint < 0) {
            xPoint = WindowConfig.xSize;
        }

        if (yPoint < 0) {
             addGravity();
             // setJumpFlag(false);
             // yPoint = WindowConfig.ySize;
             yPoint = 0;
         }

        if (yPoint > WindowConfig.ySize) {
            yPoint = WindowConfig.ySize;
        }
        jump();
    }

    /**
     * jumpする時に呼び出されるメソッド
     */
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

    /**
     * オブジェクトに重力を追加するメソッド
     */
    public void addGravity() {
        this.yPoint += WindowConfig.gravity;
    }

    /**
     * jumpFlagを更新するメソッド
     * @param flag
     */
    public void setJumpFlag(boolean flag) {
        jumpFlag = flag;
    }

    /**
     * Y軸の座標を更新するメソッド
     * @param yPoint
     */
    public void setYPoint(int yPoint) {
        this.yPoint = yPoint;
    }

}
