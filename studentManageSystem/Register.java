package com.example.first_expereience.studentManageSystem;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Register {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作类型：");
            System.out.println("1：登录账户");
            System.out.println("2：注册账户");
            System.out.println("3：忘记密码");
            int number = sc.nextInt();
            switch (number) {
//                case 1 -> register1(users);
//                case 2 -> register2(users);
//                case 3 -> forgetPassword(users);
//                default -> System.out.println("无此选项，请重新输入");
            }
        }
    }

    public static void register1(ArrayList<User> users) {
        Scanner sc = new Scanner(System.in);
        //设置次数
        int times = 0;
        while (times <= 2) {
            System.out.println("请输入用户名：");
            String userName = sc.next();
            boolean result = false;
            //设置一个数，判定是否需要跳过剩下的步骤
            int j = 0;
            if (users.size() == 0) {
                System.out.println("您输入的用户名不存在");
                System.out.println("请先注册");
                return;
            }
            for (int y = 0; y < users.size(); y++) {
                result = users.get(y).getUserName().equals(userName);
                if (result == true) {
                    break;
                }
                while (result == false) {
                    j = 1;
                    break;
                }
                if (result == false) {
                    System.out.println("您输入的用户名不存在");
                    System.out.println("请先注册");

                }
            }
            if (j == 1) {
                continue;
            }
            System.out.println("请输入密码：");
            String userPassword = sc.next();
            boolean result1 = false;
            boolean result2 = false;
            for (int i = 0; i < users.size(); i++) {
                result1 = users.get(i).getUserName().equals(userName);
                result2 = users.get(i).getUserPassword().equals(userPassword);
            }
            while (result2 == false) {
                if (times == 3) {
                    System.out.println("您已输错3次");
                    System.out.println("请下次再试");
                    break;
                }
                System.out.println("密码输入错误");
                System.out.println("请重新输入");
                times++;
                break;
            }
            while (result1 == true && result2 == true) {
                boolean afterUse = false;
                mainBody.studentSystem(users);
                if (afterUse == true) {
                    break;
                }
                //保证继续循环
                times = 0;
            }
        }
    }

    public static void register2(ArrayList<User> users) {
        User user1 = new User();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String userName = sc.next();
        boolean result = false;
        if (userName.length() < 3 || userName.length() > 15) {
            System.out.println("您输入的用户名长度错误");
            System.out.println("请输入长度在3-15位之间的用户名");
            return;
        }
        for (int i = 0; i < userName.length(); i++) {
            int c = userName.charAt(i) + 0;
            int m = 0;
            if ((c + 0 >= 97 && c + 0 <= 122) || (c + 0 >= 65 && c + 0 <= 90)) {
                m++;
            }
            if ((c + 0 >= 48 && c + 0 <= 57) || (c + 0 >= 97 && c + 0 <= 122) || (c + 0 >= 65 && c + 0 <= 90) && m >= 1) {
                break;
            } else {
                System.out.println("用户名必须为字母加数字");
                return;
            }
        }
        if (users.size() > 0) {
            for (int y = 0; y < users.size(); y++) {
                result = users.get(y).getUserName().equals(userName);
                while (result == true) {
                    break;
                }
            }
        }
        while (true) {
            if (result == true) {
                System.out.println("您输入的用户名已存在");
                return;
            } else {
                System.out.println("请输入密码：");
                String password = sc.next();
                System.out.println("请再次输入密码：");
                String password2 = sc.next();
                boolean testResult = false;
                boolean testResult2 = false;
                if (password.equals(password2)) {
                    while (testResult == false) {
                        testResult2 = false;
                        System.out.println("请输入身份证号码：");
                        String idNumber = sc.next();
                        testResult = testIdNumber(idNumber);
                        if (testResult == false) {
                            System.out.println("身份证号码出错");
                            System.out.println("请重新输入");
                            testResult2 = true;
                        }
                        while (testResult2 == false) {
                            System.out.println("请输入手机号码：");
                            String telephoneNumber = sc.next();
                            testResult2 = testTelephoneNumber(telephoneNumber);
                            if (testResult2 == false) {
                                System.out.println("手机号码出错");
                                System.out.println("请重新输入");
                            }
                            if (testResult == true && testResult2 == true) {
                                user1.setUserName(userName);
                                user1.setUserPassword(password);
                                user1.setIdNumber(idNumber);
                                user1.setTelephoneNumber(telephoneNumber);
                                users.add(user1);
                                System.out.println("注册完成");
                            }
                        }
                    }
                    break;
                } else {
                    System.out.println("两次输入的密码不相同");
                    System.out.println("请重新输入：");
                }
            }
        }
    }

    public static void forgetPassword(ArrayList<User> users) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String userName = sc.next();
        boolean result1 = false;
        boolean result2 = false;
        boolean result3 = false;
        if (users.size() == 0) {
            System.out.println("用户名不存在");
        } else {
            for (int i = 0; i < users.size(); i++) {
                result1 = users.get(i).getUserName().equals(userName);
            }
            if (result1 == false) {
                System.out.println("用户名不存在");
                System.out.println("请先注册");
            } else {
                System.out.println("请输入身份证号");
                String idNumber = sc.next();
                System.out.println("请输入手机号码");
                String telephoneNumber = sc.next();
                for (int y = 0; y < users.size(); y++) {
                    result2 = users.get(y).getIdNumber().equals(idNumber);
                    result3 = users.get(y).getTelephoneNumber().equals(telephoneNumber);
                    if (result2 == true && result3 == true) {
                        System.out.println("请输入您要修改的密码");
                        String afterChange = sc.next();
                        users.get(y).setUserPassword(afterChange);
                        System.out.println("修改完成");
                    }
                }
                if (result2 == false || result3 == false) {
                    System.out.println("账号信息不匹配，修改失败");
                }
            }
        }
    }

    public static boolean testTelephoneNumber(String telephoneNumber) {
        boolean testResult = true;
        int h = telephoneNumber.charAt(0) + 0;
        if (telephoneNumber.length() != 11) {
            testResult = false;
        }
        if (h == 0) {
            testResult = false;
        }
        for (int i = 0; i < telephoneNumber.length(); i++) {
            int b = telephoneNumber.charAt(i) + 0;
            if (b < 48 || b > 57) {
                testResult = false;
            }
        }
        return testResult;
    }

    public static boolean testIdNumber(String idNmunber) {
        boolean testResult = true;
        if (idNmunber.length() != 18) {
            testResult = false;
        }
        char c = idNmunber.charAt(0);
        if (c == 0) {
            testResult = false;
        }
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