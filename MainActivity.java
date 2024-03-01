package com.example.first_expereience;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.first_expereience.studentManageSystem.Sayings;
import com.example.first_expereience.studentManageSystem.User;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //viewpager2设置
    private ArrayList<Sayings>sayings=new ArrayList<>();
    private RegisterActivity.MyDatabaseHelper dbHelper;
    private RegisterActivity.DatabaseOperations databaseOperations = new RegisterActivity.DatabaseOperations(this);
    private ViewPager2 viewPager;
    //声明控件
    private Button BtnLogIn;
    private Button BtnRegister;
    private EditText EtUn;
    private EditText EtPw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //添加自己想要的台词
        ArrayList<String> sy = new ArrayList<>();
        String sy1 = "以声之色 ，塑花之形\n\n将你之名 ，刻于我心";sy.add(sy1);
        String sy2 = "      所有结束 \n\n都不过是另一种开始";sy.add(sy2);
        String sy3 = "忘川之上 ，桑梓之下\n\n一半是光 ，一半是影";sy.add(sy3);
        String sy4 = "山内有樱名为良 \n\n树本无名只待春";sy.add(sy4);
        String sy5 = "              时光流逝 \n\n愿有一天你能与你珍爱之人再次重逢";sy.add(sy5);
        String sy6 = "花无凋零之时 ，意无传达之日\n\n爱情亘古不变 ，紫罗兰永世长存";sy.add(sy6);
        String sy7 = "樱花满地集于我心 \n\n蝶舞纷飞祈愿相随";sy.add(sy7);
        String sy8 = "            我喜欢了你十年\n \n却用整个四月编织了我不爱你的谎言";sy.add(sy8);
        String sy9 = "机甲为婚纱 ，银河为殿堂 ，爆炸为礼炮\n\n        见证了只属于他们的婚礼";sy.add(sy9);
        String sy10 = "已知花意 ，未闻花名\n\n再见花时 ，泪落千溟";sy.add(sy10);
        //随机句子
        int n = 0;
        for (int i = 0; i < 100; i++) {
            Random rm = new Random();
            int m = rm.nextInt(10);
            if (m == n) {
                if (m == 0) {
                    m++;
                }
                if (m > 0 && m < 9) {
                    m++;
                }
                if (m == 9) {
                    m--;
                }
            }
            String rsy = sy.get(m);
            Sayings nsy = new Sayings();
            nsy.setSayings(rsy);
            sayings.add(nsy);
            n = m;
        }
        viewPager = findViewById(R.id.vp);
        viewPager.setAdapter(new VPAdapter(sayings));
        //找到控件
        BtnLogIn = findViewById(R.id.btnLogIn);
        BtnRegister = findViewById(R.id.btnRegister);
        EtUn = findViewById(R.id.editText);
        EtPw = findViewById(R.id.editText2);
        //实现跳转
//        BtnLogIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=null;
//                intent=new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(intent);
//            }
//        });

// 初始化 DatabaseOperations 对象
        databaseOperations = new RegisterActivity.DatabaseOperations(this);
        //设置点击按钮的事件
        BtnRegister.setOnClickListener(this::onClick1);
        BtnLogIn.setOnClickListener(this::onClick2);
    }
    private void onClick1(View v){
        Intent intent = null;
            intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
    }
    private void onClick2(View v){
        String username=EtUn.getText().toString();
        String password=EtPw.getText().toString();
        //从数据库中提取数据
        ArrayList<User> userList = databaseOperations.getAllUsers();
        //设置判断
        boolean result=false;
        for (User user : userList) {
            if (user.getUserName().equals(username) && user.getUserPassword().equals(password)) {
                result = true;
                break;
            }
        }
        Intent intent=null;
        String ok="登录成功！";
        String fail="账号或者密码错误，请重新输入！";
        if (result){
            intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),ok,Toast.LENGTH_SHORT).show();
        }else{
            Toast toastCenter=Toast.makeText(getApplicationContext(),fail,Toast.LENGTH_SHORT);
            toastCenter.setGravity(Gravity.CENTER,0,0);
            toastCenter.show();
        }
    }
}
