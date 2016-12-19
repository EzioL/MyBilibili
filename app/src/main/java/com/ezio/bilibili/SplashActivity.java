package com.ezio.bilibili;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
       // setContentView(R.layout.activity_splash);
        setUpSplash();
    }

    private void setUpSplash() {
        Observable.timer(2000, TimeUnit.MILLISECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                enterApp();
            }
        });
    }

    private void enterApp() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
