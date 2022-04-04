package kr.ac.tukorea.ge.sgp02.a2019182019.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View implements Choreographer.FrameCallback {
    private static final String TAG = GameView.class.getSimpleName();

    Ball ball1,ball2;
    private long previousTimeNanos;
    private int framePerSecond;
    private Paint fpsPaint = new Paint();

    public static GameView view;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        view = this;

        Resources res = getResources();
        Bitmap soccerBitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
        Ball.setBitmap(soccerBitmap);

        ball1 = new Ball(10,10);
        ball2 = new Ball(7,15);

        fpsPaint.setColor(Color.BLUE);
        fpsPaint.setTextSize(100);

        Choreographer.getInstance().postFrameCallback(this);    // 싱글톤 패턴
    }

    @Override
    protected void onDraw(Canvas canvas) {
        ball1.draw(canvas);
        ball2.draw(canvas);
        canvas.drawText("FPS: " + framePerSecond , 100,100,fpsPaint);
        //Log.d(TAG, "onDraw()");
    }

    @Override
    public void doFrame(long currentTimeNanos) {
        long now = currentTimeNanos;
//        long now = System.currentTimeMillis();
        int elapsed = (int) (now - previousTimeNanos);
        framePerSecond = 1_000_000_000 / elapsed;
        previousTimeNanos = now;
        //Log.v(TAG,"elapsed: " + elapsed + " FPS: " + framePerSecond);

        update();
        invalidate();
        Choreographer.getInstance().postFrameCallback(this);
    }

    private void update() {
        ball1.update();
        ball2.update();
    }

}
