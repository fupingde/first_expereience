package com.example.first_expereience;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.first_expereience.studentManageSystem.Sayings;
import com.example.first_expereience.studentManageSystem.StudentInfo;

import java.util.ArrayList;
import java.util.Random;
import android.content.Context;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Sayings> sayings=new ArrayList<>();
    private ViewPager2 viewPager2;
    private Context context;
    private ArrayList<StudentInfo.Student> students = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        context = this;  // 初始化 context
        recyclerView=findViewById(R.id.recyclerView);
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
        viewPager2=findViewById(R.id.vp2);
        viewPager2.setAdapter(new VPAdapter(sayings));


        //数据填入
        // 获取学生信息
        AddActivity.DatabaseOperations databaseOperations = new AddActivity.DatabaseOperations(SearchActivity.this);
        ArrayList<StudentInfo.Student> students = databaseOperations.getAllStudents();

            // 导出学生信息
            for (StudentInfo.Student student : students) {
                exportStudentInfo(student);
            }
        }

        // 导出学生信息的方法
        private void exportStudentInfo(StudentInfo.Student student) {
            String studentName = student.getName();
            String studentGender = student.getGender();
            String studentAge = student.getGrade();
            String studentLocation = student.getLocation();
        // 将学生信息添加到RecyclerView中
        Adapter adapter = new Adapter(students);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        int studentCount = getStudentCount();
            StudentInfo.Student student1=new StudentInfo.Student();
            student1.setName(studentName);
            student1.setGender(studentGender);
            student1.setGrade(studentAge);
            student1.setLocation(studentLocation);
            students.add(student1);
        // 更新RecyclerView适配器
        Adapter newAdapter = new Adapter(students);
        recyclerView.setAdapter(newAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }// 获取学生数量
    public int getStudentCount() {
        AddActivity.MyDatabaseHelper dbHelper = new AddActivity.MyDatabaseHelper(context, "StudentInfo.db", null, 10);
        SQLiteDatabase db = dbHelper.getMyWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM StudentInfo", null);

        int count = 0;
        if (cursor != null) {
            cursor.moveToFirst();
            count = cursor.getInt(0);
            cursor.close();
        }

        return count;
    }
}