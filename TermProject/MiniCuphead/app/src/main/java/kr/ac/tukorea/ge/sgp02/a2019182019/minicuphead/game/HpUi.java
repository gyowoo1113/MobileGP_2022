package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.sprites.Sprite;

public class HpUi extends Sprite {
    private int index;
    private final int imageWidth;
    private final int imageHeight;

    public HpUi(float x, float y, int hp) {
        super(x, y, R.dimen.hp_w,R.dimen.hp_h, R.mipmap.hp,0);

        int imageWidth = bitmap.getWidth();
        this.imageWidth = imageWidth / 9;
        imageHeight = bitmap.getHeight();
        setHp(hp);
    }

    public void setHp(int hp)
    {
        this.index = hp;
        srcRect.set(index * imageWidth, 0, (index + 1) * imageWidth, imageHeight);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
}
