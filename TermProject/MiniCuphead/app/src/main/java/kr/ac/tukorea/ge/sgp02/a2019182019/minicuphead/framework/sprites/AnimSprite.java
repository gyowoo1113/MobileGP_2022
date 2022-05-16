package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.sprites;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class AnimSprite extends Sprite {
    private final float framesPerSecond;
    private final int frameCount;
    private final int imageWidth;
    private final int imageHeight;
    private Paint paint = new Paint();
    protected float angle;

    private Rect srcRect = new Rect();
    private long createdOn;
    private int index = 0;

    public AnimSprite(float x, float y, float w, float h, int bitmapResId, float framesPerSecond, int frameCount) {
        super(x, y, w, h, bitmapResId);
        int imageWidth = bitmap.getWidth();
        imageHeight = bitmap.getHeight();
        this.framesPerSecond = framesPerSecond;
        if (frameCount == 0) {
            frameCount = imageWidth / imageHeight;
            imageWidth = imageHeight;
        } else {
            imageWidth = imageWidth / frameCount;
        }
        this.imageWidth = imageWidth;
        this.frameCount = frameCount;

        createdOn = System.currentTimeMillis();
        angle = -(float) (Math.PI / 2);
    }

    public AnimSprite(float x, float y, int resWID, int resHID, int bitmapResId, float framesPerSecond, int frameCount) {
        super(x, y, resWID, resHID, bitmapResId,0);
        int imageWidth = bitmap.getWidth();
        imageHeight = bitmap.getHeight();
        this.framesPerSecond = framesPerSecond;
        if (frameCount == 0) {
            frameCount = imageWidth / imageHeight;
            imageWidth = imageHeight;
        } else {
            imageWidth = imageWidth / frameCount;
        }
        this.imageWidth = imageWidth;
        this.frameCount = frameCount;

        createdOn = System.currentTimeMillis();
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        this.index = Math.round(time * framesPerSecond) % frameCount;
        srcRect.set(index * imageWidth, 0, (index + 1) * imageWidth, imageHeight);

        canvas.save();
        canvas.rotate((float) (angle * 180 / Math.PI) + 90, x, y);
        canvas.drawBitmap(bitmap, srcRect, dstRect, paint);
        canvas.restore();
    }

    public void setAlpha(int value){
        paint.setAlpha(value);
    }

    public int getIndex() {
        return index;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}