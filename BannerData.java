package com.example.first_expereience;

import java.util.ArrayList;

public class BannerData {
    ArrayList<DetailData> data;
    int errorCode;
    String errorMsg;

    static class DetailData {
        String desc;
        int id;
        String imagePath;
        int isVisible;
        int order;
        String title;
        int type;
        String url;
        public DetailData() {
        }

        public DetailData(String desc, int id, String imagePath, int isVisible, int order, String title, int type, String url) {
            this.desc = desc;
            this.id = id;
            this.imagePath = imagePath;
            this.isVisible = isVisible;
            this.order = order;
            this.title = title;
            this.type = type;
            this.url = url;
        }

        /**
         * 获取
         *
         * @return desc
         */
        public String getDesc() {
            return desc;
        }

        /**
         * 设置
         *
         * @param desc
         */
        public void setDesc(String desc) {
            this.desc = desc;
        }

        /**
         * 获取
         *
         * @return id
         */
        public int getId() {
            return id;
        }

        /**
         * 设置
         *
         * @param id
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * 获取
         *
         * @return imagePath
         */
        public String getImagePath() {
            return imagePath;
        }

        /**
         * 设置
         *
         * @param imagePath
         */
        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        /**
         * 获取
         *
         * @return isVisible
         */
        public int getIsVisible() {
            return isVisible;
        }

        /**
         * 设置
         *
         * @param isVisible
         */
        public void setIsVisible(int isVisible) {
            this.isVisible = isVisible;
        }

        /**
         * 获取
         *
         * @return order
         */
        public int getOrder() {
            return order;
        }

        /**
         * 设置
         *
         * @param order
         */
        public void setOrder(int order) {
            this.order = order;
        }

        /**
         * 获取
         *
         * @return title
         */
        public String getTitle() {
            return title;
        }

        /**
         * 设置
         *
         * @param title
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * 获取
         *
         * @return type
         */
        public int getType() {
            return type;
        }

        /**
         * 设置
         *
         * @param type
         */
        public void setType(int type) {
            this.type = type;
        }

        /**
         * 获取
         *
         * @return url
         */
        public String getUrl() {
            return url;
        }

        /**
         * 设置
         *
         * @param url
         */
        public void setUrl(String url) {
            this.url = url;
        }

        public String toString() {
            return "DetailData{desc = " + desc + ", id = " + id + ", imagePath = " + imagePath + ", isVisible = " + isVisible + ", order = " + order + ", title = " + title + ", type = " + type + ", url = " + url + "}";
        }
    }
}


