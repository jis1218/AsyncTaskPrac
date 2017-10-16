package com.example.asyctaskprac;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    /**
     * AsyncRTask = 세개의 기본함수를 지원하는 Thread
     * 1. onPreExecute : doInBackground()함수가 실행되기 전에 실행되는 함수
     * 2. doInBackground : 백그라운드(sub thread)에서 코드를 실행하는 함수
     * 3. onPostExecute : doInBackground() 함수가 실행된 후에 실행되는 함수
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView2);
        getServer("http://google.com");
    }

    private void getServer(String url) {
        AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>(){

            @Override
            protected String doInBackground(String... args) { //String...의 의미는 값을 여러개를 넣을 수 있다.
                String param1 = args[0];
                String result = Remote.getData(param1);
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                String title = result.substring(result.indexOf("<title>")+"<title>".length(), result.indexOf("</title>"));
                textView.setText(title);
            }
        };
        task.execute(url);
                // 1. doInBackground의 인자로 사용됨

    }
}
