package com.example.androidqunyinhui.test.lrc;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 歌词行
 * 包括该行歌词的时间，歌词内容
 */
public class LrcRow implements Comparable<LrcRow>{
    public final static String TAG = "LrcRow";

    /** 该行歌词要开始播放的时间，格式如下：[02:34.14] */
    private String strTime;

    /** 该行歌词要开始播放的时间，由[02:34.14]格式转换为long型，
     * 即将2分34秒14毫秒都转为毫秒后 得到的long型值：time=02*60*1000+34*1000+14
     */
    private long time;

    /** 该行歌词的内容 */
    private String content;

    
    public LrcRow(){}
    
    public LrcRow(String strTime, long time, String content){
        this.strTime = strTime;
        this.time = time;
        this.content = content;
    }

    public String getStrTime() {
        return strTime;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "[" + strTime + " ]"  + content;
    }

    /**
     * 排序的时候，根据歌词的时间来排序
     */
    public int compareTo(LrcRow another) {
        return (int)(this.time - another.time);
    }
}