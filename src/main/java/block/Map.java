package block;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import config.WindowConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

@Getter
@AllArgsConstructor
public class Map extends Block {

    /**
     * printComponentメソッドで呼び出すことでオブジェクトを表示するためのメソッド
     * @param graphics
     */
    @Override
    public void print(Graphics graphics) {
        val bufferedImage = getImageGraphics();
        graphics.drawImage(bufferedImage, 0, 0, WindowConfig.xSize, WindowConfig.ySize, null);
    }

    /**
     * オブジェクトの状態を確認するメソッド。
     * runメソッドで呼び出す
     */
    @Override
    public void status() {
    }

    private BufferedImage getImageGraphics() {
        BufferedImage bufferedImage = null;
        try {
            // bufferedImage = ImageIO.read(new File(System.getProperty("user.dir") + "/src/main/images/Block.jpg"));
            // bufferedImage = ImageIO.read(getClass().getResourceAsStream("../resources/img/Block.jpg"));
            bufferedImage = ImageIO.read(getClass().getResourceAsStream("images/Map.jpg"));
            // .read(getClass().getResourceAsStream("images/YakaraRight.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }
}
