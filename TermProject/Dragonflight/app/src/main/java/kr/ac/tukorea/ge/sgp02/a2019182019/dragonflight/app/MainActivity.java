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
        // Resume: 다시 그 예약을 재개함
        // 맨처음 Create 된 다음 한번 불림 (Pause 뿐만아니라)
        // Create - Paused 상태로 Create 되는것이기 때문
        super.onResume();
        GameView.view.resumeGame();
    }

    @Override
    protected void onDestroy() {
        GameView.view = null;
        MainGame.clear();
        super.onDestroy();
    }

    // setContentVeiw -> xml load됨
    // findViewId로 찾을 수 있음
    // or 아예 xml 쓰지말고 Member variable로 GameView 받아서 해도됨
}