package com.example.androidqunyinhui.player;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidqunyinhui.R;

public class AudioPlayerDemoActivity extends AppCompatActivity {

    private TextView mTextViewState;                // 播放状态

    private Button mBtnPlayButton;                  // 播放

    private Button mBtnPauseButton;                 // 暂停

    private Button mBtnStopButton;                  // 停止

//    private AudioPlayer mAudioPlayer;               // 播放器

    private Handler mHandler;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, AudioPlayerDemoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player_demo);
    }
}
