package service;

import java.util.ArrayList;

import config.WindowConfig;
import lombok.val;
import unit.Enemy;
import unit.Fire;
import util.HitUtil;

public class HitService {

    /**
     * 画面外かどうかの当たり判定
     * @param fire
     * @return boolean 外にでていたらtrue
     */
    public static boolean isHitFireToWindow(Fire fire) {
        if (fire.getXPoint() < 0 || fire.getXPoint() > WindowConfig.xSize) {
            return true;
        }
        return false;
    }

    /**
     * enemyとの当たり判定
     * @param fire
     * @param enemyList
     * @return boolean ぶつかっていたらtrue
     */
    public static boolean isHitFireToEnemy(Fire fire, ArrayList<Enemy> enemyList) {
        for (int index = 0; index < enemyList.size(); index++) {
            val enemy = enemyList.get(index);
            if (HitUtil.isHitFireToEnemy(fire, enemy)) {
                EnemyService.removeEnemy(index);
                System.out.println("hoge =>>>>>>>>>>>>>>>");
                return true;
            }
        }
        return false;
    }
}
