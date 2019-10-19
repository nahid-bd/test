package com.esit.floristry.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.Window;
import android.view.WindowManager;

import com.esit.floristry.MainActivity;
import com.esit.floristry.R;
import com.esit.floristry.storageUtility.AppSharedPreference;

public class SplashActivity extends Activity {
    private Context context;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        context = this;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        handler.postDelayed(startRun, 1500);

    }



    private final Runnable startRun = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub

            if (AppSharedPreference.getIslogIn(context)) {


                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();


            } else {
                Intent logInIntent = new Intent(SplashActivity.this, LogInActivity.class);
                startActivity(logInIntent);
                SplashActivity.this.finish();
            }
        }
    };
}
