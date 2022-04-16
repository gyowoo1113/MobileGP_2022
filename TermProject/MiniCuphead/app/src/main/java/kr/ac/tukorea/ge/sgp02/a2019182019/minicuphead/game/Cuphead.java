package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.BoxCollidable;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.CollisionHelper;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.RangeBox;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Sprite;

public class Cuphead extends Sprite implements BoxCollidable {
    private static final String TAG = Cuphead.class.getSimpleName();

    private float elapsedTimeForFire;
    private float fireInterval;
    protected RectF boundingRect = new RectF();

    public Cuphead(float x, float y) {
        super(x, y, R.dimen.cuphead_radius, R.mipmap.player_normal);
        //targetBitmap = BitmapPool.get(R.mipmap.target);
        fireInterval = Metrics.floatValue(R.dimen.cuphead_fire_interval);

        float radius = dstRect.width() / 2;
        boundingRect.set(x - radius, y - radius, x + radius, y + radius);
    }

    public void update() {
        boundingRect.set(dstRect);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void fire() {
    }

    public void setPosition(float x, float y, RangeBox moveBoundingBox) {

        if (!CollisionHelper.isPointInBox(moveBoundingBox,x,y))
        {return;}

        float radius = dstRect.width() / 2;
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
    }

    @Override
    public RectF getBoundingRect() {
        return boundingRect;
    }
}

