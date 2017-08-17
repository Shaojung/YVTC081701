package com.example.yvtc.yvtc081701;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv, tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        MyTask task = new MyTask();
        task.execute("5");
    }
    class MyTask extends AsyncTask<String, Integer, String>
    {

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tv.setText(String.valueOf(values[0]));
        }

        @Override
        protected String doInBackground(String... params) {
            int i = Integer.valueOf(params[0]);

            for (int j = i; j>0;j--)
            {
                publishProgress(j);
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
            tv2.setText(s);
        }
    }
}

