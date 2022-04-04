package kr.ac.tukorea.ge.sgp02.a2019182019.samplegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Fighter {
    private static Bitmap bitmap;
    private static Rect srcRect = new Rect();
    private Rect dstRect = new Rect();

    public Fighter(){
        dstRect.set(0,0,200,200);
    }

    public static void setBitmap(Bitmap bitmap) {
        Fighter.bitmap = bitmap;
        srcRect.set(0,0,bitmap.getWidth(),bitmap.getHeight());
    }

    public void update() {
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
}
