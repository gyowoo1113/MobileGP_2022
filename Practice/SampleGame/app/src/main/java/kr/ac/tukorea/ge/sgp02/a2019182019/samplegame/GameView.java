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

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View implements Choreographer.FrameCallback {
    private static final String TAG = GameView.class.getSimpleName();
    private static final int BALL_COUNT = 10;

    private ArrayList<Ball> balls = new ArrayList<>();
    private Fighter fighter;

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

        Random random = new Random();
        for (int i = 0; i< BALL_COUNT; i++){
            int dx = random.nextInt(10)+5;
            int dy = random.nextInt(10)+5;
            Ball ball = new Ball(dx,dy);
            balls.add(ball);
        }

        fighter = new Fighter();

        fpsPaint.setColor(Color.BLUE);
        fpsPaint.setTextSize(100);

        Choreographer.getInstance().postFrameCallback(this);    // 싱글톤 패턴
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Ball ball : balls) {
            ball.draw(canvas);
        }
        fighter.draw(canvas);
        canvas.drawText("FPS: " + framePerSecond , 100,100,fpsPaint);
        //Log.d(TAG, "onDraw()");
    }

    @Override
    public void doFrame(long currentTimeNanos) {
        long now = currentTimeNanos;
//        long now = System.currentTimeMillis();
        int elapsed = (int) (now - previousTimeNanos);
        if (elapsed != 0) {
            framePerSecond = 1_000_000_000 / elapsed;
            previousTimeNanos = now;
            //Log.v(TAG,"elapsed: " + elapsed + " FPS: " + framePerSecond);
            update();
            invalidate();
        }
        Choreographer.getInstance().postFrameCallback(this);
    }

    private void update() {
        for (Ball ball : balls){
            ball.update();
        }
//        fighter.update();
    }

}
