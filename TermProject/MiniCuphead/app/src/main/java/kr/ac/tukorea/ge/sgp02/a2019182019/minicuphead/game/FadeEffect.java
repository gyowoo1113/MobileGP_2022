package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.app.ResultActivity;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game.BaseGame;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game.GameView;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.interfaces.GameObject;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.resource.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.resource.Sound;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.sprites.AnimSprite;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.sprites.CustomAnimationDrawableNew;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.monster.Boss;

public class FadeEffect implements GameObject {

    static boolean isFadeStart = false;

    public FadeEffect() {
        Sound.stopLoopEffect(R.raw.player_plane_fire);
        Sound.stopLoopEffect(R.raw.flap_loop_sound);
        Sound.stopMusic();
        Sound.playEffect(R.raw.knockout_bell,0);

        //MainGame.getFadeAnimationDrawable().start();
        MainGame.getFadeImageView().setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                CustomAnimationDrawableNew cad = new CustomAnimationDrawableNew(
                        MainGame.getFadeAnimationDrawable()) {
                    @Override
                    public void onAnimationFinish() {
                        Sound.stopLoopEffect(R.raw.player_plane_fire);
                        Sound.stopLoopEffect(R.raw.flap_loop_sound);

                        switchResultActivity();
                    }

                    @Override
                    public void onAnimationStart() {

                    }
                };

                if (isFadeStart == false){
                    v.setBackgroundDrawable(cad);
                    cad.start();
                    isFadeStart = true;
                }
            }

        });
        MainGame.getFadeImageView().callOnClick();
    }

    @Override
    public void update(float frameTime) {

    }

    private void switchResultActivity() {
        Intent intent = new Intent(GameView.context, ResultActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("boss",MainGame.get().isGameClear());
        intent.putExtra("monster",MainGame.get().getKillMonsterNumber());
        intent.putExtra("event",MainGame.get().getKillEventNumber());
        intent.putExtra("player",MainGame.get().getPlayerHp());

        GameView.context.startActivity(intent);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
