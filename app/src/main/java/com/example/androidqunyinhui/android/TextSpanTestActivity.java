package com.example.androidqunyinhui.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.example.androidqunyinhui.R;

public class TextSpanTestActivity extends Activity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, TextSpanTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_span_test);

        initView();
    }

    private void initView(){
        spanText1();
    }

    private void spanText1(){
        TextView tvInfo = (TextView) findViewById(R.id.tv_text_1);
        String text = "超越\n90%竞争者";

        SpannableStringBuilder ssb = new SpannableStringBuilder(text);
        // 设置文字相对大小，指相对于文本设定的大小的相对比例。
        ssb.setSpan(new RelativeSizeSpan(2.0f), 2, text.length()-4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ssb.setSpan(new RelativeSizeSpan(1.2f), text.length()-4, text.length()-3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        // 设置文字颜色。
        ssb.setSpan(new ForegroundColorSpan(Color.BLUE), 2, text.length()-3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tvInfo.setText(ssb);
    }


}
