package com.example.first_expereience;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Get extends AppCompatActivity {


    private TextView gettv;
    private Button getbt;
    private Button postbt;
    private Handler mHandler;
    private final String mPostUrl = "https://www.wanandroid.com/user/register";
    private final String mGetUrl = "https://www.wanandroid.com/banner/json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);
        mHandler = new MyHandler();
//        initView();
        getView();
        getClick();
//        postView();
//        postClick();
    }
    private void getView(){
        gettv=findViewById(R.id.gettv);
        getbt=findViewById(R.id.getbt);
    }
    private void getClick(){
        getbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //网络请求
                sendGetRequest(mGetUrl);
            }
        });
    }
    private void postView(){
        postbt=findViewById(R.id.postbt);
    }
    private void postClick(){
        postbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //网络请求
//                sendGetRequest(mPostUrl);
                HashMap<String,String> params = new HashMap<>();
                params.put("username","任宣宇");
                params.put("password","123456");
                params.put("repassword","123456");
                sendPostRequest(mPostUrl,params);
            }
        });
    }

    private void sendGetRequest(String mGetUrl){
        new Thread(
                ()->{
                    try {
                        URL url=new URL(mGetUrl);
                        HttpURLConnection connection=(HttpURLConnection)
                                url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setConnectTimeout(8000);
                        connection.setReadTimeout(8000);//设置最⼤的读取时间，单位为
                         connection.setRequestProperty("Accept-Language",
                                 "zh-CN,zh;q=0.9");
                         connection.setRequestProperty("Accept-Encoding",
                                 "gzip,deflate");
                         //正式连接
                         connection.connect();
                        InputStream in=connection.getInputStream();
                        String response=StreamToString(in);
                        Message message = new Message();
                        message.obj = response;
                        mHandler.sendMessage(message);
                        Log.d("LogText","sendGetRequest:"+response);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
        ).start();
    }
    private void sendPostRequest(String theUrl, HashMap<String, String> params){
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
    private class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String responseData = msg.obj.toString();
            setData(decodeJson(responseData));
        }
    }
    private void setData(BannerData bannerData) {
        gettv.setText(bannerData.data.get(0).title);
    }
    private void initView() {
        gettv = findViewById(R.id.gettv);
    }
    private BannerData decodeJson(String data) {
        // 数据存储对象
        BannerData bannerData = new BannerData();
        bannerData.data = new ArrayList<>();
        try {
            // 获得这个JSON对象{}
            JSONObject jsonObject = new JSONObject(data);
            // 获取并列的三个，errorCode，errorMsg，data
            bannerData.errorCode = jsonObject.getInt("errorCode");
            bannerData.errorMsg = jsonObject.getString("errorMsg");
            // data是⼀个对象集合
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            Log.d("LogTest", "解析后数据大小: " + jsonArray.length()); // 确保解析出的数据大小不为0
            BannerData.DetailData detailData;
            for (int i = 0; i < jsonArray.length(); i++) {
                // 遍历数组，给集合添加对象
                detailData = new BannerData.DetailData();
                JSONObject singleData = jsonArray.getJSONObject(i);
                detailData.desc = singleData.getString("desc");
                detailData.id = singleData.getInt("id");
                detailData.imagePath = singleData.getString("imagePath");
                detailData.isVisible = singleData.getInt("isVisible");
                detailData.order = singleData.getInt("order");
                detailData.title = singleData.getString("title");
                detailData.type = singleData.getInt("type");
                detailData.url = singleData.getString("url");
                bannerData.data.add(detailData);
                Log.d("LogTest", "解析后的标题: " + detailData.title); // 输出解析出的标题，确保标题不为null
            }
        } catch (Exception e) {
            Log.w("lx", "(JsonActivity.java:59)-->>", e);
        }
        return bannerData;
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
    }
}

