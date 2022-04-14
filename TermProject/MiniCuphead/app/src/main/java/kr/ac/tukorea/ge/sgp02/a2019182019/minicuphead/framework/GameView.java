package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.MainGame;

public class GameView extends View implements Choreographer.FrameCallback{

    private long previousTimeNanos;
    public static GameView view;
    private boolean initialized;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void initView() {
        view = this;

        MainGame game = MainGame.getInstance();
        game.init();

        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Metrics.width = w;
        Metrics.height = h;

        if (!initialized) {
            initView();
            initialized = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        MainGame.getInstance().draw(canvas);
    }

    @Override
    public void doFrame(long currentTimeNanos) {
        long now = currentTimeNanos;
        int elapsed = (int) (now - previousTimeNanos);
        if (elapsed != 0) {
            previousTimeNanos = now;

            MainGame.getInstance().update(elapsed);
            invalidate();
        }
        Choreographer.getInstance().postFrameCallback(this);
    }
}
