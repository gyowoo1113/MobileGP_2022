package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet;

import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.sprites.AnimSprite;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.interfaces.BoxCollidable;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.interfaces.Recyclable;

public class Bullet extends AnimSprite implements BoxCollidable , Recyclable {
    protected float x, y;
    protected float dx, dy;
    protected RectF boundingRect = new RectF();

    protected Bullet(float x, float y ,int w, int h, int ResID) {
        super(x, y, w,h, ResID, 10.0f,1);
        this.x = x;
        this.y = y;
        angle = (float) (Math.PI * 2 - (Math.PI/2));
    }

    protected Bullet(float x, float y ,int w, int h, int ResID, float fps, int fc) {
        super(x, y, w,h, ResID, fps,fc);
        this.x = x;
        this.y = y;
        angle = (float) (Math.PI * 2 - (Math.PI/2));
    }

    @Override
    public void update(float frameTime) {

    }

    @Override
    public RectF getBoundingRect() {
        return boundingRect;
    }

    @Override
    public void finish() {

    }

    protected void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public int getPower(){
        return 0;
    }
}
