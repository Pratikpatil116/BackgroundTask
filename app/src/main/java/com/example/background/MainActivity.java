package com.example.background;


import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class BG extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("backgroundBG", "onPreExecute: ran");
        }

        @Override
        protected String doInBackground(String... urls) {
            Log.d("backgroundBG", "doInBackground: ran");
            String result = "";
            URL url;
            HttpURLConnection conn;
            try {
                url = new URL(urls[0]);
                conn = (HttpURLConnection) url.openConnection();
                InputStream in = conn.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
            }
            catch(Exception e){
                e.printStackTrace();
                return "Something went wrong";
            }
            return result;


        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("backgroundBG", "onPostExecute: ran");
            Log.d("backgroundBG", s);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BG myTask = new BG();
        myTask.execute("https://www.w3school.com/");



    }
    public void check(View view){
        Toast.makeText(this, "Successfully Done", Toast.LENGTH_SHORT).show();
    }
}

