package com.example.yvtc.yvtc081701;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyTask task = new MyTask();
        task.execute("5");
    }
}

class MyTask extends AsyncTask<String, Integer, String>
{

    @Override
    protected String doInBackground(String... params) {
        int i = Integer.valueOf(params[0]);

        for (int j = i; j>0;j--)
        {
            Log.d("MYTASK", "in doInBackground:" + String.valueOf(j));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Okay";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("MYTASK", "on postexecute:" + s);
    }
}