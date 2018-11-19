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
        // 一番下の床
        addFloor(-10, WindowConfig.ySize - 64, WindowConfig.xSize + 100, FloorConfig.ySize);

        // その他の床
        addFloor(-150, WindowConfig.ySize - 460, WindowConfig.xSize / 2, FloorConfig.ySize);
        addFloor(600, WindowConfig.ySize - 460, WindowConfig.xSize / 2, FloorConfig.ySize);
        addFloor(200, WindowConfig.ySize - 330, WindowConfig.xSize / 2, FloorConfig.ySize);
        addFloor(-150, WindowConfig.ySize - 200, WindowConfig.xSize / 2, FloorConfig.ySize);
        addFloor(600, WindowConfig.ySize - 200, WindowConfig.xSize / 2, FloorConfig.ySize);
        // addFloor(100, WindowConfig.ySize - 430, WindowConfig.xSize / 3, FloorConfig.ySize);
        // addFloor(200, WindowConfig.ySize - 500, WindowConfig.xSize / 2, FloorConfig.ySize);
    }

    /**
     * リストをリセットするメソッド
     */
    public static void resetList() {
        floorList.clear();
    }
}
