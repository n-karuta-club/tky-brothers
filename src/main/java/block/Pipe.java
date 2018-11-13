package block;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import config.PipeConfig;
import lombok.val;

public class Pipe extends Block{

	@Override
	public  void print(Graphics graphics) {
		val bufferedImage = getImageGraphics();
		graphics.drawImage(bufferedImage,PipeConfig.XPoint1, PipeConfig.YPoint, PipeConfig.XSize, PipeConfig.YSize,null);
		graphics.drawImage(bufferedImage,PipeConfig.XPoint2, PipeConfig.YPoint, PipeConfig.XSize, PipeConfig.YSize,null);
	}

	@Override
	public void status() {}
	 private BufferedImage getImageGraphics() {
	        BufferedImage bufferedImage = null;
	        try {
	            // bufferedImage = ImageIO.read(new File(System.getProperty("user.dir") + "/src/main/images/Block.jpg"));
	            // bufferedImage = ImageIO.read(getClass().getResourceAsStream("../resources/img/Block.jpg"));
	            bufferedImage = ImageIO.read(getClass().getResourceAsStream("images/Dokan.jpg"));
	                                   // .read(getClass().getResourceAsStream("images/YakaraRight.jpg"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return bufferedImage;
	    }
}
