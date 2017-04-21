package com.example.androidqunyinhui.player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/4/20 0020.
 */
public class PlayerBroadcastReceiver extends BroadcastReceiver{


    public static final String PLAYSTATE_CHANGED = "com.android.music.playstatechanged";
    public static final String META_CHANGED = "com.android.music.metachanged";
    public static final String QUEUE_CHANGED = "com.android.music.queuechanged";
    public static final String PLAYBACK_COMPLETE = "com.android.music.playbackcomplete";
    public static final String ASYNC_OPEN_COMPLETE = "com.android.music.asyncopencomplete";
    public static final String SERVICECMD = "com.android.music.musicservicecommand";
    public static final String CMDNAME = "command";
    public static final String CMDTOGGLEPAUSE = "togglepause";
    public static final String CMDSTOP = "stop";
    public static final String CMDPAUSE = "pause";
    public static final String CMDPREVIOUS = "previous";
    public static final String CMDNEXT = "next";

    @Override
    public void onReceive(Context context, Intent intent) {

    }

//    private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            String cmd = intent.getStringExtra("command");
//            if (CMDNEXT.equals(cmd) || NEXT_ACTION.equals(action)) {
//                next(true);
//            } else if (CMDPREVIOUS.equals(cmd) || PREVIOUS_ACTION.equals(action)) {
//                prev();
//            } else if (CMDTOGGLEPAUSE.equals(cmd) || TOGGLEPAUSE_ACTION.equals(action)) {
//                if (isPlaying()) {
//                    pause();
//                } else {
//                    play();
//                }
//            } else if (CMDPAUSE.equals(cmd) || PAUSE_ACTION.equals(action)) {
//                pause();
//            } else if (CMDSTOP.equals(cmd)) {
//                pause();
//                seek(0);
//            } else if (MediaAppWidgetProvider.CMDAPPWIDGETUPDATE.equals(cmd)) {
//                // Someone asked us to refresh a set of specific widgets, probably
//                // because they were just added.
//                int[] appWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
//                mAppWidgetProvider.performUpdate(MediaPlaybackService.this, appWidgetIds);
//            }
//        }
//    };


}
