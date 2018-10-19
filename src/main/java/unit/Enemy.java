package unit;

import java.awt.Color;
import java.awt.Graphics;

import config.CharacterConfig;
import config.WindowConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Enemy extends Unit {
    private int xPoint;
    private int yPoint;
    private String moveDirection;

    @Override
    public void print(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillOval(xPoint, yPoint, CharacterConfig.xSize, CharacterConfig.ySize);
    }

    @Override
    public void status() {
        switch (moveDirection) {
        case "left":
            xPoint += CharacterConfig.move;
            break;
        case "right":
            xPoint -= CharacterConfig.move;
            break;
        }

        if (xPoint > WindowConfig.xSize) {
            xPoint = 0;
        }
        if (xPoint < 0) {
            xPoint = WindowConfig.xSize;
        }
    }
}
