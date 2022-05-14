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
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Recyclable;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.RecycleBin;

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
    public boolean isTouch = false;
    public float tx,ty;

    private MainGame() {

    }

    public boolean getIsTouchPlayer(){
        return isTouchPlayer;
    }

    private static MainGame singleton;
    protected ArrayList<ArrayList<GameObject>> layers;

    public enum Layer {
        bg1, bullet, enemy, player,boss_bullet,boss, bg2, controller, COUNT
    }
    private Cuphead cuphead;
    private Paint collisionPaint;

    public void init() {
        initLayers(Layer.COUNT.ordinal());

        add(Layer.controller, new EnemyGenerator());
        add(Layer.controller, new CollisionChecker());

        float cupheadY = Metrics.height - Metrics.size(R.dimen.cuphead_y_offset);
        float cupheadX = Metrics.size(R.dimen.cuphead_y_offset);

        cuphead = new Cuphead(Metrics.width / 2, cupheadY);
        cuphead = new Cuphead(cupheadX, Metrics.height/2);
        add(Layer.player, cuphead);

        float bossX = Metrics.width - Metrics.size(R.dimen.boss_x_offest);
        Boss boss = new Boss(bossX,Metrics.height/2);
        add(Layer.boss,boss);

        moveBoundingBox = new RangeBox(cupheadX, Metrics.height/2);
        add(Layer.player,moveBoundingBox);

        add(Layer.bg1, new HorzScrollBackground(R.mipmap.birdhouse_bg_8, Metrics.size(R.dimen.bg_speed_8)));
        add(Layer.bg1, new HorzScrollBackground(R.mipmap.birdhouse_bg_7, Metrics.size(R.dimen.bg_speed_7)));
        add(Layer.bg1, new HorzScrollBackground(R.mipmap.birdhouse_bg_6, Metrics.size(R.dimen.bg_speed_6)));
        add(Layer.bg1, new HorzScrollBackground(R.mipmap.birdhouse_bg_5, Metrics.size(R.dimen.bg_speed_5)));
        add(Layer.bg1, new HorzScrollBackground(R.mipmap.birdhouse_bg_4, Metrics.size(R.dimen.bg_speed_4)));
        add(Layer.bg1, new HorzScrollBackground(R.mipmap.birdhouse_bg_3, Metrics.size(R.dimen.bg_speed_3)));
        add(Layer.bg1, new HorzScrollBackground(R.mipmap.birdhouse_bg_2, Metrics.size(R.dimen.bg_speed_2)));
        add(Layer.bg2, new HorzScrollBackground(R.mipmap.birdhouse_bg_1, Metrics.size(R.dimen.bg_speed_1)));

        collisionPaint = new Paint();
        collisionPaint.setColor(Color.RED);
        collisionPaint.setStyle(Paint.Style.STROKE);
        collisionPaint.setStrokeWidth(10);
    }

    private void initLayers(int count) {
        layers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            layers.add(new ArrayList<>());
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isTouch = true;
                tx = x;
                ty = y;

                if (isPlayerInBox(x, y)) {
                    setPlayerAction(x, y);
                    return true;
                }

            case MotionEvent.ACTION_MOVE:
                if (!isTouchPlayer){
                    return false;
                }
                isTouch = false;

                tx = x;
                ty = y;
                cuphead.setPosition(x, y,moveBoundingBox);
                return true;

            case MotionEvent.ACTION_UP:
                initPlayerAction(x, y);
                isTouch = false;
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
        for (ArrayList<GameObject> gameObjects : layers) {
            for (GameObject gobj : gameObjects) {
                gobj.update();
            }
        }
    }

    public void draw(Canvas canvas) {
        for (ArrayList<GameObject> gameObjects : layers) {
            for (GameObject gobj : gameObjects) {
                gobj.draw(canvas);
//                if (gobj instanceof BoxCollidable) {
//                    RectF box = ((BoxCollidable) gobj).getBoundingRect();
//                    canvas.drawRect(box, collisionPaint);
//                }
            }
        }
    }

    public void add(Layer layer, GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> gameObjects = layers.get(layer.ordinal());
                gameObjects.add(gameObject);
            }
        });
    }

    public void remove(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                for (ArrayList<GameObject> gameObjects : layers) {
                    boolean removed = gameObjects.remove(gameObject);
                    if (!removed) continue;
                    if (gameObject instanceof Recyclable) {
                        RecycleBin.add((Recyclable) gameObject);
                    }
                    break;
                }
            }
        });
    }

    public ArrayList<GameObject> objectsAt(Layer layer) {
        return layers.get(layer.ordinal());
    }

    public void switchBullet() {
        cuphead.switchBullet();
    }
}
