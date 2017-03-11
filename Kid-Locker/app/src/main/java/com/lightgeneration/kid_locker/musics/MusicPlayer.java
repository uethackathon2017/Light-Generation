package com.lightgeneration.kid_locker.musics;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;
import java.util.Random;

/**
 * Created by PhamVanLong on 3/11/2017.
 */

public class MusicPlayer {
    public static final int IDLE = -1;
    public static final int PLAY = 1;
    public static final int LOOP = 2;
    public static final int PAUSE = 3;
    public static final int STOP = 4;

    private MediaPlayer mediaPlayer;
    private int state;

    public MusicPlayer(String linkUrl) {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(linkUrl);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            state = IDLE;
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MusicPlayer(int resId, Context context) {
        // try {
        mediaPlayer = MediaPlayer.create(context, resId);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //mediaPlayer.prepare();
        // } catch (IOException e) {
        //    e.printStackTrace();
        //  }
        state = IDLE;
    }

    public MusicPlayer(int resId[], Context context) {
        //try {
        Random r = new Random();
        mediaPlayer = MediaPlayer.create(context, resId[r.nextInt(resId.length)]);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //mediaPlayer.prepare();
        //} catch (IOException e) {
        //  e.printStackTrace();
        // }
        state = IDLE;
    }

    public void play() {
        if (state == IDLE) {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stop();
                }
            });
            mediaPlayer.start();
            state = PLAY;
        }
    }

    public void loop() {
        if (state == IDLE || state == PAUSE) {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
            state = LOOP;
        }
    }

    public void stop() {
        if (state == PLAY || state == LOOP || state == PAUSE) {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                state = STOP;
            }
        }
    }

    public int getState() {
        return state;
    }
}
