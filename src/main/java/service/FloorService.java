package service;

import java.util.ArrayList;

import block.Floor;
import config.FloorConfig;
import config.WindowConfig;

public class FloorService {
    public static final ArrayList<Floor> floorList = new ArrayList<>();

    /**
     * Floorをリストに追加するメソッド
     */
    public static void addFloor(int xPoint, int yPoint, int xSize, int ySize) {
        floorList.add(new Floor(xPoint, yPoint, xSize, ySize));
    }

    /**
     * floorの初期配置を行うメソッド
     */
    public static void initialize() {
        addFloor(-10, WindowConfig.ySize - 32, WindowConfig.xSize + 100, FloorConfig.ySize);
        addFloor(-10, WindowConfig.ySize - 170, WindowConfig.xSize / 2, FloorConfig.ySize);
        addFloor(100, WindowConfig.ySize - 300, WindowConfig.xSize / 2, FloorConfig.ySize);
        addFloor(700, WindowConfig.ySize - 300, WindowConfig.xSize / 2, FloorConfig.ySize);
        addFloor(100, WindowConfig.ySize - 400, WindowConfig.xSize / 2, FloorConfig.ySize);
        addFloor(200, WindowConfig.ySize - 500, WindowConfig.xSize / 2, FloorConfig.ySize);
    }
}
