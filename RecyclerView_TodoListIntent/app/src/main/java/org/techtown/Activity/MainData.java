package org.techtown.Activity;


public class MainData {
    private String tiltle;
    private String content;

    public MainData(String title, String content){
        this.tiltle = title;
        this.content = content;
    }

    public String getTiltle() {
        return tiltle;
    }

    public String getContent() {
        return content;
    }
}