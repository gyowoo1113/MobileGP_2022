package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game;

import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.interfaces.BoxCollidable;

public class CollisionHelper {
    public static boolean collides(BoxCollidable o1, BoxCollidable o2) {
        RectF r1 = o1.getBoundingRect();
        RectF r2 = o2.getBoundingRect();

        if (r1.left > r2.right) return false;
        if (r1.top > r2.bottom) return false;
        if (r1.right < r2.left) return false;
        if (r1.bottom < r2.top) return false;

        return true;
    }

    public static boolean isPointInBox(BoxCollidable o,float x, float y)
    {
        RectF r = o.getBoundingRect();
        if (r.right < x) return false;
        if (r.left > x) return false;
        if (r.top > y) return false;
        if (r.bottom < y) return false;

        return true;
    }
}
