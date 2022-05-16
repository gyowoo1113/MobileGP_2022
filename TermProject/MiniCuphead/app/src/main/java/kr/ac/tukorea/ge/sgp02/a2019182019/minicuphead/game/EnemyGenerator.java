package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.BaseGame;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.GameObject;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.Metrics;

public class EnemyGenerator implements GameObject {
    private static final float INITIAL_SPAWN_INTERVAL = 2.0f;
    private final float spawnInterval;
    private final float fallSpeed;
    private float elapsedTime;
    private int count;
    private int max_count;
    private int raw;

    public EnemyGenerator() {
        this.spawnInterval = INITIAL_SPAWN_INTERVAL;
        this.fallSpeed = Metrics.size(R.dimen.enemy_initial_speed);
        Enemy.size = Metrics.height / 5.0f * 0.9f;
        count = 0;
        raw = 0;
        max_count = 2;
    }

    @Override
    public void update() {
        float frameTime = BaseGame.getInstance().frameTime;
        elapsedTime += frameTime;
        if (elapsedTime > spawnInterval) {
            spawn();
            elapsedTime -= spawnInterval;
        }
    }

    private void spawn() {
        if (count == 0){
            setGenerateValues();
        }

        int level = (count > max_count) ? 2 : 1;
        int i = raw*2+1;
        float tenth = Metrics.height / 10;
        Enemy enemy = Enemy.get(level, tenth * i, fallSpeed);
        MainGame.get().add(MainGame.Layer.enemy, enemy);

        count = (count > max_count) ? 0 : count + 1;
    }

    private void setGenerateValues() {
        Random r = new Random();
        int val;
        do{
            val = r.nextInt(5);
        }while(val == raw);
        raw = val;
        max_count = r.nextInt(3);
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
