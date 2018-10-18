package unit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import config.CharacterConfig;
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

    /**
     * printComponentメソッドで呼び出すことでオブジェクトを表示するためのメソッド
     * @param graphics
     */
    @Override
    public void print(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillOval(xPoint, yPoint, CharacterConfig.xSize, CharacterConfig.ySize);
    }

    /**
     * オブジェクトの状態を確認するメソッド。
     * 画面外にはみ出したら逆方向から出てくるようにしている。
     * runメソッドで呼び出す
     */
    @Override
    public void status() {
        if (xPoint > WindowConfig.xSize) {
            xPoint = 0;
        }
        if (xPoint < 0) {
            xPoint = WindowConfig.xSize;
        }
        if (yPoint > WindowConfig.ySize) {
            yPoint = 0;
        }
        if (yPoint < 0) {
            yPoint = WindowConfig.ySize;
        }

        jump();

        // System.out.println(jumpFlag);
    }

    /**
     * KeyPressedメソッドで呼び出すことでオブジェクトを移動できるようにするメソッド
     * @param event
     */
    public void action(KeyEvent event) {
        if (event.getKeyCode() == moveLeftButton) {
            xPoint -= move;
        }
        if (event.getKeyCode() == moveRightButton) {
            xPoint += move;
        }
        if (event.getKeyCode() == moveJumpButton) {
            jumpFlagInit();
            jumpFlag = true;
        }
    }

    public void addGravity() {
        this.yPoint += WindowConfig.gravity;
    }

    public void setJumpFlag(boolean flag) {
        jumpFlag = flag;
    }

    /**
     * runメソッドで呼び出すことでオブジェクトをジャンプできるようにするメソッド
     */
    private void jump() {
        if (!jumpFlag) {
            return;
        }

        int yTmp = yPoint;
        yPoint += (yPoint - previewYPoint);
        previewYPoint = yTmp;
    }

    /**
     * jumpする時に呼び出されるメソッド
     */
    private void jumpFlagInit() {
        if (jumpFlag) {
            return;
        }
        previewYPoint = yPoint;
        yPoint -= CharacterConfig.jumpPoint;
    }

    public void setYPoint(int yPoint) {
        this.yPoint = yPoint;
    }

}