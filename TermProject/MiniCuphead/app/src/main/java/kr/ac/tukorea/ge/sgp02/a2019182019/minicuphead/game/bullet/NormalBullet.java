package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game.BaseGame;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.resource.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game.RecycleBin;

public class NormalBullet extends Bullet {
    private NormalBullet(float x, float y) {
        super(x, y,R.dimen.normal_bullet_w, R.dimen.normal_bullet_h, R.mipmap.bullet_normal);
        this.dx = -Metrics.size(R.dimen.laser_speed);
        boundingRect.set(x - w/2, y - h/2, x + w/2, y + h/2);
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
        BaseGame game = BaseGame.getInstance();
        float frameTime = game.frameTime;
        x -= dx * frameTime;

        float _w = dstRect.width() / 2;
        float _h = dstRect.height() / 2;
        dstRect.set(x - _w, y - _h, x + _w, y + _h);
        boundingRect.set(dstRect);

        if (x < 0) {
            game.remove(this);
        }
    }

    @Override
    public int getPower(){
        return 10;
    }
}
