package com.example.yvtc.yvtc081701;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView tv, tv2;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        img = (ImageView) findViewById(R.id.imageView);
        MyTask task = new MyTask();
        task.execute("http://images.meredith.com/content/dam/bhg/Images/2006/03/SIP943899.jpg.rendition.largest.jpg");
    }
    class MyTask extends AsyncTask<String, Integer, Bitmap>
    {

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tv.setText(String.valueOf(values[0]));
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String str;
            int length;
            int fullsize;
            byte[] result = null;
            int total = 0;

            String str_url = params[0];
            try {
                URL url = new URL(str_url);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                fullsize = conn.getContentLength();
                InputStream stream = conn.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] data = new byte[64];

                while ((length = stream.read(data)) != -1)
                {
                    outputStream.write(data, 0, length);
                    total += length;
                    int p = 100 * total / fullsize;
                    publishProgress(p);
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            tv2.setText(total + "/" + fullsize);
//                        }
//                    });

                };
                result = outputStream.toByteArray();



                stream.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0, total);

            return bitmap;

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            img.setImageBitmap(bitmap);

        }
    }
}

