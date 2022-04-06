package kr.ac.tukorea.ge.sgp02.a2019182019.samplegame;

import android.content.res.Resources;

public class Matrics {
    public static int width;
    public static int height;

    public static float size(int dimenResId){
        Resources res = GameView.view.getResources();
        return res.getDimension(dimenResId);
    }
}
