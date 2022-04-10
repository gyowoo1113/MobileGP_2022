package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

public class MainGame {
    public static MainGame getInstance() {
        if (singleton == null) {
            singleton = new MainGame();
        }
        return singleton;
    }

    private MainGame() {

    }

    private static MainGame singleton;

    public void init() {

    }

    public void update(int elapsedNanos) {

    }

    public void draw(Canvas canvas) {
    }
}
