import game.GameWindow;
import game.StartGame;
import lombok.val;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        val gameWindow = new GameWindow();
        gameWindow.setVisible(true);
        gameWindow.change(new StartGame(gameWindow));
    }
}