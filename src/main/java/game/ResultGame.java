package game;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import block.Score;
import config.WindowConfig;
import lombok.val;
import service.EnemyService;
import service.FireService;
import service.FloorService;
import service.PlayerService;

public class ResultGame extends JPanel{

    public ResultGame(GameWindow gameWindow, Score score) {
         setPreferredSize(new Dimension(WindowConfig.xSize, WindowConfig.ySize));
         val startButton = new JButton("タイトルへ戻る");
         val scoreLabel = new JLabel(String.valueOf(score.getPoint()));

         startButton.addActionListener(event -> {
             PlayerService.resetList();
             EnemyService.resetList();
             FireService.resetList();
             FloorService.resetList();
             gameWindow.change(new StartGame(gameWindow));
         });

         add(startButton);
         add(scoreLabel);
    }

}
