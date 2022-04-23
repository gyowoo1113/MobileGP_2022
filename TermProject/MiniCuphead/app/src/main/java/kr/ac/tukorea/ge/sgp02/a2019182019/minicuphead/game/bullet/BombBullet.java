package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet;

import android.graphics.Color;
import android.graphics.Paint;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.MainGame;

public class BombBullet extends Bullet {
    public BombBullet(float x, float y) {
        super(x, y);
        this.length = Metrics.size(R.dimen.laser_length);
        this.dx = -Metrics.size(R.dimen.laser_speed);
        this.dy = -Metrics.size(R.dimen.laser_speed);

        if (paint == null) {
            paint = new Paint();
            paint.setColor(Color.RED);
            laserWidth = Metrics.size(R.dimen.laser_width);
            paint.setStrokeWidth(laserWidth);
        }
    }

    @Override
    public void update() {
        float frameTime = MainGame.getInstance().frameTime;
        x -= dx * frameTime;
        y -= dy * frameTime;

        float hw = laserWidth / 2;
        boundingRect.set(x , y - hw, x - length, y + hw);

        if (x < 0 || y < 0) {
            MainGame.getInstance().remove(this);
        }
    }
}
