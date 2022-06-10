package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.resource;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.HashMap;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game.GameView;

public class Sound {
    protected static MediaPlayer mediaPlayer;
    protected static SoundPool soundPool;

    public static void playMusic(int resId) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        mediaPlayer = MediaPlayer.create(GameView.view.getContext(), resId);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public static void playMusic(Context context , int resId) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        mediaPlayer = MediaPlayer.create(context, resId);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public static void stopMusic() {
        if (mediaPlayer == null) return;
        mediaPlayer.stop();
        mediaPlayer = null;
    }
    public static void pauseMusic() {
        if (mediaPlayer == null) return;
        mediaPlayer.pause();
    }
    public static void resumeMusic() {
        if (mediaPlayer == null) return;
        mediaPlayer.start();
    }

    private static HashMap<Integer, Integer> soundIdMap = new HashMap<>();
    private static HashMap<Integer, MediaPlayer> mediaSoundMap = new HashMap<>();
    public static void playEffect(int resId , int setLoop) {
        SoundPool pool = getSoundPool();
        int soundId;
        if (soundIdMap.containsKey(resId)) {
            soundId = soundIdMap.get(resId);
        } else {
            soundId = pool.load(GameView.view.getContext(), resId, 1);
            soundIdMap.put(resId, soundId);
        }

        pool.play(soundId, 1f, 1f, 1, setLoop, 1f);
    }

    public static void playLoopEffect(int resId) {
        MediaPlayer media;
        if (mediaSoundMap.containsKey(resId)) {
            media = mediaSoundMap.get(resId);
            media.pause();
        } else {
            media = MediaPlayer.create(GameView.view.getContext(), resId);
            mediaSoundMap.put(resId, media);
        }

        media.setLooping(true);
        media.start();
    }

    public static void stopLoopEffect(int resId) {
        MediaPlayer media;
        if (mediaSoundMap.containsKey(resId)) {
            media = mediaSoundMap.get(resId);
            media.pause();
        }
    }

    private static SoundPool getSoundPool() {
        if (soundPool != null) return soundPool;

        AudioAttributes attrs = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attrs)
                .setMaxStreams(3)
                .build();
        return soundPool;
    }
}
