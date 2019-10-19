package com.esit.floristry.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.esit.floristry.MainActivity;
import com.esit.floristry.R;
import com.esit.floristry.storageUtility.AppSharedPreference;

public class LogInActivity extends AppCompatActivity {

    TextView tvForgotPassword;
    TextView btnSignIn;
    EditText etPassword;
    EditText etMobile;
    Context context;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        etMobile = findViewById(R.id.etMobile);
        etPassword = findViewById(R.id.etPassword);


        progressDialog = new ProgressDialog(context);
        // progressDialog.setMessage("Please Wait");
        progressDialog.setMessage(context.getResources().getString(R.string.please_wait));


        // request for storage permission
        ActivityCompat.requestPermissions(LogInActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check mobile  and password
                String mobile = etMobile.getText().toString();
                String password = etPassword.getText().toString();
                if (mobile.equalsIgnoreCase("12345") && password.equalsIgnoreCase("12345")) {

                    // retailer
                    AppSharedPreference.setUserType(context, "seller");
                    AppSharedPreference.setUserName(context,"NAHIDUL ISLAM");
                    progressDialog.show();
                    Handler handler = new Handler();
                    handler.postDelayed(delayRun, 3500);

                } else if (mobile.equalsIgnoreCase("54321") && password.equalsIgnoreCase("54321")) {

                    // seller
                    AppSharedPreference.setUserType(context, "retailer");
                    AppSharedPreference.setUserName(context,"CHAYAN DEBNATH");
                    progressDialog.show();
                    Handler handler = new Handler();
                    handler.postDelayed(delayRun, 3500);

                } else {
                    // message for wrong mobile andOr PIN
                    Toast.makeText(context, "Enter Valid Mobile and PIN ", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private final Runnable delayRun = new Runnable() {

        @Override
        public void run() {
            AppSharedPreference.setIslogIn(context, true);
            progressDialog.dismiss();
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            finish();

        }
    };
}
