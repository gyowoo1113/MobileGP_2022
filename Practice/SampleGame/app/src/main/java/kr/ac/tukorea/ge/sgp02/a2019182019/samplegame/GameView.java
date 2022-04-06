package kr.ac.tukorea.ge.sgp02.a2019182019.samplegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View implements Choreographer.FrameCallback {
    private static final String TAG = GameView.class.getSimpleName();

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

        MainGame game = MainGame.getInstance();
        game.init();

        fpsPaint.setColor(Color.BLUE);
        fpsPaint.setTextSize(100);

        Choreographer.getInstance().postFrameCallback(this);    // 싱글톤 패턴
    }

    @Override
    protected void onDraw(Canvas canvas) {
        MainGame.getInstance().draw(canvas);
        canvas.drawText("FPS: " + framePerSecond , 100,100,fpsPaint);
        //Log.d(TAG, "onDraw()");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return MainGame.getInstance().onTouchEvent(event);
    }

    @Override
    public void doFrame(long currentTimeNanos) {
        long now = currentTimeNanos;
        int elapsed = (int) (now - previousTimeNanos);
        if (elapsed != 0) {
            framePerSecond = 1_000_000_000 / elapsed;
            previousTimeNanos = now;
            //Log.v(TAG,"elapsed: " + elapsed + " FPS: " + framePerSecond);

            MainGame.getInstance().update();
            invalidate();
        }
        Choreographer.getInstance().postFrameCallback(this);
    }
}
