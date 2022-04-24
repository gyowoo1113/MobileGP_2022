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
    public static SwitchButton getInstance() {
        if (singleton == null) {
            float x = Metrics.width/2;
            float y = Metrics.height/2;
            singleton = new SwitchButton(x,y);
        }
        return singleton;
    }
    private static SwitchButton singleton;
    private boolean isBomb = false;
    protected RectF boundingRect = new RectF();

    private SwitchButton(float x, float y)
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
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (CollisionHelper.isPointInBox(this, x, y))
                    return true;

                break;

            case MotionEvent.ACTION_UP:
                isBomb = !isBomb;
                return true;
        }
        return false;
    }

    public boolean isBomb() {
        return isBomb;
    }

    @Override
    public RectF getBoundingRect() {
        return null;
    }
}
