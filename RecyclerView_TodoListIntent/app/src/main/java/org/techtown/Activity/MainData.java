package org.techtown.Activity;


public class MainData {
    private String title;
    private String content;

    public MainData(String title, String content){
        this.title = title;
        this.content = content;
    }

    public String getTiltle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}