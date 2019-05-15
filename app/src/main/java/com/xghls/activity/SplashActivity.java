package com.xghls.activity;

import android.content.Intent;
import android.os.Bundle;

import com.xghls.R;
import com.xghls.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startActivity(new Intent(this,LoginActivity.class));
    }
}
