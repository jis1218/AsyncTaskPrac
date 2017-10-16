package com.example.asyctaskprac;

import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 정인섭 on 2017-10-16.
 */

public class Remote {
    public static String getData(String string){
        StringBuilder result;
        Handler handler;

        result = new StringBuilder();
        try {

            URL url = new URL(string);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // 통신이 성공인지 체크
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // 여기서부터는 파일에서 데이터를 가져오는 것과 동일
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String temp = "";

                while ((temp = br.readLine()) != null) {
                    result.append(temp).append("/n");
                }
                br.close();
                isr.close();
            } else {
                Log.e("ServerError", connection.getResponseCode()+"");
            }
            connection.disconnect();
        }catch(Exception e){

        }

        return result.toString();
    }
}
