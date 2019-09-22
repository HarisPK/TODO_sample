package com.ziro.todo_sample.activities;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.ziro.todo_sample.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        doSplash();
    }

    private void doSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent toDoIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(toDoIntent);
                finish();
            }
        }, 1000);
    }
}
