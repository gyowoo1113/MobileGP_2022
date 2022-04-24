package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.BoxCollidable;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.CollisionHelper;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.GameObject;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.RangeBox;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Sprite;

public class SwitchButton extends Sprite implements GameObject, BoxCollidable {
    protected RectF boundingRect = new RectF();

    public SwitchButton(float x, float y)
    {
        super(x, y, R.dimen.cuphead_radius, R.mipmap.player_normal);
        float radius = dstRect.width() / 2;
        boundingRect.set(x - radius, y - radius, x + radius, y + radius);
    }

    @Override
    public void update() {
        //boundingRect.set(dstRect);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    @Override
    public RectF getBoundingRect() {
        return null;
    }
}
