package com.xcript.workapp.login;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.xcript.workapp.R;

public class LaunchActivity extends AppCompatActivity {

    public static final int LAUNCH_ACTIVITY_DELAY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LaunchActivity.this, LoginSignupActivity.class);
                startActivity(intent);
                finish();
            }
        }, LAUNCH_ACTIVITY_DELAY);

        setContentView(R.layout.activity_launch);
    }
}
