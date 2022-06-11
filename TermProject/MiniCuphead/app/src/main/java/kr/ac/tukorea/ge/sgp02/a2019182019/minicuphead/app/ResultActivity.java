package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game.GameView;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.resource.Sound;

public class ResultActivity extends AppCompatActivity {
    private ImageView cupheadImageView;
    private ImageView titleImageView;
    private ImageView fadeImageView;
    private AnimationDrawable cupheadAnimationDrawable;
    private AnimationDrawable titleAnimationDrawable;
    private AnimationDrawable fadeAnimationDrawable;

    private TextView bossTextView;
    private TextView monTextView;
    private TextView eventTextView;
    private TextView hpTextView;
    private TextView rankTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Sound.playMusic(ResultActivity.this, R.raw.result_bgm);
        cupheadImageView = findViewById(R.id.cupheadImageView);
        titleImageView = findViewById(R.id.titleImageView);
        fadeImageView = findViewById(R.id.fadeImageView);
        bossTextView = findViewById(R.id.bossTextView);
        monTextView  = findViewById(R.id.monsterTextView);
        eventTextView = findViewById(R.id.eventTextView);
        hpTextView = findViewById(R.id.hpTextView);
        rankTextView = findViewById(R.id.ScoreTextView);

        fadeAnimationDrawable = (AnimationDrawable) fadeImageView.getDrawable();
        fadeAnimationDrawable.start();
        cupheadAnimationDrawable = (AnimationDrawable) cupheadImageView.getDrawable();
        cupheadAnimationDrawable.start();
        titleAnimationDrawable = (AnimationDrawable) titleImageView.getDrawable();
        titleAnimationDrawable.start();
    }
}