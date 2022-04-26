package kr.ac.tukorea.ge.sgp02.a2019182019.dragonflight.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kr.ac.tukorea.ge.sgp02.a2019182019.dragonflight.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.dragonflight.framework.GameView;
import kr.ac.tukorea.ge.sgp02.a2019182019.dragonflight.game.MainGame;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        if (GameView.view != null) {
            GameView.view.pauseGame();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (GameView.view != null) {
            GameView.view.resumeGame();
        }
    }

    @Override
    protected void onDestroy() {
        GameView.view = null;
        MainGame.clear();
        super.onDestroy();
    }
}