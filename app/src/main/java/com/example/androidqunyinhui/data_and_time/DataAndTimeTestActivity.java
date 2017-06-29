package com.example.androidqunyinhui.data_and_time;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.androidqunyinhui.R;
import com.example.androidqunyinhui.data_and_time.widget.NumericWheelAdapter;
import com.example.androidqunyinhui.data_and_time.widget.OnWheelScrollListener;
import com.example.androidqunyinhui.data_and_time.widget.WheelView;
import android.widget.PopupWindow.OnDismissListener;

import java.util.Calendar;

public class DataAndTimeTestActivity extends AppCompatActivity {

    private LayoutInflater inflater = null;
    private WheelView year;
    private WheelView month;
    private WheelView day;
    private WheelView hour;
    private WheelView mins;

    PopupWindow menuWindow;

    Button tv_time,tv_date;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, DataAndTimeTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_and_time_test);

        inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        tv_time=(Button) findViewById(R.id.tv_time);//时间选择器
        tv_date=(Button) findViewById(R.id.tv_date);//日期选择器
        tv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                showPopwindow(getTimePick());//弹出时间选择器
            }
        });
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DateTimeDialog dateTimeDialog = new DateTimeDialog(DataAndTimeTestActivity.this);
                dateTimeDialog.show();
            }
        });
    }

    /**
     * 初始化popupWindow
     * @param view
     */
    private void showPopwindow(View view) {
        menuWindow = new PopupWindow(view, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        menuWindow.setFocusable(true);
        menuWindow.setBackgroundDrawable(new BitmapDrawable());
        menuWindow.showAtLocation(view, Gravity.CENTER_HORIZONTAL, 0, 0);
        menuWindow.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                menuWindow=null;
            }
        });
    }

    /**
     *
     * @return
     */
    private View getTimePick() {
        View view = inflater.inflate(R.layout.timepick, null);
        hour = (WheelView) view.findViewById(R.id.hour);
        hour.setAdapter(new NumericWheelAdapter(0, 23));
        hour.setLabel("时");
        hour.setCyclic(true);
        mins = (WheelView) view.findViewById(R.id.mins);
        mins.setAdapter(new NumericWheelAdapter(0, 59));
        mins.setLabel("分");
        mins.setCyclic(true);

        hour.setCurrentItem(8);
        mins.setCurrentItem(30);

        Button bt = (Button) view.findViewById(R.id.set);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = hour.getCurrentItem() + ":"+ mins.getCurrentItem();
                Toast.makeText(DataAndTimeTestActivity.this, str, Toast.LENGTH_LONG).show();
                menuWindow.dismiss();
            }
        });
        Button cancel = (Button) view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuWindow.dismiss();
            }
        });

        return view;
    }


}
