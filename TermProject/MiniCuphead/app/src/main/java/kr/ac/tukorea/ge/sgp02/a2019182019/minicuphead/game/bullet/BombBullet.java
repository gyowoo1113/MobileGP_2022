package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.RecycleBin;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.MainGame;

public class BombBullet extends Bullet {
    public final float gravity;
    private BombBullet(float x, float y) {
        super(x, y, (int) Metrics.size(R.dimen.bomb_bullet_w),
                (int) Metrics.size(R.dimen.bomb_bullet_h), R.mipmap.bullet_bomb);
        this.dx = -Metrics.size(R.dimen.laser_speed);
        this.dy = Metrics.size(R.dimen.bullet_upper_speed);
        gravity = Metrics.size(R.dimen.bullet_gravity);
    }

    public static BombBullet get(float x, float y) {
        BombBullet bullet = (BombBullet) RecycleBin.get(BombBullet.class);
        if (bullet != null) {
            bullet.set(x, y);
            return bullet;
        }
        return new BombBullet(x,y);
    }

    @Override
    public void update() {
        float frameTime = MainGame.getInstance().frameTime;
        x -= dx * frameTime;
        y -= dy * frameTime;
        dy -=gravity*frameTime;

        float _w = dstRect.width() / 2;
        float _h = dstRect.height() / 2;
        dstRect.set(x - _w, y - _h, x + _w, y + _h);
        boundingRect.set(dstRect);

        if (x < 0 || y < 0) {
            MainGame.getInstance().remove(this);
        }
    }

    @Override
    protected void set(float x, float y) {
        this.x = x;
        this.y = y;
        this.dy = Metrics.size(R.dimen.bullet_upper_speed);
    }

    @Override
    public int getPower(){
        return 80;
    }
}
