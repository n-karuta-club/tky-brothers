package block;

import java.awt.Color;
import java.awt.Graphics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Floor extends Block {
    private int xPoint;
    private int yPoint;
    private int xSize;
    private int ySize;

    @Override
    public void print(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.drawRect(xPoint, yPoint, xSize, ySize);
    }

    @Override
    public void status() {
    }
}
