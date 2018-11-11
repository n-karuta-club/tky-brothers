package block;

import java.awt.Graphics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Map extends Block {
    private int xSize;
    private int ySize;

    /**
     * printComponentメソッドで呼び出すことでオブジェクトを表示するためのメソッド
     * @param graphics
     */
    @Override
    public void print(Graphics graphics) {
    }

    /**
     * オブジェクトの状態を確認するメソッド。
     * runメソッドで呼び出す
     */
    @Override
    public void status() {
    }
}
