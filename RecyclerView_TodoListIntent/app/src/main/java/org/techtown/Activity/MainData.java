package org.techtown.Activity;


import java.util.List;

public class MainData {
    private String tiltle;
    private String content;

    public MainData(String title, String contnet){
        this.tiltle = title;
        this.content = contnet;
    }

    public String getTiltle() {
        return tiltle;
    }

    public String getContent() {
        return content;
    }
}