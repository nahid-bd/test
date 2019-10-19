package com.esit.floristry.utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;

import com.esit.floristry.MainActivity;
import com.esit.floristry.R;
import com.esit.floristry.storageUtility.AppSharedPreference;

public class LogInAsyncTask extends AsyncTask<String, Void, String> {

    ProgressDialog progressDialog;
    Context context;
    String mobile;
    String password;

    public LogInAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        // progressDialog.setMessage("Please Wait");
        progressDialog.setMessage(context.getResources().getString(R.string.please_wait));
        progressDialog.show();
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... strings) {

        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        progressDialog.dismiss();
        Activity activity = (Activity) context;
        // write data to AppSharedPreference and open MainActivity
        AppSharedPreference.setIslogIn(context,true);

        Intent intent = new Intent(context, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
        super.onPostExecute(s);
    }
}
