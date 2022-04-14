package kr.ac.tukorea.ge.sgp02.a2019182019.dragonflight.game;

import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp02.a2019182019.dragonflight.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.dragonflight.framework.BitmapPool;
import kr.ac.tukorea.ge.sgp02.a2019182019.dragonflight.framework.BoxCollidable;
import kr.ac.tukorea.ge.sgp02.a2019182019.dragonflight.framework.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.dragonflight.framework.Sprite;

public class Enemy extends Sprite implements BoxCollidable {
    private static float size;
    private static float inset;
    protected float dy;
    protected RectF boundingRect = new RectF();
    public Enemy(float x,float speed){
        //super(x,0,R.dimen.enemy_radius,R.mipmap.f_01_01);
        super(x,-size/2,size,size,R.mipmap.f_01_01);
        dy = speed;
    }

    public static void setSize(float size){
        Enemy.size = size;
        Enemy.inset = size / 16;
    }

    @Override
    public void update() {
        float frameTime = MainGame.getInstance().frameTime;
        y += dy * frameTime;
        boundingRect.set(dstRect);
        setDstRectWithRadius();
        if (dstRect.top > Metrics.height) {
            MainGame.getInstance().remove(this);
        }
    }

    @Override
    public RectF getBoundingRect() {
        RectF boundingRect = new RectF(dstRect);
        boundingRect.inset(inset,inset);
        return dstRect;
    }
}
