package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.GameObject;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Metrics;

public class EnemyGenerator implements GameObject {
    private static final float INITIAL_SPAWN_INTERVAL = 2.0f;
    private final float spawnInterval;
    private final float fallSpeed;
    private float elapsedTime;
    private int wave;
    private int count;
    private int raw;

    public EnemyGenerator() {
        this.spawnInterval = INITIAL_SPAWN_INTERVAL;
        this.fallSpeed = Metrics.size(R.dimen.enemy_initial_speed);
        Enemy.size = Metrics.height / 5.0f * 0.9f;
        wave = 0;
        count = 0;
        raw = 0;
    }

    @Override
    public void update() {
        float frameTime = MainGame.getInstance().frameTime;
        elapsedTime += frameTime;
        if (elapsedTime > spawnInterval) {
            spawn();
            elapsedTime -= spawnInterval;
        }
    }

    private void spawn() {
        Random r = new Random();

        if (count == 0){
            raw = r.nextInt(5);
        }

        int i = raw*2+1;
        float tenth = Metrics.height / 10;
        Enemy enemy = Enemy.get(1, tenth * i, fallSpeed);
        MainGame.getInstance().add(MainGame.Layer.enemy, enemy);
        ++count;

        if (count == 4){
            count = 0;
        }
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
