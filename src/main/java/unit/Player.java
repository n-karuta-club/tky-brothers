package unit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import config.CharacterConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Player {
    private int xPoint;
    private int yPoint;
    private int move;
    private int life;
    private int moveLeftButton;
    private int moveRightButton;
    private int moveJumpButton;
    private int lastMove;
    private boolean jumpFlag;
    private int previewYPoint;

    /**
     * printComponentメソッドで呼び出すことでオブジェクトを表示するためのメソッド
     * @param graphics
     */
    public void print(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillOval(xPoint, yPoint, CharacterConfig.xSize, CharacterConfig.ySize);
    }

    /**
     * KeyPressedメソッドで呼び出すことでオブジェクトを移動できるようにするメソッド
     * @param event
     */
    public void move(KeyEvent event) {
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

    /**
     * runメソッドで呼び出すことでオブジェクトをジャンプできるようにするメソッド
     */
    public void jump() {
        if (!jumpFlag) {
            return;
        }

        int yTmp = yPoint;
        yPoint += (yPoint - previewYPoint) + 1;
        previewYPoint = yTmp;
        if (yPoint == CharacterConfig.yPoint) {
            jumpFlag = false;
        }
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
}
