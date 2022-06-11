package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.app;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game.BaseGame;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game.GameView;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.resource.Sound;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.MainGame;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainGame.get();
        setContentView(R.layout.activity_game);
        Sound.playMusic(R.raw.game_bgm);

        MainGame.get().setFadeImageView(findViewById(R.id.fadeInGameImageView));
        MainGame.get().setFadeAnimationDrawable((AnimationDrawable) MainGame.get().getFadeImageView().getDrawable());
    }

    @Override
    protected void onPause() {
        GameView.view.pauseGame();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GameView.view.resumeGame();
    }

    @Override
    protected void onDestroy() {
        GameView.view = null;
        BaseGame.clear();
        super.onDestroy();
    }

    public void onSwitchBtn(View view) {
        MainGame.get().switchBullet();
    }
}
