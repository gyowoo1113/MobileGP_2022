package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Sprite;

public class Cuphead extends Sprite {
    private static final String TAG = Cuphead.class.getSimpleName();

    private float elapsedTimeForFire;
    private float fireInterval;

    public Cuphead(float x, float y) {
        super(x, y, R.dimen.cuphead_radius, R.mipmap.player_normal);
        //targetBitmap = BitmapPool.get(R.mipmap.target);
        fireInterval = Metrics.floatValue(R.dimen.cuphead_fire_interval);
    }

    public void update() {
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void fire() {
    }

    public void setPosition(float x, float y) {
        float radius = dstRect.width() / 2;
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
    }
}
