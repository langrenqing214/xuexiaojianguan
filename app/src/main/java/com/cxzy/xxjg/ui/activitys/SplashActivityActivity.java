package com.cxzy.xxjg.ui.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.cxzy.xxjg.MainActivity;
import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.utils.SharedPreferencesUtils;

public class SplashActivityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String token = (String) SharedPreferencesUtils.getParam(MyApp.appComponent.getContext() , "app_token" , "");
        if (TextUtils.isEmpty(token)){
            startActivity(new Intent(this , LoginActivity.class));
        }else {
            startActivity(new Intent(this , MainActivity.class));
        }
        finish();
    }
}
