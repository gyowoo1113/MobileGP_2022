package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game.GameView;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.resource.Sound;

public class ResultActivity extends AppCompatActivity {
    private ImageView cupheadImageView;
    private ImageView titleImageView;
    private AnimationDrawable cupheadAnimationDrawable;
    private AnimationDrawable titleAnimationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Sound.playMusic(ResultActivity.this,R.raw.result_bgm);
        cupheadImageView = findViewById(R.id.cupheadImageView);
        titleImageView =  findViewById(R.id.titleImageView);

        cupheadAnimationDrawable = (AnimationDrawable) cupheadImageView.getDrawable();
        cupheadAnimationDrawable.start();
        titleAnimationDrawable = (AnimationDrawable) titleImageView.getDrawable();
        titleAnimationDrawable.start();
    }
}