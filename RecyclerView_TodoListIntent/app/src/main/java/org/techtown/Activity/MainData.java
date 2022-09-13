package org.techtown.Activity;


public class MainData {
    private String title;
    private String content;
    private int feed_id;

    private boolean selected = false;


    public MainData(String title, String content, int feed_id){
        this.title = title;
        this.content = content;
        this.feed_id = feed_id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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