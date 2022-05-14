package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.RecycleBin;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.MainGame;

public class HandgunBullet extends Bullet{
    private HandgunBullet(float x, float y) {
        super(x, y, R.dimen.handgun_bullet_w, R.dimen.handgun_bullet_h, R.mipmap.handgun_bullet);
        this.dx = Metrics.size(R.dimen.handgun_speed);
        boundingRect.set(x - w/2, y - h/2, x + w/2, y + h/2);
    }

    public static HandgunBullet get(float x, float y) {
        HandgunBullet bullet = (HandgunBullet) RecycleBin.get(HandgunBullet.class);
        if (bullet != null) {
            bullet.set(x, y);
            return bullet;
        }
        return new HandgunBullet(x,y);
    }

    @Override
    public void update() {
        MainGame game = MainGame.getInstance();
        float frameTime = game.frameTime;
        x -= dx * frameTime;

        float _w = dstRect.width() / 2;
        float _h = dstRect.height() / 2;
        dstRect.set(x - _w, y - _h, x + _w, y + _h);
        boundingRect.set(dstRect);

        if (x > Metrics.width) {
            game.remove(this);
        }
    }

    @Override
    public int getPower(){
        return 10;
    }
}
