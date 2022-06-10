package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.resource.Sound;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.MainGame;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Sound.playMusic(MainActivity.this,R.raw.main_bgm);
    }

    public void onBtnStart(View view) {
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);
    }
}