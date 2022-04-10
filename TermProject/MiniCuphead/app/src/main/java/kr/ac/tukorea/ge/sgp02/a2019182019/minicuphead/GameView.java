package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View implements Choreographer.FrameCallback{

    public static GameView view;

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
    protected void onDraw(Canvas canvas) {
        MainGame.getInstance().draw(canvas);
    }

    @Override
    public void doFrame(long l) {

    }
}
