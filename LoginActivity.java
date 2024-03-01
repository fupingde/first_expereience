package com.example.first_expereience;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.first_expereience.studentManageSystem.Sayings;

import java.util.ArrayList;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {
    private ArrayList<Sayings> sayings=new ArrayList<>();
    private ViewPager2 viewPager;
    private Button BtnAdd;
    private Button BtnDelete;
    private Button BtnChange;
    private Button BtnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
            //添加自己想要的台词
        ArrayList<String> sy = new ArrayList<>();
        String sy1="以声之色 ，塑花之形\n\n将你之名 ，刻于我心";
        String sy2="      所有结束 \n\n都不过是另一种开始";
        String sy3="忘川之上 ，桑梓之下\n\n一半是光 ，一半是影";
        String sy4="山内有樱名为良 \n\n树本无名只待春";
        String sy5="              时光流逝 \n\n愿有一天你能与你珍爱之人再次重逢";
        String sy6="花无凋零之时 ，意无传达之日\n\n爱情亘古不变 ，紫罗兰永世长存";
        String sy7="樱花满地集于我心 \n\n蝶舞纷飞祈愿相随";
        String sy8="            我喜欢了你十年\n \n却用整个四月编织了我不爱你的谎言";
        String sy9="机甲为婚纱 ，银河为殿堂 ，爆炸为礼炮\n\n        见证了只属于他们的婚礼";
        String sy10="已知花意 ，未闻花名\n\n再见花时 ，泪落千溟";
            sy.add(sy1);sy.add(sy2);sy.add(sy3);sy.add(sy4);sy.add(sy5);sy.add(sy6);sy.add(sy7);sy.add(sy8);sy.add(sy9);sy.add(sy10);
        int n=0;
        for (int i = 0; i < 100; i++) {
            Random rm=new Random();
            int m= rm.nextInt(10);
            if (m==n){
                if (m==0){
                    m++;
                }
                if (m>0&&m<9){
                    m++;
                }
                if (m==9){
                    m--;
                }
            }
            String rsy=sy.get(m);
            Sayings nsy = new Sayings();
            nsy.setSayings(rsy);sayings.add(nsy);
            n=m;
        }
            viewPager=findViewById(R.id.vp);
        viewPager.setAdapter(new VPAdapter(sayings));
        //找到控件
        BtnAdd=findViewById(R.id.BtnAdd);BtnAdd.setOnClickListener(this::onClick1);
        BtnDelete=findViewById(R.id.BtnDelete);BtnDelete.setOnClickListener(this::onClick2);
        BtnChange=findViewById(R.id.BtnChange);BtnChange.setOnClickListener(this::onClick3);
        BtnSearch=findViewById(R.id.BtnSearch);BtnSearch.setOnClickListener(this::onClick4);
    }
    private void onClick1(View v){
        Intent intent = null;
        intent = new Intent(LoginActivity.this, AddActivity.class);
        startActivity(intent);
    }
    private void onClick2(View v){
        Intent intent = null;
        intent = new Intent(LoginActivity.this, DeleteActivity.class);
        startActivity(intent);
    }
    private void onClick3(View v){
        Intent intent = null;
        intent = new Intent(LoginActivity.this, ChangeActivity.class);
        startActivity(intent);
    }
    private void onClick4(View v){
        Intent intent = null;
        intent = new Intent(LoginActivity.this, SearchActivity.class);
        startActivity(intent);
    }
}