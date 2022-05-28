package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game.BaseGame;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.interfaces.BoxCollidable;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game.CollisionHelper;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.resource.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game.RangeBox;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.resource.Sound;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.sprites.Sprite;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet.BombBullet;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet.Bullet;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet.NormalBullet;

public class Cuphead extends Sprite implements BoxCollidable {
    private static final String TAG = Cuphead.class.getSimpleName();

    private float elapsedTimeForFire;
    private float fireInterval;
    private float bombInterval;
    private float interval;
    protected RectF boundingRect = new RectF();
    private boolean isFire;
    private boolean bulletToggle = false;
    float heightVal;
    private boolean isBomb = false;
    int life = 8;
    private HpUi hpUI;

    public Cuphead(float x, float y) {
        super(x, y, R.dimen.cuphead_w,R.dimen.cuphead_h, R.mipmap.player_normal,0);

        fireInterval = Metrics.floatValue(R.dimen.cuphead_fire_interval);
        bombInterval = Metrics.floatValue(R.dimen.cuphead_bomb_interval);

        interval = (isBomb) ? bombInterval : fireInterval;

        heightVal = Metrics.size(R.dimen.bulletHeight);

        float radius = dstRect.width() / 2;
        boundingRect.set(x - radius, y - radius, x + radius, y + radius);

        float hpX  = Metrics.size(R.dimen.hp_x_offset);
        float hpY = Metrics.height - Metrics.size(R.dimen.hp_y_offset);

        hpUI = new HpUi(hpX,hpY,life);
        MainGame.get().add(MainGame.Layer.ui, hpUI);
    }

    public void update() {
        float frameTime = BaseGame.getInstance().frameTime;
        elapsedTimeForFire += frameTime;
        if (elapsedTimeForFire > interval && isFire) {
            fire();
            elapsedTimeForFire -= interval;
        }
        boundingRect.set(dstRect);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void fire() {
        if (isBomb)
            Sound.playMusic(R.raw.player_plane_bomb);

        bulletToggle = !bulletToggle;
        float val = (bulletToggle) ? +heightVal : -heightVal;
        
        Bullet bullet = (isBomb) ? BombBullet.get(x, y) : NormalBullet.get(x, y + val);
        MainGame.get().add(MainGame.Layer.bullet, bullet);

    }

    public void setPosition(float x, float y, RangeBox moveBoundingBox) {
        if (!CollisionHelper.isPointInBox(moveBoundingBox,x,y)) return;

        this.x = x;
        this.y = y;
        float _w = dstRect.width() / 2;
        float _h = dstRect.height() / 2;
        dstRect.set(x - _w, y - _h, x + _w, y + _h);
    }

    @Override
    public RectF getBoundingRect() {
        return boundingRect;
    }

    public void setFire(boolean fire) {
        isFire = fire;
    }

    public void switchBullet() {
        isBomb = !isBomb;
        interval = (isBomb) ? bombInterval : fireInterval;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public boolean decreaseLife(int power) {
        life -= power;
        hpUI.setHp(life);

        if (life <= 0) return true;
        return false;
    }
}

