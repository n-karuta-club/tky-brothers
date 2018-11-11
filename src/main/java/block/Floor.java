package block;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

@Getter
@AllArgsConstructor
public class Floor extends Block {
    private int xPoint;
    private int yPoint;
    private int xSize;
    private int ySize;

    /**
     * printComponentメソッドで呼び出すことでオブジェクトを表示するためのメソッド
     * @param graphics
     */
    @Override
    public void print(Graphics graphics) {
       //  graphics.setColor(Color.ORANGE);
       //  graphics.drawRect(xPoint, yPoint, xSize, ySize);
       val bufferedImage = getImageGraphics();
       graphics.drawImage(bufferedImage, xPoint, yPoint, xSize, ySize, Color.WHITE, null);
    }

    /**
     * オブジェクトの状態を確認するメソッド。
     * runメソッドで呼び出す
     */
    @Override
    public void status() {}

    /**
     * 画像の読み込み
     *
     * @return
     */
    private BufferedImage getImageGraphics() {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(System.getProperty("user.dir") + "/src/main/images/Block.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }
}
