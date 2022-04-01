package kr.ac.tukorea.ge.sgp02.a2019182019.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private Bitmap soccerBitmap;
    private Rect soccerSrcRect = new Rect();
    private Rect soccerDstRect = new Rect();

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(soccerBitmap,soccerSrcRect,soccerDstRect,null);
    }

    private void initView() {
        Resources res = getResources();
        soccerBitmap = BitmapFactory.decodeResource(res,R.mipmap.soccer_ball_240);

        soccerSrcRect.set(0,0,soccerBitmap.getWidth(),soccerBitmap.getHeight());
        soccerDstRect.set(0,0,200,200);
    }
}
