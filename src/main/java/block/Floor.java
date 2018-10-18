package block;

import java.awt.Color;
import java.awt.Graphics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Floor extends Block {
    private int xSize;
    private int xPoint;
    private int yPoint;

    @Override
    public void print(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.drawLine(xPoint, yPoint, xPoint + xSize, yPoint);
    }

    @Override
    public void status() {
    }
}
