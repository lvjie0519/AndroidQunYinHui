package com.example.androidqunyinhui.test.lrc;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 解析歌词，得到LrcRow的集合
 */
public class LrcParseUtil{

    static final String TAG = "LrcParseUtil";

    /**
     *
     * @param rawLrc   lrc文件中的内容；
     * @return   返回解析内容
     */
    public static List<LrcRow> getLrcRows(String rawLrc) {
        Log.d(TAG,"getLrcRows by rawString");
        if(rawLrc == null || rawLrc.length() == 0){
            Log.e(TAG,"getLrcRows rawLrc null or empty");
            return null;
        }
        StringReader reader = new StringReader(rawLrc);
        BufferedReader br = new BufferedReader(reader);
        String line = null;
        List<LrcRow> rows = new ArrayList<LrcRow>();
        try{
            //循环地读取歌词的每一行
            do{
                line = br.readLine();
                /**
                 一行歌词只有一个时间的  例如：徐佳莹   《我好想你》
                 [01:15.33]我好想你 好想你

                 一行歌词有多个时间的  例如：草蜢 《失恋战线联盟》
                 [02:34.14][01:07.00]当你我不小心又想起她
                 [02:45.69][02:42.20][02:37.69][01:10.60]就在记忆里画一个叉
                 **/
                if(line != null && line.length() > 0){
                    //解析每一行歌词 得到每行歌词的集合，因为有些歌词重复有多个时间，就可以解析出多个歌词行来
                    List<LrcRow> lrcRows = LrcParseUtil.createRows(line);
                    if(lrcRows != null && lrcRows.size() > 0){
                        for(LrcRow row : lrcRows){
                            rows.add(row);
                        }
                    }
                }
            }while(line != null);

            if( rows.size() > 0 ){
                // 根据歌词行的时间排序
                Collections.sort(rows);
                if(rows!=null&&rows.size()>0){
                    for(LrcRow lrcRow:rows){
                        Log.d(TAG, "lrcRow:" + lrcRow.toString());
                    }
                }
            }
        }catch(Exception e){
            Log.e(TAG,"parse exceptioned:" + e.getMessage());
            return null;
        }finally{
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            reader.close();
        }
        return rows;
    }

    /**
     * 读取歌词的每一行内容，转换为LrcRow，加入到集合中
     */
    private static List<LrcRow> createRows(String standardLrcLine){
        /**
         一行歌词只有一个时间的  例如：徐佳莹   《我好想你》
         [01:15.33]我好想你 好想你

         一行歌词有多个时间的  例如：草蜢 《失恋战线联盟》
         [02:34.14][01:07.00]当你我不小心又想起她
         [02:45.69][02:42.20][02:37.69][01:10.60]就在记忆里画一个叉
         **/
        try{
            if(standardLrcLine.indexOf("[") != 0 || standardLrcLine.indexOf("]") != 9 ){
                return null;
            }
            //[02:34.14][01:07.00]当你我不小心又想起她
            //找到最后一个 ‘]’ 的位置
            int lastIndexOfRightBracket = standardLrcLine.lastIndexOf("]");
            //歌词内容就是 ‘]’ 的位置之后的文本   eg:   当你我不小心又想起她
            String content = standardLrcLine.substring(lastIndexOfRightBracket + 1, standardLrcLine.length());
            //歌词时间就是 ‘]’ 的位置之前的文本   eg:   [02:34.14][01:07.00]

            /**
             将时间格式转换一下  [mm:ss.SS][mm:ss.SS] 转换为  -mm:ss.SS--mm:ss.SS-
             即：[02:34.14][01:07.00]  转换为      -02:34.14--01:07.00-
             */
            String times = standardLrcLine.substring(0,lastIndexOfRightBracket + 1).replace("[", "-").replace("]", "-");
            //通过 ‘-’ 来拆分字符串
            String arrTimes[] = times.split("-");
            List<LrcRow> listTimes = new ArrayList<LrcRow>();
            for(String temp : arrTimes){
                if(temp.trim().length() == 0){
                    continue;
                }
                /** [02:34.14][01:07.00]当你我不小心又想起她
                 *
                 上面的歌词的就可以拆分为下面两句歌词了
                 [02:34.14]当你我不小心又想起她
                 [01:07.00]当你我不小心又想起她
                 */
                LrcRow lrcRow = new LrcRow(temp, timeConvert(temp), content);
                listTimes.add(lrcRow);
            }
            return listTimes;
        }catch(Exception e){
            Log.e(TAG,"createRows exception:" + e.getMessage());
            return null;
        }
    }

    /**
     * 将解析得到的表示时间的字符转化为Long型
     */
    private static long timeConvert(String timeString){
        //因为给如的字符串的时间格式为XX:XX.XX,返回的long要求是以毫秒为单位
        //将字符串 XX:XX.XX 转换为 XX:XX:XX
        timeString = timeString.replace('.', ':');
        //将字符串 XX:XX:XX 拆分
        String[] times = timeString.split(":");
        // mm:ss:SS
        return Integer.valueOf(times[0]) * 60 * 1000 +//分
                Integer.valueOf(times[1]) * 1000 +//秒
                Integer.valueOf(times[2]) ;//毫秒
    }


}
