package com.example.androidqunyinhui.test;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidqunyinhui.R;
import com.example.androidqunyinhui.test.lrc.LrcParseUtil;
import com.example.androidqunyinhui.test.lrc.LrcRow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class LrcPlayerActivity extends Activity {

    private List<LrcRow> mLrcRowEnglishList = new ArrayList<>();
    private List<LrcRow> mLrcRowChinaList = new ArrayList<>();

    private LrcView mLrcView;

    //更新歌词的频率，每秒更新一次
    private int mPalyTimerDuration = 1000;
    //更新歌词的定时器
    private Timer mTimer;
    //更新歌词的定时任务
    private TimerTask mTask;
    private MediaPlayer mPlayer;

    private Button btnTest;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, LrcPlayerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lrc_player);

        initLrcData();
        initView();

        //开始播放歌曲并同步展示歌词
        beginLrcPlay();
    }

    private void initLrcData(){
        String lrc = getContentFromAssets("demo.lrc");
        List<LrcRow> lrcRowList = LrcParseUtil.getLrcRows(lrc);
        setLrcRowEnglishAndChinas(lrcRowList);
    }

    private void initView(){

        this.mLrcView = (LrcView) findViewById(R.id.lrcview_show_english);
        this.mLrcView.setmTextSize(40);
        this.mLrcView.setmDivider(10);
        this.mLrcView.setmSelectTextColor(Color.BLUE);
        this.mLrcView.setmUnSelectTextColor(Color.BLACK);
        this.mLrcView.setOnClickListener(onClickListener);
        this.mLrcView.setmDatas(this.mLrcRowEnglishList);

        this.btnTest = (Button) findViewById(R.id.btn_test);
        this.btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLrcView.test(2);
            }
        });
    }

    private LrcRowView.OnClickListener onClickListener = new LrcRowView.OnClickListener(){

        @Override
        public void onClick(String text) {
            Toast.makeText(LrcPlayerActivity.this, text, Toast.LENGTH_SHORT).show();
        }

    };

    /**
     * 从assets目录下读取歌词文件内容
     * @param fileName
     * @return
     */
    public String getContentFromAssets(String fileName){
        try {
            InputStreamReader inputReader = new InputStreamReader( getResources().getAssets().open(fileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            String result="";
            while((line = bufReader.readLine()) != null){
                if(line.trim().equals(""))
                    continue;
                result += line + "\r\n";
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private void setLrcRowEnglishAndChinas(List<LrcRow> lrcRows){

        if(lrcRows == null || lrcRows.size() == 0){
            mLrcRowChinaList.clear();
            mLrcRowEnglishList.clear();
            return;
        }

        int len = lrcRows.size();
        LrcRow chinaLrcRow;
        LrcRow englishLrcRow;
        for(int i=0; i<len; i++){
            String content = lrcRows.get(i).getContent();
            String []tempStr = content.split("##");
            // 对于不符合规范的过滤掉；
            if(tempStr.length==2){
                englishLrcRow = new LrcRow(lrcRows.get(i).getStrTime(), lrcRows.get(i).getTime(), tempStr[0].trim());
                chinaLrcRow = new LrcRow(lrcRows.get(i).getStrTime(), lrcRows.get(i).getTime(), tempStr[1].trim());

                mLrcRowEnglishList.add(englishLrcRow);
                mLrcRowChinaList.add(chinaLrcRow);
            }
        }

    }

    /**
     * 开始播放歌曲并同步展示歌词
     */
    public void beginLrcPlay(){
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(getAssets().openFd("demo.mp3").getFileDescriptor());
            //准备播放歌曲监听
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                //准备完毕
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    if(mTimer == null){
                        mTimer = new Timer();
                        mTask = new LrcTask();
                        mTimer.scheduleAtFixedRate(mTask, 0, mPalyTimerDuration);
                    }
                }
            });
            //歌曲播放完毕监听
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    stopLrcPlay();
                }
            });
            //准备播放歌曲
//            mPlayer.prepare();    // 该方法比较耗时，最好在线程中运行；运行完会回调onPrepared
            mPlayer.prepareAsync();
            //开始播放歌曲
//            mPlayer.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 停止展示歌曲
     */
    public void stopLrcPlay(){
        if(mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
    }

    /**
     * 展示歌曲的定时任务
     */
    class LrcTask extends TimerTask{
        @Override
        public void run() {
            //获取歌曲播放的位置
            final long timePassed = mPlayer.getCurrentPosition();
            LrcPlayerActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    //滚动歌词
                    Log.i("lvjie", "timePassed="+timePassed);
                    mLrcView.selectLrcRowViewByTime(timePassed);
                }
            });

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            mPlayer.stop();
        }

        if(mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }

    }


}
