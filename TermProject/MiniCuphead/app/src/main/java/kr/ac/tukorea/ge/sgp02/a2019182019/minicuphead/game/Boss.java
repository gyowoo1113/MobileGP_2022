package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.AnimSprite;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.BaseGame;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.BoxCollidable;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.GameObject;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet.Bullet;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet.FeatherBullet;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.bullet.HandgunBullet;

public class Boss implements BoxCollidable, GameObject {
    private static final String TAG = Boss.class.getSimpleName();
    private ArrayList<AnimSprite> states = new ArrayList<>();
    private State curState = State.idle;
    private AnimSprite currentSprite;
    float x, y;
    boolean isDown = true;
    static float updateElapsedTime;
    private int loop_count = 1;
    static final int inoutCount = 1;
    private float elapsedTimeForFire;
    private float interval;
    private RectF boundingBox = new RectF();
    protected int life, maxlife;
    private boolean isToggle = false;

    private enum State{
        idle,handgun,handgun_outro,flap_intro,flap_loop,flap_outro,COUNT;
        static float[] w = {
                Metrics.size(R.dimen.boss_idle_w),
                Metrics.size(R.dimen.boss_idle_w),
                Metrics.size(R.dimen.boss_idle_w),
                Metrics.size(R.dimen.boss_flap_w),
                Metrics.size(R.dimen.boss_flap_w),
                Metrics.size(R.dimen.boss_idle_w),
        };

        static float[] h = {
                Metrics.size(R.dimen.boss_idle_h),
                Metrics.size(R.dimen.boss_idle_h),
                Metrics.size(R.dimen.boss_idle_h),
                Metrics.size(R.dimen.boss_flap_h),
                Metrics.size(R.dimen.boss_flap_h),
                Metrics.size(R.dimen.boss_idle_h),
        };

        static int[] resIds = {
                R.mipmap.boss_normal,
                R.mipmap.boss_attack_handgun,
                R.mipmap.boss_attack_handgun_outro,
                R.mipmap.boss_attack_flap_intro,
                R.mipmap.boss_attack_flap_loop,
                R.mipmap.boss_attack_flap_outro,
        };

        static float[] fps = {
                12.0f,
                12.0f,
                12.0f,
                12.0f,
                9.0f,
                12.0f,
        };

        static int[] frameCount = {
                17,
                17,
                17,
                9,
                9,
                3,
        };
    }

    Boss(float x, float y) {
        this.x = x;
        this.y = y;
        for (int i=0; i<State.COUNT.ordinal(); ++i)
        {
            AnimSprite state = new AnimSprite(x,y, State.w[i], State.h[i], State.resIds[i], State.fps[i], State.frameCount[i]);
            states.add(state);
        }
        updateElapsedTime = Metrics.size(R.dimen.boss_update_speed);
        currentSprite = states.get(curState.ordinal());
        interval = Metrics.floatValue(R.dimen.feather_fire_interval);
        life = 2000;
    }

    @Override
    public void update() {
        float frameTime = BaseGame.getInstance().frameTime;

        switch(curState)
        {
            case idle:
                updateHeight();
                break;

            case flap_loop:
                elapsedTimeForFire += frameTime;
                if (elapsedTimeForFire > interval) {
                    featherFire();
                    elapsedTimeForFire -= interval;
                }
                break;
        }

        if (isAnimEnd()){
            ++loop_count;
            switchState();
        }

        currentSprite.updateDstRect(this.x,this.y);
        boundingBox.set(currentSprite.getDstRect());
    }

    private void featherFire() {
        int shotNum = 12;
        float angle = 360 / shotNum;

        for(int i =0;i<shotNum; ++i) {
            float valAngle = angle*i;
            if (isToggle) {valAngle += angle/2;}
            float dx = (float) Math.sin(valAngle);
            float dy = (float) Math.cos(valAngle);
            Bullet bullet = FeatherBullet.get(x, y,dx,dy);
            MainGame.get().add(MainGame.Layer.boss_bullet, bullet);
        }
        isToggle = !isToggle;
    }

    private void handgunFire() {
        float dy = Metrics.size(R.dimen.handgun_speed_y);
        float[] vals = {dy,-dy,0};

        for(int i=0; i<3; ++i)
        {
            Bullet bullet = HandgunBullet.get(x, y,vals[i]);
            MainGame.get().add(MainGame.Layer.boss_bullet, bullet);
        }
    }

    private void switchState() {
        int cnt = getLoopCnt();
        Random r = new Random();

        if (loop_count > cnt)
            loop_count = loop_count%cnt;

        if (loop_count == cnt)
        {
            switch(curState)
            {
                case idle:
                    boolean check = r.nextBoolean();
                    State st = (check) ? State.handgun : State.flap_intro;
                    setState(st);

                    if (st == State.handgun)
                        handgunFire();

                    break;
                case handgun:
                    setState(State.handgun_outro);
                    break;

                case flap_intro:
                    setState(State.flap_loop);
                    break;

                case flap_loop:
                    setState(State.flap_outro);
                    break;

                case handgun_outro:
                case flap_outro:
                    setState(State.idle);
                    break;
            }
        }
    }

    private int getLoopCnt() {
        Random r = new Random();
        if (curState == State.idle) {
            int cnt = r.nextInt(2)+3;
            return cnt;
        }
        if (curState == State.flap_loop) {
            int cnt =  r.nextInt(2)+10;
            return cnt;
        }
        return inoutCount;
    }

    private boolean isAnimEnd() {
        return State.frameCount[curState.ordinal()] == currentSprite.getIndex() + 1;
    }

    private void setState(State handgun_outro) {
        curState = handgun_outro;
        currentSprite = states.get(curState.ordinal());
        currentSprite.setCreatedOn(System.currentTimeMillis());
        currentSprite.setIndex(0);
        loop_count = 0;
        elapsedTimeForFire = 0;
    }

    private void updateHeight() {
        float h = currentSprite.getH();
        float frameTime = BaseGame.getInstance().frameTime;
        if (isDown)
        {
            if (this.y + h/2 > Metrics.height)
                isDown = false;
            else
                this.y += frameTime * updateElapsedTime;
        }
        else
        {
            if (this.y - h/2 < 0)
                isDown = true;
            else
                this.y -= frameTime * updateElapsedTime;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        currentSprite.draw(canvas);
    }

    @Override
    public RectF getBoundingRect() {
        return boundingBox;
    }

    public boolean decreaseLife(int power) {
        life -= power;
        if (life <= 0) return true;
        return false;
    }
}
