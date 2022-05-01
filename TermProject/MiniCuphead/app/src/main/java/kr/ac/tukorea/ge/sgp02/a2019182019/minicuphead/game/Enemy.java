package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game;

import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.AnimSprite;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.BitmapPool;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.BoxCollidable;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Recyclable;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.RecycleBin;


public class Enemy extends AnimSprite implements BoxCollidable, Recyclable {
    public static final float FRAMES_PER_SECOND = 10.0f;
    private static final String TAG = Enemy.class.getSimpleName();
    public static float size;
    protected int level;
    protected float dx;
    protected RectF boundingBox = new RectF();
    protected static int[] bitmapIds = {
            R.mipmap.monster_normal, R.mipmap.monster_touch
    };
    public static final int MIN_LEVEL = 1;
    public static final int MAX_LEVEL = bitmapIds.length;

    public static Enemy get(int level, float y, float speed) {
        Enemy enemy = (Enemy) RecycleBin.get(Enemy.class);
        if (enemy != null) {
            enemy.set(level, y, speed);
            return enemy;
        }
        return new Enemy(level, y, speed);
    }

    private void set(int level, float y, float speed) {
        bitmap = BitmapPool.get(bitmapIds[level - 1]);
        this.y = y;
        this.x = -size;
        this.dx = speed;
        this.level = level;
    }

    private Enemy(int level, float y, float speed) {
        super(Metrics.width+size,y, size, size, bitmapIds[level - 1], FRAMES_PER_SECOND, 16);
        this.level = level;
        dx = speed;
    }

    @Override
    public void update() {
        float frameTime = MainGame.getInstance().frameTime;
        x -= dx * frameTime;
        setDstRectWithRadius();
        boundingBox.set(dstRect);
        boundingBox.inset(size/16, size/16);
        if (dstRect.top > Metrics.height) {
            MainGame.getInstance().remove(this);
        }
    }

    @Override
    public RectF getBoundingRect() {
        return boundingBox;
    }

    @Override
    public void finish() {

    }
}
