package org.techtown.Activity;


public class MainData {


    private String tv_title;
    private String tv_content;

    public MainData(String tv_title, String tv_content){
        this.tv_title = tv_title;
        this.tv_content = tv_content;
    }

    public String getTv_title() {
        return tv_title;
    }

    public String getTv_content() {
        return tv_content;
    }
}