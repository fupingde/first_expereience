package com.example.first_expereience;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonActivity extends AppCompatActivity {
        TextView jsontv;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_json);
            initView();
            String jsonData = "{\n" +
                    " \"data\": [\n" +
                    " {\n" +
                    " \"desc\": \"我们⽀持订阅啦\",\n" +
                    " \"id\": 30,\n" +
                    " \"imagePath\": \"https://www.wanandroid.com/blogimgs\"," +
                    " \"isVisible\": 1,\n" +
                    " \"order\": 2,\n" +
                    " \"title\": \"我们⽀持订阅啦\",\n" +
                    " \"type\": 0,\n" +
                    " \"url\": \"https://www.wanandroid.com/blog/show/3352\"\n " +
                    "  },\n" +
                "  {\n" +
                "  \"desc\": \"\",\n" +
                " \"id\": 6,\n" +
                " \"imagePath\": \"https://www.wanandroid.com/blogimgs\", " +
                "  \"isVisible\": 1,\n" +
                " \"order\": 1,\n" +
                " \"title\": \"我们新增了⼀个常⽤导航Tab~\",\n" +
                " \"type\": 1,\n" +
                " \"url\": \"https://www.wanandroid.com/navi\"\n" +
                " },\n" +
                " {\n" +
                " \"desc\": \"⼀起来做个App吧\",\n" +
                " \"id\": 10,\n" +
                " \"imagePath\": \"https://www.wanandroid.com/blogimg\"," +
                "  \"isVisible\": 1,\n" +
                " \"order\": 1,\n" +
                " \"title\": \"⼀起来做个App吧\",\n" +
                " \"type\": 1,\n" +
                " \"url\": \"https://www.wanandroid.com/blog/show/2\"\n " +
                "  }\n" +
                "  ],\n" +
                "  \"errorCode\": 0,\n" +
                " \"errorMsg\": \"\"\n" +
             "}";
            BannerData bannerData = decodeJson(jsonData);
                setData(bannerData);
        }
        private void setData(BannerData bannerData) {for (BannerData.DetailData detailData : bannerData.data) {
            Log.d("LogTest", "标题: " + detailData.title);
        }
            Log.d("LogTest", "第一个元素标题: " + (bannerData.data.size() > 0 ? bannerData.data.get(0).title : "没有元素"));

            jsontv.setText(bannerData.data.get(0).title);
        }
        private void initView() {
            jsontv = findViewById(R.id.jsontv);
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
}