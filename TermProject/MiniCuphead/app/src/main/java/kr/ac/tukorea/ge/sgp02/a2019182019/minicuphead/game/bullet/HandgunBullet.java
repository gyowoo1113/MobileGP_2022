package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.RecycleBin;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.MainGame;

public class HandgunBullet extends Bullet{
    float tx,ty;
    private HandgunBullet(float x, float y, float dy) {
        super(x, y, R.dimen.handgun_bullet_w, R.dimen.handgun_bullet_h, R.mipmap.handgun_bullet);
        this.dx = Metrics.size(R.dimen.handgun_speed);
        this.dy = dy;
        tx = x;
        ty = y;
        boundingRect.set(x - w/2, y - h/2, x + w/2, y + h/2);
    }

    public static HandgunBullet get(float x, float y, float dy) {
        HandgunBullet bullet = (HandgunBullet) RecycleBin.get(HandgunBullet.class);
        if (bullet != null) {
            bullet.set(x, y, dy);
            return bullet;
        }
        return new HandgunBullet(x,y,dy);
    }

    @Override
    public void update() {
        MainGame game = MainGame.getInstance();
        float frameTime = game.frameTime;
        x -= dx * frameTime;
        y -= dy * frameTime;

        angle = (float) Math.atan2(ty - y, tx-x) - (float)Math.PI/2;

        updateDstRect(x,y);
        boundingRect.set(dstRect);

        if (x > Metrics.width) {
            game.remove(this);
        }
    }

    @Override
    public int getPower(){
        return 10;
    }

    protected void set(float x, float y, float dy) {
        this.x = x;
        this.y = y;
        this.dy = dy;
        this.tx = x;
        this.ty = y;
    }
}
