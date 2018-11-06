package unit;

import java.awt.Color;
import java.awt.Graphics;

import config.PlayerConfig;
import config.WindowConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Player extends Unit {
    // x座標
    private int xPoint;
    // y座標
    private int yPoint;
    // 移動距離
    private int move;
    // ライフ
    private int life;
    // 左移動するためのボタン
    private int moveLeftButton;
    // 右移動するためのボタン
    private int moveRightButton;
    // ジャンプするためのボタン
    private int moveJumpButton;
    // 最後に押されたボタン
    private int lastMove;
    // ジャンプしているかしていないかのフラグ
    private boolean jumpFlag;
    // 1つ前のフレームのy座標
    private int previewYPoint;
    // Y軸の移動距離
    private int moveYPoint;

    /**
     * printComponentメソッドで呼び出すことでオブジェクトを表示するためのメソッド
     * @param graphics
     */
    @Override
    public void print(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillOval(xPoint, yPoint, PlayerConfig.xSize, PlayerConfig.ySize);
    }

    /**
     * オブジェクトの状態を確認するメソッド。
     * 画面外にはみ出したら逆方向から出てくるようにしている。
     * runメソッドで呼び出す
     */
    @Override
    public void status() {
        if (PlayerConfig.isPressing(moveLeftButton)) {
            xPoint -= move;
        }
        if (PlayerConfig.isPressing(moveRightButton)) {
            xPoint += move;
        }
        if (PlayerConfig.isPressing(moveJumpButton)) {
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
            yPoint = WindowConfig.ySize;
        }
        if (yPoint < 0) {
            addGravity();
            // setJumpFlag(false);
            // yPoint = WindowConfig.ySize;
            yPoint = 0;
        }
        jump();

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

    /**
     * jumpする時に呼び出されるメソッド
     */
    private void jumpFlagInit() {
        if (jumpFlag) {
            return;
        }
        previewYPoint = yPoint;
        yPoint -= PlayerConfig.jumpPoint;
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