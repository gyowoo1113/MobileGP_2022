package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;

public class ResultActivity extends AppCompatActivity {
    private ImageView cupheadImageView;
    private ImageView titleImageView;
    private AnimationDrawable cupheadAnimationDrawable;
    private AnimationDrawable titleAnimationDrawable;

    protected MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        playMusic(R.raw.result_bgm);
        cupheadImageView = findViewById(R.id.cupheadImageView);
        titleImageView =  findViewById(R.id.titleImageView);

        cupheadAnimationDrawable = (AnimationDrawable) cupheadImageView.getDrawable();
        cupheadAnimationDrawable.start();
        titleAnimationDrawable = (AnimationDrawable) titleImageView.getDrawable();
        titleAnimationDrawable.start();
    }

    public void playMusic(int resId) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        mediaPlayer = MediaPlayer.create(ResultActivity.this, resId);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }
}