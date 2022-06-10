package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game;

import android.content.Intent;
import android.graphics.Canvas;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.R;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.app.ResultActivity;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game.BaseGame;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game.GameView;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.resource.Metrics;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.resource.Sound;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.sprites.AnimSprite;
import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.game.monster.Boss;

public class FadeEffect extends AnimSprite {
    public FadeEffect() {
        super((float) Metrics.width / 2, (float) Metrics.height / 2,(float)Metrics.width,(float)Metrics.height,
                R.mipmap.fade_in,15.0f,17,false);

        Sound.stopLoopEffect(R.raw.player_plane_fire);
        Sound.stopLoopEffect(R.raw.flap_loop_sound);
        Sound.playEffect(R.raw.knockout_bell,0);
        Sound.playEffect(R.raw.announcer_knockout,0);
    }

    @Override
    public void update(float frameTime) {
        if ( 17 == getIndex() + 1)
        {
            Intent intent = new Intent(GameView.context, ResultActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            GameView.context.startActivity(intent);
        }
    }
}
