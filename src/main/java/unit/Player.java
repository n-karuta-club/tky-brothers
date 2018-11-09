package unit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import config.PlayerConfig;
import config.WindowConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;
import service.FireService;

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
    // 攻撃用のボタン
    private int attackButton;
    // 最後に押されたボタン
    private int lastMove;
    // ジャンプしているかしていないかのフラグ
    private boolean jumpFlag;
    // 1つ前のフレームのy座標
    private int previewYPoint;
    // Y軸の移動距離
    private int moveYPoint;
    // プレイヤー番号
    private int playerNumber;
    // ダメージを受けているか判定するフラグ
    private boolean damageFlag;
    // ダメージを受けてから経過した時間を保持する変数
    private int damageTime;

    /**
     * printComponentメソッドで呼び出すことでオブジェクトを表示するためのメソッド
     * @param graphics
     */
    @Override
    public void print(Graphics graphics) {
        //  graphics.setColor(Color.GREEN);
        //  graphics.fillOval(xPoint, yPoint, PlayerConfig.xSize, PlayerConfig.ySize);
        graphics.drawString(String.valueOf(life), 100, 50);
        val bufferedImage = getImageGraphics();
        graphics.drawImage(bufferedImage, xPoint, yPoint, PlayerConfig.xSize, PlayerConfig.ySize, Color.WHITE, null);
    }

    /**
     * オブジェクトの状態を確認するメソッド。
     * 画面外にはみ出したら逆方向から出てくるようにしている。
     * runメソッドで呼び出す
     */
    @Override
    public void status() {
        // 左移動
        if (PlayerConfig.isPressing(moveLeftButton)) {
            xPoint -= move;
            lastMove = moveLeftButton;
        }
        // 右移動
        if (PlayerConfig.isPressing(moveRightButton)) {
            xPoint += move;
            lastMove = moveRightButton;
        }
        // ジャンプ
        if (PlayerConfig.isPressing(moveJumpButton)) {
            jumpFlagInit();
            jumpFlag = true;
        }
        // 火を吹く
        if (PlayerConfig.isPressing(attackButton)) {
            FireService.createFire(xPoint, yPoint, lastMove, playerNumber);
        }

        // == 画面外に移動した時の処理 ==
        if (xPoint > WindowConfig.xSize) {
            xPoint = 0;
        }
        if (xPoint < 0) {
            xPoint = WindowConfig.xSize;
        }
        if (yPoint > WindowConfig.ySize) {
            yPoint = WindowConfig.ySize;
        }
        // ==========================

        if (yPoint < 0) {
            addGravity();
            // setJumpFlag(false);
            // yPoint = WindowConfig.ySize;
            yPoint = 0;
        }

        // ジャンプ
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
     * ダメージ処理
     */
    public void damage() {
        if (!damageFlag) {
            life--;
        }
        damageFlag = true;
    }

    /**
     *  ダメージを受けている時間を測定
     */
    public void addDamageTime() {
        damageTime++;
        if (damageTime > PlayerConfig.damageTime) {
            damageTime = 0;
            damageFlag = false;
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

    /**
     * 表示する画像を決めるメソッド
     *
     * @return
     */
    private BufferedImage getImageGraphics() {
        BufferedImage bufferedImage = null;

        if (playerNumber == 1) {
            try {
                if (lastMove == moveLeftButton) {
                    bufferedImage = ImageIO
                            .read(new File(System.getProperty("user.dir") + "/src/main/images/QLeft.jpg"));
                } else {
                    bufferedImage = ImageIO
                            .read(new File(System.getProperty("user.dir") + "/src/main/images/QRight.jpg"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (lastMove == moveLeftButton) {
                    bufferedImage = ImageIO
                            .read(new File(System.getProperty("user.dir") + "/src/main/images/TLeft.jpg"));
                } else {
                    bufferedImage = ImageIO
                            .read(new File(System.getProperty("user.dir") + "/src/main/images/TRight.jpg"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bufferedImage;
    }
}