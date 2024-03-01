package com.example.first_expereience;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.first_expereience.studentManageSystem.Sayings;
import com.example.first_expereience.studentManageSystem.StudentInfo;

import java.util.ArrayList;
import java.util.Random;

public class DeleteActivity extends AppCompatActivity {
    private ArrayList<Sayings> sayings=new ArrayList<>();
    private ViewPager2 vp3;
    private TextView tvstudentGender2;
    private TextView tvstudentName2;
    private Button btDeleteData;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        // 初始化 context
        context = this;
        //找到控件
        tvstudentGender2=findViewById(R.id.tvstudentGender2);
        tvstudentName2=findViewById(R.id.tvstudentName2);
        btDeleteData=findViewById(R.id.btDeleteData);
        //设置按键
        btDeleteData.setOnClickListener(this::onClick);
        //添加自己想要的台词
        ArrayList<String> sy = new ArrayList<>();
        String sy1 = "以声之色 ，塑花之形\n\n将你之名 ，刻于我心";
        String sy2 = "      所有结束 \n\n都不过是另一种开始";
        String sy3 = "忘川之上 ，桑梓之下\n\n一半是光 ，一半是影";
        String sy4 = "山内有樱名为良 \n\n树本无名只待春";
        String sy5 = "              时光流逝 \n\n愿有一天你能与你珍爱之人再次重逢";
        String sy6 = "花无凋零之时 ，意无传达之日\n\n爱情亘古不变 ，紫罗兰永世长存";
        String sy7 = "樱花满地集于我心 \n\n蝶舞纷飞祈愿相随";
        String sy8 = "            我喜欢了你十年\n \n却用整个四月编织了我不爱你的谎言";
        String sy9 = "机甲为婚纱 ，银河为殿堂 ，爆炸为礼炮\n\n        见证了只属于他们的婚礼";
        String sy10 = "已知花意 ，未闻花名\n\n再见花时 ，泪落千溟";
        sy.add(sy1);
        sy.add(sy2);
        sy.add(sy3);
        sy.add(sy4);
        sy.add(sy5);
        sy.add(sy6);
        sy.add(sy7);
        sy.add(sy8);
        sy.add(sy9);
        sy.add(sy10);
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
        vp3 = findViewById(R.id.vp3);
        vp3.setAdapter(new VPAdapter(sayings));
    }

    //按键事件
    private void onClick(View v){
        // 获取输入框中的值
        String studentName = tvstudentName2.getText().toString();
        String studentGender = tvstudentGender2.getText().toString();

        // 获取数据库实例
        AddActivity.DatabaseOperations databaseOperations = new AddActivity.DatabaseOperations(context);
        SQLiteDatabase db = databaseOperations.getWritableDatabase();

// 构造删除条件
        String selection = "studentName = ? AND studentGender = ?";
        String[] selectionArgs = { studentName,studentGender };

// 执行删除操作
        int deletedRows = db.delete(
                StudentInfo.Student.TABLE_NAME,
                selection,
                selectionArgs
        );
// 处理删除结果
        if (deletedRows > 0) {
            // 删除成功
            finish();
            Toast.makeText(this, "删除成功!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "删除失败!", Toast.LENGTH_SHORT).show();
        }

// 关闭数据库连接
        db.close();
    }


}