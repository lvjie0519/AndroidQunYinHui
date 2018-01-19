package com.example.androidqunyinhui.webview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidqunyinhui.R;

public class MyWebViewTest extends AppCompatActivity {

    private WebView webView;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, MyWebViewTest.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_web_view_test);

        initView();
        initWebSeeting();
        bindListener();
    }

    private void initView(){
        webView = (WebView) findViewById(R.id.wb_webview);
    }

    private void initWebSeeting(){

        WebSettings webSettings = webView.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        //在js中调用本地java方法
        webView.addJavascriptInterface(new JsInterface(this), "AndroidWebView");

        // 先载入JS代码
        // 格式规定为:file:///android_asset/文件名.html
        webView.loadUrl("file:///android_asset/myweb.html");

    }

    private void bindListener(){
        findViewById(R.id.btn_webview_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 必须另开线程进行JS方法调用(否则无法调用)
                webView.post(new Runnable() {
                    @Override
                    public void run() {

                        // 注意调用的JS方法名要对应上
                        // 调用javascript的callJS()方法
                        webView.loadUrl("javascript:callJS()");
                    }
                });
            }
        });

        findViewById(R.id.btn_send_info_to_js).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = ((EditText) findViewById(R.id.et_test)).getText().toString();
                //调用js中的函数：showInfoFromJava(msg)
                webView.loadUrl("javascript:showInfoFromJava('" + msg + "')");
            }
        });

        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(MyWebViewTest.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

        });
    }

    private class JsInterface {
        private Context mContext;

        public JsInterface(Context context) {
            this.mContext = context;
        }

        //在js中调用window.AndroidWebView.showInfoFromJs(name)，便会触发此方法。
        @JavascriptInterface
        public void showInfoFromJs(String name) {
            Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
        }
    }

}
