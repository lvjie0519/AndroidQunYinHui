package com.example.androidqunyinhui.test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.androidqunyinhui.R;
import com.example.androidqunyinhui.test.lrc.LrcParseUtil;
import com.example.androidqunyinhui.test.lrc.LrcRow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LrcPlayerActivity extends AppCompatActivity {

    private List<LrcRow> mLrcRowEnglishList = new ArrayList<>();
    private List<LrcRow> mLrcRowChinaList = new ArrayList<>();

    public static void startActivity(Context context){
        Intent intent = new Intent(context, LrcPlayerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lrc_player);

        String lrc = getContentFromAssets("demo01.lrc");
        List<LrcRow> lrcRowList = LrcParseUtil.getLrcRows(lrc);
        setLrcRowEnglishAndChinas(lrcRowList);
    }



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


}
