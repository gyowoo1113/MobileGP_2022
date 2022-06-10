package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game;

import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game.BaseGame;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game.CollisionHelper;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.interfaces.GameObject;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.resource.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game.RangeBox;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.resource.Sound;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.monster.Boss;

public class MainGame extends BaseGame {
    private boolean isTouchPlayer = false;
    private RangeBox moveBoundingBox;

    public static MainGame get() {
        if (singleton == null) {
            singleton = new MainGame();
        }
        return (MainGame) singleton;
    }

    public boolean isTouch = false;
    public float tx,ty;

    private MainGame() {

    }

    public boolean getIsTouchPlayer(){
        return isTouchPlayer;
    }

    public enum Layer {
        bg1, bullet, enemy, player,boss_bullet,boss, bg2,ui, controller, COUNT
    }
    private Cuphead cuphead;

    public void init() {
        super.init();

        initLayers(Layer.COUNT.ordinal());

        add(Layer.controller, new EnemyGenerator());
        add(Layer.controller, new CollisionChecker());

        float cupheadY = Metrics.height - Metrics.size(R.dimen.cuphead_y_offset);
        float cupheadX = Metrics.size(R.dimen.cuphead_y_offset);

        cuphead = new Cuphead(Metrics.width / 2, cupheadY);
        cuphead = new Cuphead(cupheadX, Metrics.height/2);
        add(Layer.player, cuphead);

        float bossX = Metrics.width - Metrics.size(R.dimen.boss_x_offest);
        Boss boss = new Boss(bossX,Metrics.height/2);
        add(Layer.boss,boss);

        moveBoundingBox = new RangeBox(cupheadX, Metrics.height/2);
        add(Layer.player,moveBoundingBox);

        add(Layer.bg1, new HorzScrollBackground(R.mipmap.birdhouse_bg_8, Metrics.size(R.dimen.bg_speed_8)));
        add(Layer.bg1, new HorzScrollBackground(R.mipmap.birdhouse_bg_7, Metrics.size(R.dimen.bg_speed_7)));
        add(Layer.bg1, new HorzScrollBackground(R.mipmap.birdhouse_bg_6, Metrics.size(R.dimen.bg_speed_6)));
        add(Layer.bg1, new HorzScrollBackground(R.mipmap.birdhouse_bg_5, Metrics.size(R.dimen.bg_speed_5)));
        add(Layer.bg1, new HorzScrollBackground(R.mipmap.birdhouse_bg_4, Metrics.size(R.dimen.bg_speed_4)));
        add(Layer.bg1, new HorzScrollBackground(R.mipmap.birdhouse_bg_3, Metrics.size(R.dimen.bg_speed_3)));
        add(Layer.bg1, new HorzScrollBackground(R.mipmap.birdhouse_bg_2, Metrics.size(R.dimen.bg_speed_2)));
        add(Layer.bg2, new HorzScrollBackground(R.mipmap.birdhouse_bg_1, Metrics.size(R.dimen.bg_speed_1)));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isTouch = true;
                tx = x;
                ty = y;

                if (isPlayerInBox(x, y)) {
                    setPlayerAction(x, y);

                    if(!cuphead.isBomb())
                        Sound.playLoopEffect(R.raw.player_plane_fire);
                    return true;
                }

            case MotionEvent.ACTION_MOVE:
                if (!isTouchPlayer){
                    return false;
                }
                isTouch = false;

                tx = x;
                ty = y;
                cuphead.setPosition(x, y,moveBoundingBox);
                return true;

            case MotionEvent.ACTION_UP:
                initPlayerAction(x, y);

                if(!cuphead.isBomb())
                    Sound.stopLoopEffect(R.raw.player_plane_fire);
                isTouch = false;
        }
        return false;
    }

    private void initPlayerAction(int x, int y) {
        isTouchPlayer = false;
        cuphead.setFire(false);
        moveBoundingBox.setPosition(x, y);
    }

    private boolean isPlayerInBox(int x, int y) {
        if (!CollisionHelper.isPointInBox(cuphead, x, y)) return false;
        if (isTouchPlayer) return false;
        return true;
    }

    private void setPlayerAction(int x, int y) {
        isTouchPlayer = true;
        moveBoundingBox.setPosition(x, y);
        cuphead.setFire(true);
    }

    public void add(Layer layer, GameObject gameObject) {
        add(layer.ordinal(),gameObject);
    }

    public ArrayList<GameObject> objectsAt(Layer layer) {
        return objectsAt(layer.ordinal());
    }

    public void switchBullet() {
        cuphead.switchBullet();
    }
}
