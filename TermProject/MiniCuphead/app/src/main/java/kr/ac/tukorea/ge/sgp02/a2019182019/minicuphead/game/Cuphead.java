package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.BoxCollidable;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.CollisionHelper;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.RangeBox;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Sprite;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet.BombBullet;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet.Bullet;

public class Cuphead extends Sprite implements BoxCollidable {
    private static final String TAG = Cuphead.class.getSimpleName();

    private float elapsedTimeForFire;
    private float fireInterval;
    protected RectF boundingRect = new RectF();
    private boolean isFire;
    private boolean bulletToggle = false;
    float heightVal;

    public Cuphead(float x, float y) {
        super(x, y, R.dimen.cuphead_radius, R.mipmap.player_normal);
        //targetBitmap = BitmapPool.get(R.mipmap.target);
        fireInterval = Metrics.floatValue(R.dimen.cuphead_fire_interval);
        heightVal = Metrics.size(R.dimen.bulletHeight);

        float radius = dstRect.width() / 2;
        boundingRect.set(x - radius, y - radius, x + radius, y + radius);
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;
        elapsedTimeForFire += frameTime;
        if (elapsedTimeForFire > fireInterval && isFire) {
            fire();
            elapsedTimeForFire -= fireInterval;
        }
        boundingRect.set(dstRect);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void fire() {
        bulletToggle = !bulletToggle;
        float val = (bulletToggle) ? +heightVal : -heightVal;
        //Bullet bullet = new Bullet(x, y + val);
        BombBullet bullet = new BombBullet(x, y);
        MainGame.getInstance().add(bullet);
    }

    public void setPosition(float x, float y, RangeBox moveBoundingBox) {
        if (!CollisionHelper.isPointInBox(moveBoundingBox,x,y))
        {return;}

        float radius = dstRect.width() / 2;
        this.x = x;
        this.y = y;
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
    }

    @Override
    public RectF getBoundingRect() {
        return boundingRect;
    }

    public void setFire(boolean fire) {
        isFire = fire;
    }
}

