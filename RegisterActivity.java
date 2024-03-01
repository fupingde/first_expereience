package com.example.first_expereience;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.first_expereience.studentManageSystem.User;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
//声明控件
    private static EditText Etun;
    private static EditText Etpw1;
    private static EditText Etpw2;
    private static EditText Etid;
    private static EditText Ettn;
    private Button BtRegister;
    private static MyDatabaseHelper dbHelper;
    private DatabaseOperations databaseOperations = new DatabaseOperations(this);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // 初始化 DatabaseOperations 对象
        databaseOperations = new DatabaseOperations(this);
        dbHelper=new MyDatabaseHelper(this,"User.db",null,10);//下面还有一个版本号，须保持一致
        //找到控件
        Etun = findViewById(R.id.username);Etpw1 = findViewById(R.id.password1);Etpw2 = findViewById(R.id.password2);Etid = findViewById(R.id.IDnumber);Ettn = findViewById(R.id.teleNumber);BtRegister=findViewById(R.id.BtRegister);
        //设置按键效果
        BtRegister.setOnClickListener(this::onClick);
    }
    //按键点击事件
    private void onClick(View v){
        //接收数据
        String userName = Etun.getText().toString();
        String userPassword = Etpw1.getText().toString();
        String userRePassword = Etpw2.getText().toString();
        String userIdnumber = Etid.getText().toString();
        String userTelephoneNumber = Ettn.getText().toString();
        boolean Result=false;
        boolean Result2 = register2(users, Result);
        //从数据库中提取数据,检测用户名是否已经注册
        ArrayList<User> userList = databaseOperations.getAllUsers();
        for (User user : userList) {
            if (User.getUserName().equals(userName)) {
                Toast.makeText(Etun.getContext(), "该用户名已被注册", Toast.LENGTH_SHORT).show();
                Result2 = false;
                break;
            }
        }
        if (Result2==true) {
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            ContentValues values=new ContentValues();

            //组装数据
            values.put("userName",userName);
            values.put("userPassword",userPassword);
            values.put("userRePassword",userRePassword);
            values.put("userIdNumber",userIdnumber);
            values.put("userTelephoneNumber",userTelephoneNumber);
            db.insert("User",null,values);
            Cursor cursor=db.query("User",null,null,null,null,null,null);
            cursor.close();
//        Intent intent=null;
//        intent = new Intent(RegisterActivity.this, MainActivity.class);
//        startActivity(intent);
            finish();
            Toast.makeText(Etun.getContext(), "注册完成",Toast.LENGTH_SHORT).show();
        }
    }
    //创建新用户
    ArrayList<User> users = new ArrayList<>();
    // 插入用户数据
    public long insertUser(String userName, String userPassword, String userRePassword, String userIdNumber, String userTelephoneNumber) {
        SQLiteDatabase db = dbHelper.getMyWritableDatabase();
        ContentValues values = new ContentValues();values.put("userName", userName);values.put("userPassword", userPassword);values.put("userRePassword", userRePassword);values.put("userIdNumber", userIdNumber);values.put("userTelephoneNumber", userTelephoneNumber);
        // 插入数据
        long result = db.insert("User", null, values);
        db.close();
        return result;
    }
    //创建一个方法，在其他类中调用此方法以便于查询数据
    public static class DatabaseOperations {
        private Context context;
        public DatabaseOperations(Context context) {
            this.context = context;  // 保存传入的 Context
            dbHelper = new MyDatabaseHelper(context, "User.db", null, 10);
        }
        @SuppressLint("Range")
        public ArrayList<User> getAllUsers() {
            ArrayList<User> userList = new ArrayList<>();
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query("User", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    User user = new User();
                    user.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
                    user.setUserPassword(cursor.getString(cursor.getColumnIndex("userPassword")));
                    user.setUserRePassword(cursor.getString(cursor.getColumnIndex("userRePassword")));
                    user.setIdNumber(cursor.getString(cursor.getColumnIndex("userIdNumber")));
                    user.setTelephoneNumber(cursor.getString(cursor.getColumnIndex("userTelephoneNumber")));
                    userList.add(user);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            Log.d("LogTest", "getAllUsers: " + userList.size() + " users found");
            return userList;
        }
    }

//创建数据库
public static class MyDatabaseHelper extends SQLiteOpenHelper{
    // 获取可写的数据库
    public SQLiteDatabase getMyWritableDatabase() {
        return this.getWritableDatabase();
    }
    // 获取可读的数据库
    public SQLiteDatabase getMyReadableDatabase() {
        return this.getReadableDatabase();
    }
    public static final String CREAT_USER="create table User("+"id integer primary key autoincrement,"
            +"userName text,"
            +"userPassword text,"
            +"userRePassword text,"
            +"userIdNumber text,"
            +"userTelephoneNumber text)";
    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext=context;
    }


    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREAT_USER);
