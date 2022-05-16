package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.interfaces.BoxCollidable;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.interfaces.GameObject;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.resource.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.MainGame;

public class RangeBox implements BoxCollidable, GameObject {

    protected RectF boundingRect = new RectF();
    float boundingRadius;
    float x, y;
    float dx, dy;

    public RangeBox(float x, float y){
        this.x = x;
        this.y = y;
        boundingRadius = Metrics.size(R.dimen.player_move_range_radius);
        boundingRect.set(x - boundingRadius, y - boundingRadius, x + boundingRadius, y + boundingRadius);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        boundingRect.set(x - boundingRadius, y - boundingRadius, x + boundingRadius, y + boundingRadius);
    }

    @Override
    public RectF getBoundingRect() {
        return boundingRect;
    }

    @Override
    public void update() {
        if (!MainGame.get().getIsTouchPlayer()) return;

        float tx = MainGame.get().tx;
        float ty = MainGame.get().ty;

        float angle = (float) Math.atan2(ty-y,tx-x);
        float speed = 300;
        float dist = speed * MainGame.getInstance().frameTime;
        dx = (float) (dist * Math.cos(angle));
        dy = (float) (dist * Math.sin(angle));
        if (dx > 0){
            if (x + dx > tx) {
                dx = tx - x;
                x = tx;
            } else {
                x += dx;
            }

        } else {
            if (x + dx < tx) {
                dx = tx - x;
                x = tx;
            } else {
                x += dx;
            }
        }

        if (dy > 0){
            if (y + dy > ty) {
                dy = ty - y;
                y = ty;
            } else {
                y += dy;
            }

        } else {
            if (y + dy < ty) {
                dy = ty - y;
                y = ty;
            } else {
                y += dy;
            }
        }
        boundingRect.set(x - boundingRadius, y - boundingRadius, x + boundingRadius, y + boundingRadius);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
