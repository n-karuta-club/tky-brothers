package block;

import java.awt.Graphics;

import config.PipeConfig;

public class Pipe extends Block{

	@Override
	public  void print(Graphics graphics) {
		graphics.drawOval(PipeConfig.XPoint1, 0, 80,80 );
		graphics.drawOval(PipeConfig.XPoint2, 0, 80,80 );
	}

	@Override
	public void status() {}

}
