package kr.ac.tukorea.ge.sgp02.a2019182019.samplegame;

public class MainGame {
    public static MainGame getInstance() {
        if (singleton == null) {
            singleton = new MainGame();
        }
        return singleton;
    }

    private MainGame() {

    }

    private static MainGame singleton;
}
