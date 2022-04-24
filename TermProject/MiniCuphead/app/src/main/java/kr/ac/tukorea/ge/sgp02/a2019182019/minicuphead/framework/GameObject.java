package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface GameObject {
    public void update();
    public void draw(Canvas canvas);
    public boolean onTouchEvent(MotionEvent event);
}
