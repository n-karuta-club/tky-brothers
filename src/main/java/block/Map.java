package block;

import java.awt.Graphics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Map extends Block {
    private int xSize;
    private int ySize;

    @Override
    public void print(Graphics graphics) {
    }

    @Override
    public void status() {
    }
}