//        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("drop table if exists User");
        Log.d("LogTest", "Database and tables created successfully");
        onCreate(db);
    }
}
    //创建用户
    public static boolean register2(ArrayList<User> users,boolean Result) {
 //命名获取信息
     String userName = Etun.getText().toString();
     String password1 = Etpw1.getText().toString();
     String password2 = Etpw2.getText().toString();
     String IDnumber = Etid.getText().toString();
     String teleNumber = Ettn.getText().toString();
        User user1 = new User();
        boolean result = false;
        //检查用户名是否符合长度要求
        if (userName.length() < 3 || Etun.length() > 15) {
            Toast.makeText(Etun.getContext(), "您输入的用户名长度错误",Toast.LENGTH_SHORT).show();
            Toast.makeText(Etun.getContext(), "请输入长度在3-15位之间的用户名",Toast.LENGTH_SHORT).show();
        }
        if (users.size() > 0) {
            for (int y = 0; y < users.size(); y++) {
                result = users.get(y).getUserName().equals(userName);
                while (result == true) {
                    break;
                }
            }
        }
        //检查用户名是否重复

        out:
        while (true) {
//            Toast.makeText(Etun.getContext(), "注册完成",Toast.LENGTH_SHORT).show();
            //遍历之前的存储信息，查看是否重复(此方法已废用)
            if (result == true) {
//                Toast.makeText(Etun.getContext(), "您输入的用户名已存在",Toast.LENGTH_SHORT).show();
            } else {
                //分别设置身份证号码和电话号码的验证结果
                boolean testResult1 = false;
                boolean testResult2 = false;
                if (password1.equals(password2)) {
                    while (testResult1 == false) {
                        testResult2 = false;
                        testResult1 = testIdNumber(IDnumber);
                        if (testResult1 == false) {
                            Toast.makeText(Etid.getContext(), "身份证号码出错，请重新输入",Toast.LENGTH_SHORT).show();
                            break out;
                        }
                        while (testResult2 == false) {
                            testResult2 = testTelephoneNumber(teleNumber);
                            if (testResult2 == false) {
                                Toast.makeText(Etid.getContext(), "手机号码出错，请重新输入",Toast.LENGTH_SHORT).show();
                                break out;
                            }
                            //只有身份验证和手机号验证均通过，才能将信息存进数据库中
                            if (testResult1 == true && testResult2 == true) {
                                user1.setUserName(userName);
                                user1.setUserPassword(password1);
                                user1.setIdNumber(IDnumber);
                                user1.setTelephoneNumber(teleNumber);
                                users.add(user1);
                                Result = true;
                            }
                        }
                    }
                } else {
                    Toast.makeText(Etun.getContext(), "两次输入的密码不相同，请重新输入",Toast.LENGTH_SHORT).show();
                    break;
                }
            }break;
        }return Result;
    }
    //测试电话号码
    public static boolean testTelephoneNumber(String telephoneNumber) {
        boolean testResult = true;
        if (telephoneNumber.length()==0){
            Toast.makeText(Etid.getContext(), "手机号码不能为空",Toast.LENGTH_SHORT).show();
            testResult = false;
        }
        int h = telephoneNumber.charAt(0) + 0;
        if (telephoneNumber.length() != 11) {
            testResult = false;
        }
        if (h == 0) {
            testResult = false;
        }
        //手机号码每一位都必须位于0~9之间
        for (int i = 0; i < telephoneNumber.length(); i++) {
            int b = telephoneNumber.charAt(i) + 0;
            if (b < 48 || b > 57) {
                testResult = false;
            }
        }
        return testResult;
    }
//测试身份证号码
    public static boolean testIdNumber(String idNmunber) {
        boolean testResult = true;
        if (idNmunber.length()==0){
            Toast.makeText(Etid.getContext(), "身份证号码不能为空",Toast.LENGTH_SHORT).show();
            testResult = false;
        }
        if (idNmunber.length() != 18) {
            testResult = false;
        }
        char c = idNmunber.charAt(0);
        if (c == 0) {
            testResult = false;
        }
        //身份证号码最后一位只能为数字或者x、X
        if (idNmunber.length() == 18) {
            for (int i = 0; i < 17; i++) {
                char n = idNmunber.charAt(i);
                if (n < 48 || n > 57) {
                    testResult = false;
                }
                int m = idNmunber.charAt(17) + 0;

                if (m < 48 || m > 57) {
                    testResult = false;
                    if (m == 88 || m == 120) {
                        testResult = true;
                    }
                }
            }
        }
        return testResult;
    }
}