package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.BoxCollidable;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.CollisionHelper;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.GameObject;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.GameView;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.RangeBox;

public class MainGame {
    private boolean isTouchPlayer = false;
    private RangeBox moveBoundingBox;

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
    private Paint collisionPaint;

    public void init() {
        objects.clear();

        float cupheadY = Metrics.height - Metrics.size(R.dimen.cuphead_y_offset);
        float cupheadX = Metrics.size(R.dimen.cuphead_y_offset);

        cuphead = new Cuphead(Metrics.width / 2, cupheadY);
        cuphead = new Cuphead(cupheadX, Metrics.height/2);
        objects.add(cuphead);

        moveBoundingBox = new RangeBox(cupheadX, Metrics.height/2);
        objects.add(moveBoundingBox);

        collisionPaint = new Paint();
        collisionPaint.setColor(Color.RED);
        collisionPaint.setStyle(Paint.Style.STROKE);
        collisionPaint.setStrokeWidth(10);
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (isPlayerInBox(x, y)) {
                    setPlayerAction(x, y);
                    return true;
                }

            case MotionEvent.ACTION_MOVE:
                if (!isTouchPlayer){
                    return false;
                }
                cuphead.setPosition(x, y,moveBoundingBox);
                return true;

            case MotionEvent.ACTION_UP:
                initPlayerAction(x, y);
        }
        return false;
    }

    private void initPlayerAction(int x, int y) {
        isTouchPlayer = false;
        cuphead.setFire(false);
        moveBoundingBox.setPosition(x, y);
    }

    private boolean isPlayerInBox(int x, int y) {
        if (!CollisionHelper.isPointInBox(cuphead, x, y)) return false;
        if (isTouchPlayer) return false;
        return true;
    }

    private void setPlayerAction(int x, int y) {
        isTouchPlayer = true;
        moveBoundingBox.setPosition(x, y);
        cuphead.setFire(true);
    }

    public void update(int elapsedNanos) {
        frameTime = elapsedNanos * 1e-9f;
        for (GameObject gobj : objects){
            gobj.update();
        }
    }

    public void draw(Canvas canvas) {
        for (GameObject gobj : objects) {
            gobj.draw(canvas);
            if (gobj instanceof BoxCollidable) {
                RectF rect = ((BoxCollidable) gobj).getBoundingRect();

                canvas.drawRect(rect, collisionPaint);
            }
        }
    }

    public void add(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                objects.add(gameObject);
            }
        });
    }

    public void remove(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                objects.remove(gameObject);
            }
        });
    }

    public void switchBullet() {
        cuphead.switchBullet();
    }
}
