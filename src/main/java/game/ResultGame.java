package game;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import config.WindowConfig;
import lombok.val;
import service.EnemyService;
import service.FireService;
import service.FloorService;
import service.PlayerService;

public class ResultGame extends JPanel{

    public ResultGame(GameWindow gameWindow) {
         setPreferredSize(new Dimension(WindowConfig.xSize, WindowConfig.ySize));
         val button1 = new JButton("タイトルへ戻る");

         button1.addActionListener(event -> {
             PlayerService.resetList();
             EnemyService.resetList();
             FireService.resetList();
             FloorService.resetList();
             gameWindow.change(new StartGame(gameWindow));
         });

         add(button1);
    }

}
