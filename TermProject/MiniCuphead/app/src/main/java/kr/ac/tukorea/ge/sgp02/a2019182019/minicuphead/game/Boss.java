package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.AnimSprite;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.BoxCollidable;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.GameObject;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Metrics;

public class Boss implements BoxCollidable, GameObject {
    ArrayList<AnimSprite> states = new ArrayList<>();
    private int curState = State.idle.ordinal();
    AnimSprite currentSprite;
    float x, y;

    private enum State{
        //idle,flap_intro,flap_loop,flap_outro,headgun,headgun_outro,death,count
        idle,COUNT;
        static float[] w = {
            Metrics.size(R.dimen.boss_idle_w),
        };

        static float[] h = {
            Metrics.size(R.dimen.boss_idle_h),
        };

        static int[] resIds = {
            R.mipmap.boss_normal,
        };

        static float[] fps = {
                10.0f,
        };

        static int[] frameCount = {
            17,
        };
    }

    Boss(float x, float y) {

        for (int i=0; i<State.COUNT.ordinal(); ++i)
        {
            AnimSprite state = new AnimSprite(x,y, State.w[i], State.h[i], State.resIds[i], State.fps[i], State.frameCount[i]);
            states.add(state);
        }
    }

    @Override
    public void update() {
        currentSprite = states.get(curState);
        currentSprite.setXY(this.x,this.y);
    }

    @Override
    public void draw(Canvas canvas) {
        currentSprite = states.get(curState);
        currentSprite.draw(canvas);
    }

    @Override
    public RectF getBoundingRect() {
        return null;
    }
}
