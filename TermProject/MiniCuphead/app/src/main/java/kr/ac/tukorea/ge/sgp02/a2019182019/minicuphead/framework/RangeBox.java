package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework;

import android.graphics.Canvas;
import android.graphics.RectF;

import androidx.lifecycle.SavedStateHandle;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;

public class RangeBox implements BoxCollidable, GameObject{

    protected RectF boundingRect = new RectF();
    float boundingRadius;

    public RangeBox(float x, float y){
        boundingRadius = Metrics.size(R.dimen.player_move_range_radius);
        boundingRect.set(x - boundingRadius, y - boundingRadius, x + boundingRadius, y + boundingRadius);
    }

    public void setPosition(float x, float y) {
        boundingRect.set(x - boundingRadius, y - boundingRadius, x + boundingRadius, y + boundingRadius);
    }

    @Override
    public RectF getBoundingRect() {
        return boundingRect;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {

    }
}
