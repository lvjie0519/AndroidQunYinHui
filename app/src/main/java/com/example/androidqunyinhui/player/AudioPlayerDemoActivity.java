package com.example.androidqunyinhui.player;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidqunyinhui.R;

import java.io.IOException;

public class AudioPlayerDemoActivity extends AppCompatActivity {

    private TextView mTextViewState;                // 播放状态
    private Button mBtnPlayButton;                  // 播放
    private Button mBtnPauseButton;                 // 暂停
    private Button mBtnStopButton;                  // 停止

    private AudioManager mAudioManager;             // 播放器管理器
    private MediaPlayer mediaPlayer;               // 播放器
    private boolean isFirstStart = true;


    public static void startActivity(Context context){
        Intent intent = new Intent(context, AudioPlayerDemoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player_demo);

        initView();
        initPlayer();

    }

    private void initView(){
        mTextViewState = (TextView) findViewById(R.id.tv_show_info);
        mBtnPlayButton = (Button) findViewById(R.id.btn_player_start);
        mBtnPauseButton = (Button) findViewById(R.id.btn_player_pause);
        mBtnStopButton = (Button) findViewById(R.id.btn_player_stop);

        mBtnPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPreparePlayer();
            }
        });

        mBtnPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausePlayer();
            }
        });

        mBtnStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlayer();
            }
        });
    }

    private void initPlayer(){

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        if(mediaPlayer == null){
            mediaPlayer = new MediaPlayer();
        }

        try {
            mediaPlayer.setDataSource(getAssets().openFd("test.mp3").getFileDescriptor());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //准备播放歌曲监听
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            //准备完毕
            public void onPrepared(MediaPlayer mp) {
                startPlayer();
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlayer();
            }
        });
    }

    private void startPreparePlayer(){

        if(isFirstStart){
            mediaPlayer.prepareAsync();
            isFirstStart = false;
        }else{
            startPlayer();
        }
    }

    private void startPlayer(){

        int result = mAudioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        // 判断请求焦点是否成功
        if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
            Log.i("lvjie","焦点获取成功...");
            mediaPlayer.start();
        }else{
            Log.i("lvjie","焦点获取失败...");
        }
    }

    private void pausePlayer(){
        if(mediaPlayer != null){
            mediaPlayer.pause();
            Log.i("lvjie","mediaPlayer  pause...");
        }
    }

    private void stopPlayer(){
        if(mediaPlayer != null){
            mediaPlayer.stop();
            Log.i("lvjie","mediaPlayer  stop...");
        }
        isFirstStart = true;
    }

    // TODO   http://blog.csdn.net/chenchuntong/article/details/8813719
    // http://download.csdn.net/download/chenchuntong/5268025
    // http://blog.csdn.net/ab6637225c/article/details/51659135

    private AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {

        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                // 重新获取焦点
                case AudioManager.AUDIOFOCUS_GAIN:
                    Log.i("lvjie","afChangeListener  重新获取焦点...");

                    break;
                // 暂时失去焦点
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    Log.i("lvjie","afChangeListener  暂时失去焦点...");

                    break;
                //  可能长时间失去了这个音频的焦点
                case AudioManager.AUDIOFOCUS_LOSS:
                    Log.i("lvjie","afChangeListener  可能长时间失去了这个音频的焦点...");
                    //  暂停播放音乐，不再继续播放

                    break;
                // 你暂时的失去了音频的焦点,但是你允许继续用小音量播放音乐而不是完全杀掉音频.
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    Log.i("lvjie","afChangeListener  你暂时的失去了音频的焦点,但是你允许继续用小音量播放音乐而不是完全杀掉音频...");

                    break;
            }

        }

    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.release();
            Log.i("lvjie","mediaPlayer  release...");
        }

        if(mAudioManager != null){
            mAudioManager.abandonAudioFocus(afChangeListener);
        }
    }
}
