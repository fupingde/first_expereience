package com.example.first_expereience.studentManageSystem;
import java.util.ArrayList;
import java.util.Scanner;
public class mainBody {
    public static void studentSystem(ArrayList<User>users) {
        Scanner sc = new Scanner(System.in);
        ArrayList<StudentInfo.Student> sif = new ArrayList<>();
        int number = 0;
        while (true) {
            System.out.println("----------欢迎使用学生信息管理系统----------");
            System.out.println("请选择您的需求：");
            System.out.println("1：添加学生信息。");
            System.out.println("2：删除学生信息。");
            System.out.println("3：修改学生信息。");
            System.out.println("4：查询学生信息。");
            System.out.println("5：退出。");
            System.out.println("请输入您的选项：");
        number = sc.nextInt();
        if(number==1) {
                addstudentInfo(sif);
            traverse(sif);
            }
        if (number==2){
                deleteStudentInfo(sif);
            }
        if (number==3){
            changeStudent(sif);
        }
        if (number==4){
            inquireStudentInfo(sif);
        }
        if (number==5){
            boolean afterUse=true;
            break;
        }
    }
    }
    public static ArrayList<StudentInfo.Student> addstudentInfo(ArrayList<StudentInfo.Student> sif){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您想添加的学生人数：");
        int i = sc.nextInt();
        out:
        for (int t=0;t<i;t++) {
            StudentInfo.Student s = new StudentInfo.Student();
            System.out.printf("请输入第%s个学生的id：",t+1);
            String id = sc.next();
            boolean result=false;
            if(sif.size()>0){
                for (int m = 0; m < sif.size(); m++) {
                     result=sif.get(m).getId().equals(id);
                }
            }
            if (result==false) {
                s.setId(id);
            }else{
                System.out.println("您输入的id已存在。");
                break out;
            }
            System.out.printf("请输入第%s个学生的名字：",t+1);
            String name = sc.next();
            s.setName(name);
            System.out.printf("请输入第%s个学生的年龄：",t+1);
            String grade=sc.next();
            s.setGrade(grade);
            System.out.printf("请输入第%s个学生的家庭住址：",t+1);
            String location = sc.next();
            s.setLocation(location);
            sif.add(s);
        }
        return sif;
    }
    public static void deleteStudentInfo(ArrayList<StudentInfo.Student> sif){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的id：");
        String id = sc.next();
        boolean result=false;
        int i = 0;
        if(sif.size()>0){
            for (; i < sif.size(); i++) {
                result=sif.get(i).getId().equals(id);
                while (result==true){
                    break;
                }
            }
        }
        if (result==true){
            sif.remove(i-1);
            System.out.println("该学生信息已删除。");
        }else {
            System.out.println("您想删除的id不存在。");
        }
    }
    public static void changeStudent(ArrayList<StudentInfo.Student> sif){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要修改的id：");
        String id = sc.next();
        boolean result=false;
        int i=0;
        for (; i < sif.size(); i++) {
            result=sif.get(i).getId().equals(id);
            while (result==true){
                break;
            }
        }
            if (result==true){
                System.out.println("请输入修改之后的姓名：");
                String name = sc.next();
                sif.get(i-1).setName(name);
                System.out.println("请输入要修改之后的年龄：");
                String grade = sc.next();
                sif.get(i-1).setGrade(grade);
                System.out.println("请输入要修改之后的家庭住址：");
                String location = sc.next();
                sif.get(i-1).setLocation(location);
                System.out.println("已经修改完成");
                System.out.println("id:"+"     "+"姓名"+"     "+
                        "     "+"年龄"+"     "+"家庭住址");
                System.out.println(
                        sif.get(i-1).getId()+"     "+sif.get(i-1).getName()
                                +"     "+sif.get(i-1).getGrade()+"     "+sif.get(i-1).getLocation());
            }else{
                System.out.println("您输入的id不存在。");
            }
    }
    public static void inquireStudentInfo(ArrayList<StudentInfo.Student> sif){
        while (sif.size()==0){
            System.out.println("当前无学生信息，请添加后再查询。");
            break;
        }
        while (sif.size()>0){
            traverse(sif);
            break;
        }
    }
    public static void traverse(ArrayList<StudentInfo.Student> sif){
        System.out.println("id:"+"     "+"姓名"+"     "+
                "     "+"年龄"+"     "+"家庭住址");
        for(int i=0;i<sif.size();i++){
        System.out.println(
                sif.get(i).getId()+"     "+sif.get(i).getName()
        +"     "+sif.get(i).getGrade()+"     "+sif.get(i).getLocation());
        }
        }
}