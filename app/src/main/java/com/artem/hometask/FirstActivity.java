package com.artem.hometask;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.artem.hometask.base.BaseActivity;

public class FirstActivity extends BaseActivity {

    AsyncTask sleepedAsynTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        sleepedAsynTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            }


        };
        sleepedAsynTask.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sleepedAsynTask != null)
            sleepedAsynTask.cancel(false);
    }
}
