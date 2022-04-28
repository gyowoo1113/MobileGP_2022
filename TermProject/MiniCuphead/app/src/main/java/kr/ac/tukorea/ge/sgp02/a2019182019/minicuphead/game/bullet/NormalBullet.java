package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.RecycleBin;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.MainGame;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet.Bullet;

public class NormalBullet extends Bullet {
    private NormalBullet(float x, float y) {
        super(x, y,R.dimen.bomb_bullet_radius, R.mipmap.bullet_normal);
        this.dx = -Metrics.size(R.dimen.laser_speed);
        boundingRect.set(x - radius, y - radius, x + radius, y + radius);
    }

    public static NormalBullet get(float x, float y) {
        NormalBullet bullet = (NormalBullet) RecycleBin.get(NormalBullet.class);
        if (bullet != null) {
            bullet.set(x, y);
            return bullet;
        }
        return new NormalBullet(x,y);
    }

    @Override
    public void update() {
        MainGame game = MainGame.getInstance();
        float frameTime = game.frameTime;
        x -= dx * frameTime;

        float radius = dstRect.width() / 2;
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
        boundingRect.set(dstRect);

        if (x < 0) {
            game.remove(this);
        }
    }
}
