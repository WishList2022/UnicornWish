package org.techtown.Activity;


public class MainData {
    private String title;
    private String content;
    private int feedId;

    public MainData(String title, String content, int feedId){
        this.title = title;
        this.content = content;
        this.feedId = feedId;
    }

    public String getTiltle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getFeedId() {
        return feedId;
    }
}