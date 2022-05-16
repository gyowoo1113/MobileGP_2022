package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Sprite implements GameObject {
    protected Bitmap bitmap;
    protected RectF dstRect = new RectF();
    protected float x, y, radius, w,h;
    public Sprite(float x, float y, int radiusDimenResId, int bitmapResId) {
        this.x = x;
        this.y = y;
        this.radius = Metrics.size(radiusDimenResId);
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
        bitmap = BitmapPool.get(bitmapResId);
    }

    public Sprite(float x, float y, float w, float h, int bitmapResId) {
        this.x = x;
        this.y = y;
        this.radius = w / 2;
        this.w = w;
        this.h = h;
        dstRect.set(x - w / 2, y - h / 2, x + w / 2, y + h / 2);
        bitmap = BitmapPool.get(bitmapResId);
    }

    public Sprite(float x, float y, int resWID, int resHID, int bitmapResId, int temp) {
        this.x = x;
        this.y = y;
        this.radius = w / 2;
        this.w = Metrics.size(resWID);
        this.h = Metrics.size(resHID);
        dstRect.set(x - w / 2, y - h / 2, x + w / 2, y + h / 2);
        bitmap = BitmapPool.get(bitmapResId);
    }

    public void setDstRectWithRadius() {
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
    }

    public void setDstRect(float width, float height) {
        dstRect.set(x - width / 2, y - height / 2, x + width / 2, y + height / 2);
    }

    public void updateDstRect(float x, float y) {
        this.x = x;
        this.y = y;
        dstRect.set(x - w / 2, y - h / 2, x + w / 2, y + h / 2);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void setXY(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getW() {
        return w;
    }

    public float getH() {
        return h;
    }
}
