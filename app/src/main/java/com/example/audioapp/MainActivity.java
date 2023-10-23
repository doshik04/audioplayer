package com.example.audioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mPlayer;
    Button playButton, pauseButton, stopButton;
    SeekBar volumeControl;
    AudioManager audioManager;

    private
    Button  firstAudioButton, secondAudioButton, thirdAudioButton, fourAudioButton, fiveAudioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        stopButton = findViewById(R.id.stopButton);
        firstAudioButton = findViewById(R.id.firstAudio);
        secondAudioButton = findViewById(R.id.secondAudio);
        thirdAudioButton = findViewById(R.id.thirdAudio);
        fourAudioButton = findViewById(R.id.fourAudio);
        fiveAudioButton = findViewById(R.id.fiveAudio);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curValue = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        volumeControl = findViewById(R.id.volumeControl);
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(curValue);
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
    }

    private void stopPlay() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.stop();
        }
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        try {
            mPlayer.prepare();
            mPlayer.seekTo(0);
            playButton.setEnabled(true);
            // Разблокируем кнопки firstAudioButton и secondAudioButton
            firstAudioButton.setEnabled(true);
            secondAudioButton.setEnabled(true);
            thirdAudioButton.setEnabled(true);
            fourAudioButton.setEnabled(true);
            fiveAudioButton.setEnabled(true);

        } catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void play(View view) {
        if (mPlayer != null) {
            mPlayer.start();
            playButton.setEnabled(false);
            pauseButton.setEnabled(true);
            stopButton.setEnabled(true);
            // Блокируем кнопки firstAudioButton и secondAudioButton
            firstAudioButton.setEnabled(false);
            secondAudioButton.setEnabled(false);
            thirdAudioButton.setEnabled(false);
            fourAudioButton.setEnabled(false);
            fiveAudioButton.setEnabled(false);

        }
    }

    public void pause(View view) {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
            playButton.setEnabled(true);
            pauseButton.setEnabled(false);
            stopButton.setEnabled(true);
            // Разблокируем кнопки firstAudioButton и secondAudioButton
            firstAudioButton.setEnabled(true);
            secondAudioButton.setEnabled(true);
            thirdAudioButton.setEnabled(true);
            fourAudioButton.setEnabled(true);
            fiveAudioButton.setEnabled(true);
        }
    }

    public void stop(View view) {
        stopPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer != null && mPlayer.isPlaying()) {
            stopPlay();
        }
    }

    public void firstAudio(View view) {
        mPlayer = MediaPlayer.create(this, R.raw.eminem_lose);

    }

    public void secondAudio(View view) {
        mPlayer = MediaPlayer.create(this, R.raw.eminem_mockingbird);

    }

    public void thirdAudio(View view) {
        mPlayer = MediaPlayer.create(this, R.raw.thewekeend);

    }

    public void fourAudio(View view) {
        mPlayer = MediaPlayer.create(this, R.raw.sfgfd);

    }

    public void fiveAudio(View view) {
        mPlayer = MediaPlayer.create(this, R.raw.music);

    }
}