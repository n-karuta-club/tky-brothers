import javax.swing.JFrame;

import game.GameWindow;
import game.StartGame;
import lombok.val;

public class Main extends JFrame {
    public static void main(String[] args) throws InterruptedException {
        val gameWindow = new GameWindow();
        gameWindow.setVisible(true);
        gameWindow.change(new StartGame(gameWindow));
    }
}