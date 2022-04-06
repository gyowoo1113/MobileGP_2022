package kr.ac.tukorea.ge.sgp02.a2019182019.samplegame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class Fighter implements GameObject {
    private static Bitmap bitmap;
    private static Rect srcRect = new Rect();
    private RectF dstRect = new RectF();

    private float x,y;
    private float dx,dy;
    private float tx,ty;

    public Fighter(float x, float y){
        this.x = x;
        this.y = y;
        float radius_dp = 100;
        DisplayMetrics displayMetrics = GameView.view.getResources().getDisplayMetrics();
        float radius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,radius_dp, displayMetrics);
        dstRect.set(x-radius,y-radius,x+radius,y+radius);
        tx = x;
        ty = y;

        if (Fighter.bitmap == null) {
            Resources res = GameView.view.getResources();
            Fighter.bitmap = BitmapFactory.decodeResource(res, R.mipmap.plane_240);
            srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        }
    }

    public void update() {
        float angle = (float) Math.atan2(ty-y,tx-x);
        float speed = 1000;
        float dist = speed * MainGame.getInstance().frameTime;
        dx = (float) (dist * Math.cos(angle));
        dy = (float) (dist * Math.sin(angle));

        if (dx > 0){
            if (x + dx > tx) {
                dx = tx - x;
                x = tx;
            } else {
                x += dx;
            }

        } else {
            if (x + dx < tx) {
                dx = tx - x;
                x = tx;
            } else {
                x += dx;
            }
        }

        if (dy > 0){
            if (y + dy > ty) {
                dy = ty - y;
                y = ty;
            } else {
                y += dy;
            }

        } else {
            if (y + dy < ty) {
                dy = ty - y;
                y = ty;
            } else {
                y += dy;
            }
        }
        dstRect.offset(dx,dy);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }

    public void setTargetPosition(int x, int y) {
        tx = x;
        ty = y;
    }
}
