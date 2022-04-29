package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.CollisionHelper;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.GameObject;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet.Bullet;

public class CollisionChecker implements GameObject {
    private static final String TAG = CollisionChecker.class.getSimpleName();

    @Override
    public void update() {
        MainGame game = MainGame.getInstance();
        ArrayList<GameObject> bullets = game.objectsAt(MainGame.Layer.bullet);
        ArrayList<GameObject> enemies = game.objectsAt(MainGame.Layer.enemy);
        for (GameObject o1 : enemies) {
            if (!(o1 instanceof Enemy)) {
                continue;
            }
            Enemy enemy = (Enemy) o1;
            boolean collided = false;
            for (GameObject o2 : bullets) {
                if (!(o2 instanceof Bullet)) {
                    continue;
                }
                Bullet bullet = (Bullet) o2;
                if (CollisionHelper.collides(enemy, bullet)) {
                    //Log.d(TAG, "*Collision*");
                    game.remove(bullet);
                    game.remove(enemy);
                    collided = true;
                    break;
                }
            }
            if (collided) {
                continue;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
