package kr.ac.tukorea.ge.sgp02.a2019182019.dragonflight.framework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import kr.ac.tukorea.ge.sgp02.a2019182019.dragonflight.game.MainGame;

public class GameView extends View implements Choreographer.FrameCallback {
    private static final String TAG = GameView.class.getSimpleName();

    private long previousTimeNanos;
    private int framesPerSecond;
    private Paint fpsPaint = new Paint();

    public static GameView view;
    private boolean initialized;
    private boolean running;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //initView();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Metrics.width = w;
        Metrics.height = h;
        
        if (!initialized) {
            initView();
            initialized = true;
            running = true;
        }
    }

    private void initView() {
        view = this;

        MainGame game = MainGame.getInstance();
        game.init();

        fpsPaint.setColor(Color.BLUE);
        fpsPaint.setTextSize(100);

        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    public void doFrame(long currentTimeNanos) {
        if (!running){
            Log.d(TAG,"Running is false on doFrame()");
            return;
        }
        long now = currentTimeNanos;
        int elapsed = (int) (now - previousTimeNanos);
        if (elapsed != 0) {
            framesPerSecond = 1_000_000_000 / elapsed;
            //Log.v(TAG, "Elapsed: " + elapsed + " FPS: " + framesPerSecond);
            previousTimeNanos = now;
            MainGame.getInstance().update(elapsed);
            invalidate();
        }
        Choreographer.getInstance().postFrameCallback(this);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        MainGame.getInstance().draw(canvas);
        canvas.drawText("FPS: " + framesPerSecond, framesPerSecond * 10, 100, fpsPaint);
//        Log.d(TAG, "onDraw()");
        canvas.drawText(""+MainGame.getInstance().objectCount(),10,100,fpsPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return MainGame.getInstance().onTouchEvent(event);
    }

    public void pauseGame() {
        // GameView 중지시킨다
        // 다음 framecallback 불리지 않게 하자
        running = false;
    }

    public void resumeGame() {
        if (initialized && !running){
            Log.d(TAG,"Resuming the game");
            running = true;
            Choreographer.getInstance().postFrameCallback(this);
        }
    }
}
