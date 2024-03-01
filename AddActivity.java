package com.example.first_expereience;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.first_expereience.studentManageSystem.Sayings;
import com.example.first_expereience.studentManageSystem.StudentInfo;
import com.example.first_expereience.studentManageSystem.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.jar.Attributes;

public class AddActivity extends AppCompatActivity {
    private ArrayList<Sayings> sayings=new ArrayList<>();
    private ViewPager2 vpAdd;
    private EditText tvstudentName;
    private EditText tvstudentGender;
    private EditText tvstudentAge;
    private EditText tvstudentLocation;
    private Button btAddData;
    private static MyDatabaseHelper dbHelper;
    private RegisterActivity.DatabaseOperations databaseOperations = new RegisterActivity.DatabaseOperations(this);


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dbHelper = new MyDatabaseHelper(this, "StudentInfo.db", null, 10);
        // 初始化 DatabaseOperations 对象
        databaseOperations = new RegisterActivity.DatabaseOperations(this);
        //找到控件
        tvstudentName=findViewById(R.id.tvstudentName);tvstudentAge=findViewById(R.id.tvstudentAge);tvstudentGender=findViewById(R.id.tvstudentGender);tvstudentLocation=findViewById(R.id.tvstudentLocation);btAddData=findViewById(R.id.btAddData);
        //设置按键
        btAddData.setOnClickListener(this::onClick);
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
        vpAdd=findViewById(R.id.vpAdd);
        vpAdd.setAdapter(new VPAdapter(sayings));
    }

    //按键效果
    private void onClick(View v){
        String studentName=tvstudentName.getText().toString();
        String studentGender=tvstudentGender.getText().toString();
        String studentAge=tvstudentAge.getText().toString();
        String studentLocation=tvstudentLocation.getText().toString();
        // 使用 getWritableDatabase() 获取可写数据库
        SQLiteDatabase db = dbHelper.getMyWritableDatabase();
        ContentValues values=new ContentValues();
        // 插入数据
        long result = insertStudent(studentName, studentGender, studentAge, studentLocation);
        // 关闭数据库连接
        db.close();
        // 在需要查询时重新获取可写数据库
        db = dbHelper.getMyWritableDatabase();
        Cursor cursor=db.query("StudentInfo",null,null,null,null,null,null);
        cursor.close();
        finish();
        Toast.makeText(this, "添加成功!", Toast.LENGTH_SHORT).show();
    }

    // 插入学生数据
    public long insertStudent(String studentName, String studentGender, String studentAge, String studentLocation) {
        SQLiteDatabase db = dbHelper.getMyWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("studentName", studentName);
        values.put("studentGender", studentGender);
        values.put("studentAge", studentAge);
        values.put("studentLocation", studentLocation);

        // 插入数据
        long result = db.insert("StudentInfo", null, values);

        // 关闭数据库连接
        db.close();

        return result;
    }
    //创建数据库
    public static class MyDatabaseHelper extends SQLiteOpenHelper {

        public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            mContext = context;
        }
        // 获取可写的数据库
        public SQLiteDatabase getMyWritableDatabase() {

            SQLiteDatabase db = this.getWritableDatabase();
            if (db == null) {
                Log.e("MyDatabaseHelper", "Failed to get a writable database.");
            }return db;
        }
        // 获取可读的数据库
        public SQLiteDatabase getMyReadableDatabase() {
            return this.getReadableDatabase();
        }
        public static final String CREAT_STUDENT="CREATE TABLE StudentInfo("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"studentName TEXT,"
                +"studentGender TEXT,"
                +"studentAge TEXT,"
                +"studentLocation TEXT)";
        private Context mContext;



        public void onCreate(SQLiteDatabase db){
            db.execSQL(CREAT_STUDENT);
//                Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
        }
        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
            db.execSQL("DROP TABLE IF EXISTS StudentInfo");
            Log.d("LogTest", "Database and tables created successfully");
            onCreate(db);
        }
    }
    //创建一个方法，在其他类中调用此方法以便于查询数据
    public static class DatabaseOperations extends SQLiteOpenHelper {
        private Context context;

        public DatabaseOperations(Context context) {
            super(context, "StudentInfo.db", null, 10);
        }
// 更新学生信息的方法
public int updateStudentInfo(String oldName, String oldGender, String newName, String newGender, String newAge, String newLocation) {
    SQLiteDatabase db = getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put("studentName", newName);
    values.put("studentGender", newGender);
    values.put("studentAge", newAge);
    values.put("studentLocation", newLocation);

    String selection = "studentName = ? AND studentGender = ?";
    String[] selectionArgs = {oldName, oldGender};
    // 执行更新操作
    int updatedRows = db.update("StudentInfo", values, selection, selectionArgs);

    // 关闭数据库连接
    db.close();

    return updatedRows;
}
    @SuppressLint("Range")
        public ArrayList<StudentInfo.Student> getAllStudents() {
            ArrayList<StudentInfo.Student> studentList = new ArrayList<>();
            SQLiteDatabase db = getReadableDatabase(); // 使用 getWritableDatabase() 也是可以的
            Cursor cursor = db.query("StudentInfo", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    StudentInfo.Student student = new StudentInfo.Student();
                    student.setName(cursor.getString(cursor.getColumnIndex("studentName")));
                    student.setGender(cursor.getString(cursor.getColumnIndex("studentGender")));
                    student.setGrade(cursor.getString(cursor.getColumnIndex("studentAge")));
                    student.setLocation(cursor.getString(cursor.getColumnIndex("studentLocation")));
                    studentList.add(student);
                } while (cursor.moveToNext());
            }
            cursor.close();
            Log.d("LogTest", "getAllStudents: " + studentList.size() + " students found");
            return studentList;
        }

        // 添加其他方法，例如 onCreate 和 onUpgrade
        @Override
        public void onCreate(SQLiteDatabase db) {
            // 在这里创建你的数据库表
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // 如果需要，可以在这里升级数据库模式
        }
    }

}