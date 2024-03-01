package com.example.first_expereience.studentManageSystem;

import android.provider.BaseColumns;

public class StudentInfo {



    public static class Student implements BaseColumns {
        public static final String TABLE_NAME = "StudentInfo";

        private String id;
        private String gender;
        private String Name;
        private String grade;
        private String location;

        public Student() {
        }

        public Student(String id, String username,String gender, String grade, String location) {
            this.id = id;
            this.Name = Name;
            this.gender=gender;
            this.grade = grade;
            this.location = location;
        }

        /**
         * 获取
         * @return id
         */
        public String getId() {
            return id;
        }

        /**
         * 设置
         * @param id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * 获取
         * @return StudentName
         */
        public String getName() {
            return Name;
        }

        /**
         * 设置
         * @param Name
         */
        public void setName(String Name) {
            this.Name = Name;
        }

        /**
         * 获取
         * @return age
         */
        public String getGrade() {
            return grade;
        }
        /**
         * 设置
         * @param grade
         */
        public void setGrade(String grade) {
            this.grade = grade;
        }
        /**
         * 设置
         * @param gender
         */
        public void setGender(String gender) {
            this.gender = gender;
        }
        /**
         * 获取
         * @return gender
         */
        public String getGender() {
            return gender;
        }
        /**
         * 获取
         * @return location
         */
        public String getLocation() {
            return location;
        }

        /**
         * 设置
         * @param location
         */
        public void setLocation(String location) {
            this.location = location;
        }

        public String toString() {
            return "Student{id = " + id + ", username = " + Name + ",gender="+gender+", grade = " + grade + ", location = " + location + "}";
        }

    }
}
