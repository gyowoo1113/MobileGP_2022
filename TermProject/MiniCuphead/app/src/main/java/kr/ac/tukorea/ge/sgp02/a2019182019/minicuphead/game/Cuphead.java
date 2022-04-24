package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.BoxCollidable;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.CollisionHelper;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.RangeBox;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Sprite;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet.BombBullet;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet.Bullet;

public class Cuphead extends Sprite implements BoxCollidable {
    private static final String TAG = Cuphead.class.getSimpleName();
    private final Paint collisionPaint;

    private RangeBox moveBoundingBox;
    private boolean isTouchPlayer = false;

    private float elapsedTimeForFire;
    private float fireInterval;
    protected RectF boundingRect = new RectF();
    private boolean isFire;
    private boolean bulletToggle = false;
    float heightVal;

    public Cuphead(float x, float y) {
        super(x, y, R.dimen.cuphead_radius, R.mipmap.player_normal);
        //targetBitmap = BitmapPool.get(R.mipmap.target);
        fireInterval = Metrics.floatValue(R.dimen.cuphead_fire_interval);
        heightVal = Metrics.size(R.dimen.bulletHeight);

        float radius = dstRect.width() / 2;
        boundingRect.set(x - radius, y - radius, x + radius, y + radius);
        moveBoundingBox = new RangeBox(x, y);

        collisionPaint = new Paint();
        collisionPaint.setColor(Color.RED);
        collisionPaint.setStyle(Paint.Style.STROKE);
        collisionPaint.setStrokeWidth(10);
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;
        elapsedTimeForFire += frameTime;
        if (elapsedTimeForFire > fireInterval && isFire) {
            fire();
            elapsedTimeForFire -= fireInterval;
        }
        boundingRect.set(dstRect);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
        moveBoundingBox.draw(canvas);

        RectF rect = moveBoundingBox.getBoundingRect();
        canvas.drawRect(rect, collisionPaint);
    }

    public void fire() {
        bulletToggle = !bulletToggle;
        float val = (bulletToggle) ? +heightVal : -heightVal;
        //Bullet bullet = new Bullet(x, y + val);
        BombBullet bullet = new BombBullet(x, y);
        MainGame.getInstance().add(bullet);
    }

    public void setPosition(float x, float y, RangeBox moveBoundingBox) {
        if (!CollisionHelper.isPointInBox(moveBoundingBox,x,y))
        {return;}

        float radius = dstRect.width() / 2;
        this.x = x;
        this.y = y;
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
    }

    @Override
    public RectF getBoundingRect() {
        return boundingRect;
    }

    public void setFire(boolean fire) {
        isFire = fire;
    }

    @Override
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
                if (!isTouchPlayer) return false;
                setPosition(x, y,moveBoundingBox);
                return true;

            case MotionEvent.ACTION_UP:
                initPlayerAction(x, y);
        }
        return false;
    }

    private void initPlayerAction(int x, int y) {
        isTouchPlayer = false;
        setFire(false);
        moveBoundingBox.setPosition(x, y);
    }

    private boolean isPlayerInBox(int x, int y) {
        if (!CollisionHelper.isPointInBox(this, x, y)) return false;
        if (isTouchPlayer) return false;
        return true;
    }

    private void setPlayerAction(int x, int y) {
        isTouchPlayer = true;
        moveBoundingBox.setPosition(x, y);
        setFire(true);
    }
}

