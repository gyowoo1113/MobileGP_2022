package kr.ac.tukorea.ge.sgp02.a2019182019.dragonflight.framework;

import android.graphics.Canvas;
import android.graphics.Rect;

import kr.ac.tukorea.ge.sgp02.a2019182019.dragonflight.game.MainGame;

public class AnimSprite extends Sprite {
    protected final int imageWidth;
    protected final int imageHeight;
    protected final int frameCount;
    private final float framesPerSecond;
    private Rect srcRect = new Rect();
    private int frameIndex;
    //private float time = 0;
    private long createOn;

    public AnimSprite(float x, float y, float w, float h, int bitmapResId,float framesPerSecond, int frameCount) {
        super(x, y, w, h, bitmapResId);

        imageHeight = bitmap.getHeight();
        if (frameCount == 0) {
            imageWidth = imageHeight;
            frameCount = bitmap.getWidth() / imageHeight;
        } else {
            imageWidth = bitmap.getWidth() / frameCount;
        }

        this.framesPerSecond = framesPerSecond;
        this.frameCount = frameCount;
        srcRect.set(0, 0, imageWidth, imageHeight);
    }

    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = (now - createOn) / 1000.0f;
        int frameIndex = Math.round(time*framesPerSecond) % frameCount;
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
}

