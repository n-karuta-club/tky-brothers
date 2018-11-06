package game;

import java.awt.Dimension;

import javax.swing.JPanel;

import config.WindowConfig;

public class ResultGame extends JPanel{

	public ResultGame(GameWindow gamewindow) {
		setPreferredSize(new Dimension(WindowConfig.xSize, WindowConfig.ySize));

	}

}
