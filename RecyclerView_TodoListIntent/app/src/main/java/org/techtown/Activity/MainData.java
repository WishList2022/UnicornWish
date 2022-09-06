package org.techtown.Activity;


public class MainData {
    private String title;
    private String content;
    private int feed_id;


    public MainData(String title, String content, int feed_id){
        this.title = title;
        this.content = content;
        this.feed_id = feed_id;
    }

    public String getTiltle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getFeed_id() {
        return feed_id;
    }
}