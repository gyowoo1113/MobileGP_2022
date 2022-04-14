package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.GameObject;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Metrics;

public class MainGame {
    public static MainGame getInstance() {
        if (singleton == null) {
            singleton = new MainGame();
        }
        return singleton;
    }

    public float frameTime;

    private MainGame() {

    }

    private static MainGame singleton;
    private ArrayList<GameObject> objects = new ArrayList<>();
    private Cuphead cuphead;

    public void init() {
        objects.clear();

        float cupheadY = Metrics.height - Metrics.size(R.dimen.cuphead_y_offset);
        cuphead = new Cuphead(Metrics.width / 2, cupheadY);
        objects.add(cuphead);
    }

    public void update(int elapsedNanos) {
        for (GameObject gobj : objects){
            gobj.update();
        }
    }

    public void draw(Canvas canvas) {
        for (GameObject gobj : objects) {
            gobj.draw(canvas);
        }
    }

}
