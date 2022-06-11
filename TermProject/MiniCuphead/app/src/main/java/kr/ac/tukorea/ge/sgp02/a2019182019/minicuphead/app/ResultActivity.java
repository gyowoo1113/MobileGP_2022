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

        setParameter();

    }

    private void setParameter() {
        boolean clear = getIntent().getExtras().getBoolean("boss");
        int mon = getIntent().getExtras().getInt("monster");
        int event = getIntent().getExtras().getInt("event");
        int hp = getIntent().getExtras().getInt("player");

        int success = (clear) ? 1 : 0;
        bossTextView.setText("BOSS ....................... " + success + "/1");
        monTextView.setText("MONSTER ............... " + mon);
        eventTextView.setText("EVENT MONSTER ... " + event);
        hpTextView.setText("LIFE ......................... " + hp + "/8");
        setScore(calculateScore(clear,mon,event,hp));
    }

    private int calculateScore(boolean clear, int mon, int event, int hp){
        int score = 0;
        if (clear == false) return score;
        ++score;
        if (mon > 5) ++score;
        if (event > 1) ++score;
        if (hp > 5) ++score;
        return score;
    }

    private void setScore(int score){
        String rank[] = {"F","D","C","B","A"};
        rankTextView.setText(rank[score]);
    }
}