package unit;

import java.awt.Color;
import java.awt.Graphics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Floor {
    private int xSize;
    private int xPoint;
    private int yPoint;

    public void print(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.drawLine(xPoint, yPoint, xPoint + xSize, yPoint);
    }

    public void onPlayer(Player player) {
    }

    public void isOnPlayer(Player player) {
    }
}
