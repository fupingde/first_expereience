package com.example.first_expereience;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class Post extends AppCompatActivity {
    private TextView posttv;
    private Button postbt;

    private final String mPostUrl = "https://www.wanandroid.com/user/register";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        postView();
        postClick();
    }
    private void postView(){
        posttv=findViewById(R.id.posttv);
        postbt=findViewById(R.id.postbt);
    }
    private void postClick(){
        postbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //网络请求
//                sendGetRequest(mPostUrl);
                HashMap<String,String> params = new HashMap<>();
                params.put("username","胡苏桓");
                params.put("password","123456");
                params.put("repassword","123456");
                sendPostRequest(mPostUrl,params);
            }
        });
    }

    private  String StreamToString(InputStream in){
        StringBuilder sb = new StringBuilder();
        String oneLine;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            while ((oneLine = reader.readLine()) != null) {//readLine⽅法将读取⼀⾏
                sb.append(oneLine).append('\n');//拼接字符串并且增加换⾏，提⾼可读性
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { in.close();//关闭InputStream
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }private void sendPostRequest(String theUrl, HashMap<String, String> params){
        new Thread(
                ()->{
                    try {
                        URL url=new URL(theUrl);
                        HttpURLConnection connection=(HttpURLConnection)
                                url.openConnection();
                        connection.setRequestMethod("POST");
                        connection.setConnectTimeout(8000);
                        connection.setReadTimeout(8000);//设置最⼤的读取时间
                        connection.setDoOutput(true);
                        connection.setDoInput(true);
                        StringBuilder dataToWrite = new StringBuilder();
                        for (String key : params.keySet()) {
                            dataToWrite.append(key).append("=").append(params.get(key)).append("&");
                        }
                        //正式连接
                        connection.connect();
                        OutputStream outputStream = connection.getOutputStream();
                        outputStream.write(dataToWrite.substring(0,
                                dataToWrite.length() - 1).getBytes());
                        InputStream in=connection.getInputStream();
                        String response=StreamToString(in);
                        Log.d("LogText","sendGetRequest:"+response);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
        ).start();
    }
}