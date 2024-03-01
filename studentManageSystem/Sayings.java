package com.example.first_expereience.studentManageSystem;

public class Sayings {

        private String sayings;


        public Sayings() {
        }

        public Sayings(String sayings) {
            this.sayings = sayings;
        }

        /**
         * 获取
         * @return sayings
         */
        public String getSayings() {
            return sayings;
        }

        /**
         * 设置
         * @param sayings
         */
        public void setSayings(String sayings) {
            this.sayings = sayings;
        }

        public String toString() {
            return "Sayings{sayings = " + sayings + "}";
        }
}
