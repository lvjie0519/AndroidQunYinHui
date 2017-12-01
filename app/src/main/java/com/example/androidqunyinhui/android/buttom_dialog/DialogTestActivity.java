package com.example.androidqunyinhui.android.buttom_dialog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.example.androidqunyinhui.R;

public class DialogTestActivity extends AppCompatActivity {

    private MyDialog myDialog;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, DialogTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_test);

        findViewById(R.id.btn_dialog_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void showDialog(){
        if(myDialog == null){
            WindowManager windowManager = getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            myDialog = new MyDialog(this, display.getWidth());
        }
        myDialog.show();
    }

}
