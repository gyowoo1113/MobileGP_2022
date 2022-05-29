package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.interfaces.GameObject;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.resource.BitmapPool;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.resource.Metrics;

public class Sprite implements GameObject {
    protected Bitmap bitmap;
    protected RectF dstRect = new RectF();
    protected Rect srcRect = new Rect();
    protected float x, y, radius, w,h;
    protected Paint paint = new Paint();

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

    public RectF getDstRect() {
        return dstRect;
    }

    public void updateDstRect(float x, float y) {
        this.x = x;
        this.y = y;
        dstRect.set(x - w / 2, y - h / 2, x + w / 2, y + h / 2);
    }

    @Override
    public void update(float frameTime) {
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, paint);
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

    public void setAlpha(int value){
        paint.setAlpha(value);
    }
}
