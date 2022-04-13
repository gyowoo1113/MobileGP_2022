package kr.ac.tukorea.ge.sgp02.a2019182019.dragonflight.framework;

import android.content.res.Resources;
import android.util.TypedValue;

import kr.ac.tukorea.ge.sgp02.a2019182019.dragonflight.framework.GameView;

public class Metrics {
    public static int width;
    public static int height;

    public static float size(int dimenResId) {
        Resources res = GameView.view.getResources();
        return res.getDimension(dimenResId);
    }

    public static float floatValue(int dimenResId) {
        Resources res = GameView.view.getResources();

        TypedValue outValue = new TypedValue();
        res.getValue(dimenResId, outValue, true);
        float value = outValue.getFloat();
        return value;
//        return res.getFloat(dimenResId);
    }
}
