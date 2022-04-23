package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.BoxCollidable;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.GameObject;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.MainGame;

public class Bullet implements GameObject, BoxCollidable {
    protected float x, y;
    protected float length;
    protected float dx, dy;
    protected RectF boundingRect = new RectF();

    protected static Paint paint;
    protected static float laserWidth;

    public Bullet(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawLine(x, y, x-length, y, paint);
    }

    @Override
    public RectF getBoundingRect() {
        return boundingRect;
    }
}
