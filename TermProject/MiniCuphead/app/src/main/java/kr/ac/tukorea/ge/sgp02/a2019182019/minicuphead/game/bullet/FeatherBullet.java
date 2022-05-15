package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.RecycleBin;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.MainGame;

public class FeatherBullet extends Bullet{
    private float fireSpeed;
    private float tx,ty;
    private FeatherBullet(float x, float y, float dx, float dy) {
        super(x, y, R.dimen.feather_w, R.dimen.feather_h, R.mipmap.boss_attack_flap_feathers,10.0f,8);
        fireSpeed = Metrics.size(R.dimen.feather_fire_speed);
        this.dx = dx;
        this.dy = dy;
        this.tx = x;
        this.ty = y;
        boundingRect.set(x - w/2, y - h/2, x + w/2, y + h/2);
    }

    public static FeatherBullet get(float x, float y, float dx, float dy) {
        FeatherBullet bullet = (FeatherBullet) RecycleBin.get(FeatherBullet.class);
        if (bullet != null) {
            bullet.set(x, y, dx, dy);
            return bullet;
        }
        return new FeatherBullet(x,y,dx,dy);
    }

    @Override
    public void update() {
        MainGame game = MainGame.getInstance();
        float frameTime = game.frameTime;
        x -= dx * fireSpeed * frameTime;
        y -= dy * fireSpeed * frameTime;

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

    protected void set(float x, float y,float dx, float dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.tx = x;
        this.ty = y;
    }
}
