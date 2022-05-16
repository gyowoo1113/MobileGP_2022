package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.GameView;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.MainGame;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this, null));
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
        MainGame.clear();
        super.onDestroy();
    }
}
