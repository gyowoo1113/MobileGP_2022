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
    public static final int TOUCH_MONSTER = 5;
    public static final int NORMAL_MONSTER = 100;
    private static final String TAG = Enemy.class.getSimpleName();
    public static float size;
    protected int level;
    protected float dx;
    protected int life, maxlife;
    protected int maxAlpha = 255;
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
        this.life = (level == 1) ? NORMAL_MONSTER : TOUCH_MONSTER;
        this.maxlife = (level == 1) ? NORMAL_MONSTER : TOUCH_MONSTER;
        setAlpha(this.maxAlpha);
    }

    private Enemy(int level, float y, float speed) {
        super(Metrics.width+size,y, size, size, bitmapIds[level - 1], FRAMES_PER_SECOND, 16);
        this.level = level;
        dx = speed;
        this.life = (level == 1) ? NORMAL_MONSTER : TOUCH_MONSTER;
        this.maxlife = (level == 1) ? NORMAL_MONSTER : TOUCH_MONSTER;
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

    public boolean decreaseLife(int power) {
        life -= power;
        setAlphaPercent();
        if (life <= 0) return true;
        return false;
    }

    public void setAlphaPercent(){
        int per = maxlife/5;

        if (life > per*4) setAlpha(255 - 51);
        else if (life > per*3) setAlpha(255 - 102);
        else if (life > per*2) setAlpha(255 - 153);
        else setAlpha(255 - 204);
    }
}
