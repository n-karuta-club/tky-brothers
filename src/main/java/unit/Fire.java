package unit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import config.FireConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

@Getter
@AllArgsConstructor
public class Fire extends Unit {
    // x座標
    private int xPoint;
    // y座標
    private int yPoint;
    // 向き
    private String moveDirection;
    // player番号
    private int playerNumber;

    /**
     * printComponentメソッドで呼び出すことでオブジェクトを表示するためのメソッド
     * @param graphics
     */
    @Override
    public void print(Graphics graphics) {
        // TODO 自動生成されたメソッド・スタブ
        val bufferedImage = getImageGraphics();
        graphics.drawImage(bufferedImage, xPoint, yPoint, FireConfig.xSize, FireConfig.ySize, Color.WHITE, null);
    }

    /**
     * オブジェクトの状態を確認するメソッド。
     */
    @Override
    public void status() {
        if (moveDirection == "left") {
            xPoint -= FireConfig.move;
        } else {
            xPoint += FireConfig.move;
        }
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
                if (moveDirection == "left") {
                    bufferedImage = ImageIO
                            .read(new File(System.getProperty("user.dir") + "/src/main/images/QFireLeft.jpg"));
                } else {
                    bufferedImage = ImageIO
                            .read(new File(System.getProperty("user.dir") + "/src/main/images/QFireRight.jpg"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (moveDirection == "left") {
                    bufferedImage = ImageIO
                            .read(new File(System.getProperty("user.dir") + "/src/main/images/TFireLeft.jpg"));
                } else {
                    bufferedImage = ImageIO
                            .read(new File(System.getProperty("user.dir") + "/src/main/images/TFireRight.jpg"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bufferedImage;
    }
}
